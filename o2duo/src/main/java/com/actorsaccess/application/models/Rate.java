package com.actorsaccess.application.models;

public class Rate {
    private String activity;
    private int rate;
    private String year;
    private String actorName;
    private long actorId;

    public Rate(){}

    public Rate(String activity, int rate) {
        this.activity = activity;
        this.rate = rate;
    }
    
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
   
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
    
    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }
}
