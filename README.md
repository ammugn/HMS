# MediTech HMS
A Hospital Management System that has three user roles:Admin,Doctor and Patients

## Technologies

Springboot 2.7.1<br>
Maven<br>
Java corretto 11<br>
Spring JPA<br>
MariaDB<br>
Spring Security<br>
Thymeleaf<br>
HTML,CSS,Bootstrap,JQuery<br>
JUnit<br>


## Dependencies
Spring web<br>
Lombok<br>
Thymeleaf<br>
Mariadb Driver<br>
Spring Data JPA<br>
Validation<br>
H2 Memory Database(testing)<br>

## Epics

### Admin
  - As an admin I want to login to the system so that I can access my Dashboard
  - As an admin I want to view Patient list so that I can add,delete or update the patients
  - As an admin I want to view Doctor list so that I can add,delete or update the doctors
  - As an admin I want view Appoinment list so that I can add,delete or update the appointments
  - As an admin I want to logout of the system so that I can exit from the hms
### Patient
  - As a patient I want to register to the system so that I can create an account to use the system
  - As a patient I want to login to the system so that I can access my Dashboard
  - As a patient I want to make new appoinments so that I can consult a doctor
  - As a patient I want to view my Appoinment list so that I can remember the details
  - As an admin I want to logout of the system so that I can exit the application
### Doctor
  - As a doctor I want to register to the system so that I can create an account to use the system
  - As a doctor I want to login to the system so that I can access my Dashboard
  - As a doctor I want to view my Appoinment list so that I can plan my day
  - As a doct I want to edit my Appoinments list to update the diagnosis and treatment plan
  - As an admin I want to logout of the system so that I can exit the application
