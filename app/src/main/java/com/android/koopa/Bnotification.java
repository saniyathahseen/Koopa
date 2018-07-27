package com.android.koopa;

public class Bnotification {
    private String worksSite;
    private String workDate;
    private String bookedworker;

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

    public String getBookedworker() {
        return bookedworker;
    }

    public void setBookedworker(String bookedworker) {
        this.bookedworker = bookedworker;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String bookedDate;
    private String status;
}
