package com.actorsaccess.application.models;

import java.sql.Timestamp;

public class Actor {
	//recordID, actorID, userFirstName, userLastName, userEmail, type, project, role, activityDate, creationDate, agentEmail, agentName, days
	private long id;
	private long actorId;
    private String actorFirstName;
    private String actorLastName;
    private String actorEmail;
    private String type;
    private String project;
    private String role;    
    private Timestamp activityDate;
    private Timestamp creationDate;
    private String agentName;
    private String agentEmail;
    private int days;

    public Actor(){}

    public Actor(long id, long actorId, String actorFirstName, String actorLastName, String actorEmail, String type, String project, String role, Timestamp activityDate, Timestamp creationDate, String agentEmail, String agentName, int days) {
        this.id = id;
        this.actorId = actorId;
        this.actorFirstName = actorFirstName;
        this.actorLastName = actorLastName;
        this.actorEmail = actorEmail;
        this.type = type;
        this.project = project;
        this.role = role;
        this.activityDate = activityDate;
        this.creationDate = creationDate;
        this.agentName = agentName;
        this.agentEmail = agentEmail;
        this.days = days;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    public String getActorFirstName() {
        return actorFirstName;
    }

    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }
    
    public String getActorLastName() {
        return actorLastName;
    }

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }

    public String getActorEmail() {
        return actorEmail;
    }

    public void setActorEmail(String actorEmail) {
        this.actorEmail = actorEmail;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public Timestamp getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Timestamp activityDate) {
        this.activityDate = activityDate;
    }
    
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
    
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentEmail() {
        return agentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor user = (Actor) o;

        return getId() == user.getId();
    }

}
