package com.codecool.si3.controler;

import com.codecool.si3.DAO.ApplicantDao;
import com.codecool.si3.DAO.MentorDao;
import com.codecool.si3.model.*;
import com.codecool.si3.view.View;

import java.util.ArrayList;
import java.util.List;

public class UniversalActions  {

        private void updateApplicantDetails(){
            String id = Integer.toString(InputProvider.getInstance().getIntInput("Provide applicant Id"));
            List<Applicant> applicants = ApplicantDao.getInstance().getAppById(new Entry("id", id));
            if (applicants.size() == 1) {
                Applicant applicant = applicants.get(0);
                applicant.setFirst_name(InputProvider.getInstance().getStringInput("Provide new first name."));
                applicant.setLast_name(InputProvider.getInstance().getStringInput("Provide new last name."));
                applicant.setPhone_number(InputProvider.getInstance().getStringInput("Provide phone number."));
                applicant.setEmail(InputProvider.getInstance().getStringInput("Provide new email"));
                applicant.setApplication_code(InputProvider.getInstance().getIntInput("Provide new application code"));
                ApplicantDao.getInstance().update(applicant);
                viewApplicantsDetails();
            }    else {
                InputProvider.getInstance().getStringInput("There is no applicant with that id! Press enter to confirm.");
            }
        }

        private void insertApplicant(){
            Applicant applicant = new Applicant();
            applicant.setFirst_name(InputProvider.getInstance().getStringInput("Provide first name."));
            applicant.setLast_name(InputProvider.getInstance().getStringInput("Provide last name."));
            applicant.setPhone_number(InputProvider.getInstance().getStringInput("Provide phone number."));
            applicant.setEmail(InputProvider.getInstance().getStringInput("Provide email."));
            applicant.setApplication_code(InputProvider.getInstance().getIntInput("Provide application code."));
            ApplicantDao.getInstance().insert(applicant);
            viewApplicantsDetails();
        }

        private void insertMentor(){
            Mentor mentor = new Mentor();
            mentor.setFirst_name(InputProvider.getInstance().getStringInput("Provide first name."));
            mentor.setLast_name(InputProvider.getInstance().getStringInput("Provide last name."));
            mentor.setNick_name(InputProvider.getInstance().getStringInput("Provide nick name."));
            mentor.setPhone_number(InputProvider.getInstance().getStringInput("Provide phone number."));
            mentor.setEmail(InputProvider.getInstance().getStringInput("Provide email."));
            mentor.setCity(InputProvider.getInstance().getStringInput("Provide city."));
            mentor.setFavourite_number(InputProvider.getInstance().getIntInput("Provide favourite number."));
            MentorDao.getInstance().insert(mentor);
            viewMentorDetails();
        }

        private void updateMentorDetails(){
            String id = Integer.toString(InputProvider.getInstance().getIntInput("Provide mentor id"));
            List<Mentor> mentors = MentorDao.getInstance().getMentorById(new Entry("id", id));
            if (mentors.size() == 1) {
                Mentor mentor = mentors.get(0);
                mentor.setFirst_name(InputProvider.getInstance().getStringInput("Provide new first name."));
                mentor.setLast_name(InputProvider.getInstance().getStringInput("Provide new last name."));
                mentor.setNick_name(InputProvider.getInstance().getStringInput("Provide new nick name."));
                mentor.setPhone_number(InputProvider.getInstance().getStringInput("Provide new phone number."));
                mentor.setEmail(InputProvider.getInstance().getStringInput("Provide new email."));
                mentor.setCity(InputProvider.getInstance().getStringInput("Provide new city."));
                mentor.setFavourite_number(InputProvider.getInstance().getIntInput("Provide new favourite number."));
                MentorDao.getInstance().update(mentor);
                viewMentorDetails();
            }   else {
                InputProvider.getInstance().getStringInput("There is no mentor with that id! Press enter to confirm.");
            }
        }

        private void viewMentorDetails(){
            String[] querryHeaders = {"ID", "FIRST_NAME", "LAST_NAME", "NICK_NAME", "PHONE_NUMBER", "EMAIL", "CITY", "FAVOURITE NUMBER"};
            View.getInstance().setQuerryHeaders(querryHeaders);
            List<Mentor> mentors = MentorDao.getInstance().getObjects(new Entry("first_name", "%"));
            List<Displayable> displayableMentors = new ArrayList<>();
            for (Mentor mentor: mentors){
                displayableMentors.add((Displayable) mentor);
            }
            View.getInstance().setQuerryList(displayableMentors);
        }

        private void viewApplicantsDetails(){
            String[] querryHeaders = {"ID", "FIRST_NAME", "LAST_NAME", "PHONE_NUMBER", "EMAIL", "APPLICATION_CODE"};
            View.getInstance().setQuerryHeaders(querryHeaders);
            List<Applicant> applicants = ApplicantDao.getInstance().getObjects(new Entry("first_name", "%"));
            List<Displayable> displayableApplicants = new ArrayList<>();
            for (Applicant applicant: applicants){
                displayableApplicants.add((Displayable) applicant);
            }
            View.getInstance().setQuerryList(displayableApplicants);
        }

        private void exitLab(){
            System.exit(0);
        }

        public List<MenuOption> returnActions() {
            List<MenuOption> options = new ArrayList<>();
            options.add(new MenuOption("View all applicants.", this::viewApplicantsDetails));
            options.add(new MenuOption("View all mentors.", this::viewMentorDetails));
            options.add(new MenuOption("Update applicant details.", this::updateApplicantDetails));
            options.add(new MenuOption("Update mentor details.", this::updateMentorDetails));
            options.add(new MenuOption("Insert new mentor", this::insertMentor));
            options.add(new MenuOption("Insert new applicant", this::insertApplicant));
            options.add(new MenuOption("Exit this labirynth now!", this::exitLab));
            return options;
        }
    }
