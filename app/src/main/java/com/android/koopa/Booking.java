package com.android.koopa;

public class Booking {
    private String worksSite;
    private String workDate;
    private String bookedUser;
    private String bookedDate;
    private String bookedUserId;

    public Booking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

    public String getWorksSite() {
        return worksSite;
    }

    public void setWorksSite(String worksSite) {
        this.worksSite = worksSite;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getBookedUser() {
        return bookedUser;
    }

    public void setBookedUser(String bookedUser) {
        this.bookedUser = bookedUser;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getBookedUserId() {
        return bookedUserId;
    }

    public void setBookedUserId(String bookedUserId) {
        this.bookedUserId = bookedUserId;
    }
}
