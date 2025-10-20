/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.domain;

/**
 *
 * @author samuk
 */


public class Subject {
    private String subjectName;
    private double mark;

    public Subject(String subjectName, double mark) {
        this.subjectName = subjectName;
        this.mark = mark;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public double getMark() {
        return mark;
    }
}

