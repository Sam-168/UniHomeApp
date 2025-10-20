package za.ac.cput.unihomeapp.domain;
import java.util.ArrayList;

public class Application {
    private int studentId;
    private String gender;
    private String campus;
    private double average;
    private String status;
    private ArrayList<String> subjects;
    private ArrayList<Double> marks;
    private String fullName;
    private int applicationId;
    
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
    
    public int getApplicationId() {
        return applicationId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setCampus(String campus) {
        this.campus = campus;
    }
    
    public void setAverage(double average) {
        this.average = average;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }
    
    public void setMarks(ArrayList<Double> marks) {
        this.marks = marks;
    }
    
    public Application() {
    }
    
    public Application(int studentId, String gender, String campus, double average, String status,
                       ArrayList<String> subjects, ArrayList<Double> marks) {
        this.studentId = studentId;
        this.gender = gender;
        this.campus = campus;
        this.average = average;
        this.status = status;
        this.subjects = subjects;
        this.marks = marks;
    }
    
    // FIXED: Now calculates average automatically
    public Application(int studentId, String fullName, String gender, String campus,
                       ArrayList<String> subjects, ArrayList<Double> marks) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gender = gender;
        this.campus = campus;
        this.subjects = subjects;
        this.marks = marks;
        this.status = "Pending";
        this.average = calculateAverage(marks); // Calculate average here
    }
    
    // Helper method to calculate average
    private double calculateAverage(ArrayList<Double> marks) {
        if (marks == null || marks.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Double mark : marks) {
            sum += mark;
        }
        return sum / marks.size();
    }
    
    public String getFullName() { 
        return fullName; 
    }
    
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
    }
    
    public int getStudentId() { 
        return studentId; 
    }
    
    public String getGender() { 
        return gender; 
    }
    
    public String getCampus() { 
        return campus; 
    }
    
    public double getAverage() { 
        return average; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public ArrayList<String> getSubjects() { 
        return subjects; 
    }
    
    public ArrayList<Double> getMarks() { 
        return marks; 
    }
}