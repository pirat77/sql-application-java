package com.codecool.si3.DAO;


import com.codecool.si3.model.Applicant;
import com.codecool.si3.model.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ApplicantDao  extends SQLDao<Applicant> implements Dao<Applicant> {
    static ApplicantDao applicantDao;

    public static ApplicantDao getInstance(){
        if (applicantDao == null) applicantDao = new ApplicantDao();
        return applicantDao;
    }

    private ApplicantDao(){
        super("applicants", new String[]{"id", "first_name", "last_name", "phone_number", "email", "application_code"});
    }

    @Override
    protected Entry[] objectToArray(Applicant applicant) {
        Entry id = new Entry("id", Integer.toString(applicant.getId()));
        Entry first_name = new Entry("first_name", applicant.getFirst_name());
        Entry last_name = new Entry("last_name", applicant.getLast_name());
        Entry phone_number = new Entry("phone_number", applicant.getPhone_number());
        Entry email = new Entry("email", applicant.getEmail());
        Entry application_code = new Entry("application_code", Integer.toString(applicant.getApplication_code()));
        return new Entry[]{id, first_name, last_name, phone_number, email, application_code};
    }

    @Override
    public void update(Applicant applicant) {
        updateRecord(objectToArray(applicant));
    }

    @Override
    public void remove(Applicant applicant) {
        removeRecord(new Entry("id", Integer.toString(applicant.getId()))); }

    @Override
    public void insert(Applicant applicant) { insertRecord(objectToArray(applicant)); }

    public List<Applicant> getAppById(Entry entry){
        return applicantListFromResultSet(getById(entry));
    }

   public List<Applicant> applicantListFromResultSet(ResultSet resultSet){
       List<Applicant> applicants = new ArrayList<>();
       try {
           while (resultSet.next()) {
               Applicant applicant = new Applicant();
               applicant.setId(resultSet.getInt("id"));
               applicant.setFirst_name(resultSet.getString("first_name"));
               applicant.setLast_name(resultSet.getString("last_name"));
               applicant.setPhone_number(resultSet.getString("phone_number"));
               applicant.setEmail(resultSet.getString("email"));
               applicant.setApplication_code(resultSet.getInt("application_code"));
               applicants.add(applicant);
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return applicants;
   }

    @Override
    public List<Applicant> getObjects(Entry entry) {
         return applicantListFromResultSet(getRecords(entry));
    }
}

