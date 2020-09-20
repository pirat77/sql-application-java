select first_name, last_name from mentors;
select nick_name from mentors where city='Miskolc';
select CONCAT(first_name, ' ', last_name) as full_name, phone_number from applicants where first_name='Carol';
select CONCAT(first_name, ' ', last_name) as full_name, phone_number from applicants where email like '%@adipiscingenimmi.edu';
insert into applicants (first_name, last_name, phone_number, email, application_code) VALUES ('Markus','Schaffarzyk','003620/725-2666','djnovus@groovecoverage.com',54823
);
select * from applicants where application_code='54823';
update applicants set phone_number='003670/223-7459' where first_name='Jemima' and last_name='Foreman';
select phone_number from applicants where first_name='Jemima' and last_name='Foreman';
delete from applicants where email like '%mauriseu.net';