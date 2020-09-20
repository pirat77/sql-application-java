package com.codecool.si3.controler;

import com.codecool.si3.DAO.ApplicantDao;
import com.codecool.si3.model.Applicant;
import com.codecool.si3.model.Displayable;
import com.codecool.si3.model.Entry;
import com.codecool.si3.model.MenuOption;
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
            options.add(new MenuOption("Update appplicant details.", this::updateApplicantDetails));
            options.add(new MenuOption("Exit this labirynth now!", this::exitLab));
            System.out.println(options);
            return options;
        }
    }
