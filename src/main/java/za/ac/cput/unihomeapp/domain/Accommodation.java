/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.unihomeapp.domain;

/**
 *
 * @author samuk
 */
public class Accommodation {
    private int accommodation_id;
    private int residence_id;
    private int landlord_id;
    private String room_type;
    private double price;
    private String availability_status;
    private String lease_duration;

    public Accommodation() {
    }

    public Accommodation(int accommodation_id, int residence_id, int landlord_id, String room_type, double price, String availability_status, String lease_duration) {
        this.accommodation_id = accommodation_id;
        this.residence_id = residence_id;
        this.landlord_id = landlord_id;
        this.room_type = room_type;
        this.price = price;
        this.availability_status = availability_status;
        this.lease_duration = lease_duration;
    }

    public int getAccommodation_id() {
        return accommodation_id;
    }

    public void setAccommodation_id(int accommodation_id) {
        this.accommodation_id = accommodation_id;
    }

    public int getResidence_id() {
        return residence_id;
    }

    public void setResidence_id(int residence_id) {
        this.residence_id = residence_id;
    }

    public int getLandlord_id() {
        return landlord_id;
    }

    public void setLandlord_id(int landlord_id) {
        this.landlord_id = landlord_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(String availability_status) {
        this.availability_status = availability_status;
    }

    public String getLease_duration() {
        return lease_duration;
    }

    public void setLease_duration(String lease_duration) {
        this.lease_duration = lease_duration;
    }

    @Override
    public String toString() {
        return "Accommodation{" + "accommodation_id=" + accommodation_id + ", residence_id=" + residence_id + ", landlord_id=" + landlord_id + ", room_type=" + room_type + ", price=" + price + ", availability_status=" + availability_status + ", lease_duration=" + lease_duration + '}';
    }
    
    
    
    
}
