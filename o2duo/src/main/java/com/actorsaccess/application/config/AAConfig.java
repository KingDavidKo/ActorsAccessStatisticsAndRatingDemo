package com.actorsaccess.application.config;

import com.actorsaccess.application.models.Actor;

public final class AAConfig {
	public static final String SUBJECT_ADD = "Actor Rate Activity - Record Added";
	public static final String SUBJECT_UPDATE = "Actor Rate Activity - Record Updated";
	public static final String SUBJECT_DELETE = "Actor Rate Activity - Record Deleted";
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	public static String getEmailMessageAdd(Actor actor) {
		String emailMessage = "<!DOCTYPE html><html><head><title>Actor's Rating and Statistics Update</title></head><body><h1>Actor's Rating and Statistics Record Added</h1><p>" + 
				  "<p style=\"font-size:25px;\">Actor: " + actor.getActorFirstName() + " " + actor.getActorLastName() + "</p>" + 
				  "<p style=\"font-size:25px;\">Actor Id: " + actor.getActorId() + "</p>" +
				  "<p style=\"font-size:25px;\">Completed: " + actor.getType() + "</p>" +
				  "<p style=\"font-size:25px;\">Project: " + actor.getProject() + "</p>" +
				  "<p style=\"font-size:25px;\">Role: " + actor.getRole() + "</p>" +
				  "<p style=\"font-size:25px;\">Days: " + actor.getDays() + "</p>" +
				  "<p style=\"font-size:25px;\">Activity Date: " + actor.getActivityDate() + "</p>" +
				  "<p style=\"font-size:25px;\">Agent Name: " + actor.getAgentName() + "</p>" + 
				  "<p>Get actor's rating <a href=\"http://localhost:8080/o2duo/rating?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" + 
				  "<p>Get actor's performance <a href=\"http://localhost:8080/o2duo/performance?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" +
				  "<p>Get actor's statistics <a href=\"http://localhost:8080/o2duo/statistics?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" +
				  "</body></html>";
		return emailMessage;
	}

	public static String getEmailMessageUpdate(Actor actor) {
		String emailMessage = "<!DOCTYPE html><html><head><title>Actor's Rating and Statistics Update</title></head><body><h1>Actor's Rating and Statistics Record Updated</h1><p>" + 
				  "<p style=\"font-size:25px;\">Actor: " + actor.getActorFirstName() + " " + actor.getActorLastName() + "</p>" +
				  "<p style=\"font-size:25px;\">Actor Id: " + actor.getActorId() + "</p>" +
				  "<p style=\"font-size:25px;\">Completed: " + actor.getType() + "</p>" +
				  "<p style=\"font-size:25px;\">Project: " + actor.getProject() + "</p>" +
				  "<p style=\"font-size:25px;\">Role: " + actor.getRole() + "</p>" +
				  "<p style=\"font-size:25px;\">Days: " + actor.getDays() + "</p>" +
				  "<p style=\"font-size:25px;\">Activity Date: " + actor.getActivityDate() + "</p>" + 
				  "<p style=\"font-size:25px;\">Agent Name: " + actor.getAgentName() + "</p>" + 
				  "<p>Get actor's rating and statistics <a href=\"http://localhost:8080/o2duo/rating?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" + 
				  "<p>Get actor's performance <a href=\"http://localhost:8080/o2duo/performance?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" +
				  "<p>Get actor's statistics <a href=\"http://localhost:8080/o2duo/statistics?actorId=" + actor.getActorId() + "\">here</a>.</p>\n" +
				  "</body></html>";
		return emailMessage;
	}
	
	public static String getEmailMessageDelete(long id) {
		String emailMessage = "<!DOCTYPE html><html><head><title>Actor's Rating and Statistics Update</title></head><body><h1>Actor's Rating and Statistics Record Deleted</h1><p>" + 
				  "<p style=\"font-size:25px;\">Your rating and statistics record with ID " + id + " has been deleted.</p>" +
				  "</body></html>";
		return emailMessage;
	}
}
