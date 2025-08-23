package za.ac.cput.unihomeapp.domain;

import java.sql.Date;

/**
 *
 * @author 220239215
 */
public class Students {
    private int student_ID;
    private int uni_ID;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String password;
    private Date dob ;
    private String gender;
     private String current_Address;
    private int academicyear;
    private String faculty_Department;

    public Students(int student_ID, int uni_ID, String first_name, String last_name, String email, String phone, String password, Date dob, String gender, String current_Address, int academicyear, String faculty_Department) {
        this.student_ID = student_ID;
        this.uni_ID = uni_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.current_Address = current_Address;
        this.academicyear = academicyear;
        this.faculty_Department = faculty_Department;
    }

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public int getUni_ID() {
        return uni_ID;
    }

    public void setUni_ID(int uni_ID) {
        this.uni_ID = uni_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrent_Address() {
        return current_Address;
    }

    public void setCurrent_Address(String current_Address) {
        this.current_Address = current_Address;
    }

    public int getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(int academicyear) {
        this.academicyear = academicyear;
    }

    public String getFaculty_Department() {
        return faculty_Department;
    }

    public void setFaculty_Department(String faculty_Department) {
        this.faculty_Department = faculty_Department;
    }

    @Override
    public String toString() {
        return student_ID + ": " + first_name + " " + last_name + " | " + email + " | " + phone + " | " +  faculty_Department;
    }
   
}