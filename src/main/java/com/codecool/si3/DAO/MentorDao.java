package com.codecool.si3.DAO;
import com.codecool.si3.model.Mentor;
import com.codecool.si3.model.Entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MentorDao  extends SQLDao<Mentor> implements Dao<Mentor> {
    static MentorDao mentorDao;

    public static MentorDao getInstance(){
        if (mentorDao == null) mentorDao = new MentorDao();
        return mentorDao;
    }

    public MentorDao(){
        super("mentors", new String[]{"id", "first_name", "last_name", "nick_name", "phone_number", "email", "city", "favourite_number"});
    }

    @Override
    protected Entry[] objectToArray(Mentor mentor) {
        Entry id = new Entry("id", Integer.toString(mentor.getId()));
        Entry first_name = new Entry("first_name", mentor.getFirst_name());
        Entry last_name = new Entry("last_name", mentor.getLast_name());
        Entry nick_name = new Entry("nick_name", mentor.getNick_name());
        Entry phone_number = new Entry("phone_number", mentor.getPhone_number());
        Entry email = new Entry("email", mentor.getEmail());
        Entry city = new Entry("city", mentor.getCity());
        Entry favourite_number = new Entry("favourite_number", Integer.toString(mentor.getFavourite_number()));
        return new Entry[]{id, first_name, last_name, nick_name, phone_number, email, city, favourite_number};
    }

    @Override
    public void update(Mentor mentor) {
        updateRecord(objectToArray(mentor));
    }

    @Override
    public void remove(Mentor mentor) {
        removeRecord(new Entry("id", Integer.toString(mentor.getId()))); }

    @Override
    public void insert(Mentor mentor) { insertRecord(objectToArray(mentor)); }

    public List<Mentor> getMentorById(Entry entry){
        return mentorListFromResultSet(getById(entry));
    }

    private List<Mentor> mentorListFromResultSet(ResultSet resultSet){
        List<Mentor> mentors = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(resultSet.getInt("id"));
                mentor.setFirst_name(resultSet.getString("first_name"));
                mentor.setLast_name(resultSet.getString("last_name"));
                mentor.setNick_name(resultSet.getString("nick_name"));
                mentor.setPhone_number(resultSet.getString("phone_number"));
                mentor.setEmail(resultSet.getString("email"));
                mentor.setCity(resultSet.getString("city"));
                mentor.setFavourite_number(resultSet.getInt("favourite_number"));
                mentors.add(mentor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }

    @Override
    public List<Mentor> getObjects(Entry entry) {
        return mentorListFromResultSet(getRecords(entry));
    }
}
