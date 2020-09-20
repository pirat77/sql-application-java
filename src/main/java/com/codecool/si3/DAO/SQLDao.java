package com.codecool.si3.DAO;
import com.codecool.si3.model.Entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class SQLDao<T> {
    protected Connection connection;
    protected PreparedStatement statement;
    protected String[] columnNames;
    protected String tableName;
    protected PostgreSQLJDBC JDBCInstance;
    protected String columnsString;
    protected String insertString;
    protected String removeString;
    protected String selectString;
    protected HashMap<String, Boolean> isIntFlag;

    public SQLDao(String tableName, String[] columnNames) {
        this.tableName = tableName;
        this.columnNames = columnNames;
        updateFlags();
        JDBCInstance = PostgreSQLJDBC.getInstance();
        buildQueryString();
    }

    private void updateFlags(){
        this.isIntFlag = new HashMap<>();
        for (String column: columnNames) {
            this.isIntFlag.put(column, column.contains("favourite_number") || column.contains("id")
                                        || column.contains("application_code"));
        }
    }

    private void buildQueryString(){
        createColumnsString();
        createInsertString();
        createRemoveString();
        createSelectString();
    }

    private void createSelectString(){ this.selectString = "SELECT * FROM " + this.tableName + " WHERE %s LIKE ?"; }

    private void createRemoveString(){ this.removeString = "DELETE FROM " + this.tableName + "  WHERE Id =  ? "; }

    private void createColumnsString(){
        StringBuilder columns = new StringBuilder(" ( " + columnNames[0]);
        for (int i=1; i<columnNames.length; i++) { columns.append(", " + columnNames[i]); }
        columns.append(" ) ");
        this.columnsString = columns.toString();
    }

    private void createInsertString() {
        StringBuilder query = new StringBuilder("INSERT INTO " + this.tableName + this.columnsString + " VALUES ( ? ");
        for (int i = 1; i < columnNames.length; i++) { query.append(", ?"); }
        query.append(")");
        this.insertString = query.toString();
    }

    protected ResultSet executeQuery(String query, Entry[] parameters) {
        ResultSet resultSet = null;
        try{
            this.connection = JDBCInstance.connect();
            createStatement(query);
            updateParameters(parameters);
            this.statement.execute();
            resultSet = this.statement.getResultSet();
            this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private void createStatement(String query) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        statement = connection.prepareStatement(query);
    }

    private void updateParameters(Entry[] parameters) throws SQLException {
        for (int i = 1; i<=parameters.length; i++){
            if(isIntFlag.get(parameters[i-1].getColumnName())){
                this.statement.setInt(i, Integer.parseInt(parameters[i-1].getColumnValue()));
            } else {
                this.statement.setString(i, parameters[i-1].getColumnValue());
            }
            System.out.println(statement.toString());
        }
    }

    protected void updateRecord(Entry[] newValues) {
        String id = newValues[0].getColumnValue();
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String column : columnNames) { query.append(column).append(" = ?"); }
        query.append(" WHERE Id = ").append(id).append(";");
        executeQuery(query.toString(), newValues);
    }

    protected void removeRecord(Entry id) {
        executeQuery(this.removeString, new Entry[]{id});
    }

    protected void insertRecord(Entry[] values) {
        executeQuery(this.insertString, values);
    }

    protected  ResultSet getRecords(Entry entry){
        Entry[] parameters = new Entry[]{entry};
        String searchQuery = String.format(this.selectString, entry.getColumnName());
        System.out.println(searchQuery);
        return executeQuery(searchQuery, parameters);
    }

    protected abstract Entry[] objectToArray(T t);

}