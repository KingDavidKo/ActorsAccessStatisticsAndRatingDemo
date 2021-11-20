package com.actorsaccess.application.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.actorsaccess.application.models.Actor;
import com.actorsaccess.application.models.Rate;

public class AADatabaseService {
	Connection conn = null;
	AADatabaseConnectionOracle dbAccess = null;
	int readRecords;
	
	 
	public AADatabaseService() {
		dbAccess = new AADatabaseConnectionOracle();
	}
	
	public int insertActorRateStatistics(Actor actor) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	          throw new SQLException();
	        
	        Statement stmt = conn.createStatement();
			String query = "INSERT INTO ACTOR_RATE_STATISTICS (id, actorID, actorFirstName, actorLastName, actorEmail, type, project, role, activityDate, creationDate, days, agentName, agentEmail) " +
			"VALUES (seq_actor.nextval, '" + 
					actor.getActorId() + "','" +
					actor.getActorFirstName() + "','" +
					actor.getActorLastName() + "','" + 
					actor.getActorEmail() + "','" + 
					actor.getType() + "','" + 
					actor.getProject() + "','" + 
					actor.getRole() + "', TO_TIMESTAMP('" + 
					actor.getActivityDate() + "','YYYY-MM-DD HH24:MI:SS.FF1'), " +
					"sysdate ," + 
					actor.getDays() + ",'" +
					actor.getAgentName() + "','" +
					actor.getAgentEmail() + "')";

			int count = stmt.executeUpdate(query);
			stmt.close();
	        conn = null;
	        return count;
	}

	public int updateActorRateStatistics(Actor actor) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
	       
	        Statement stmt = conn.createStatement();
	        
			String query = "UPDATE ACTOR_RATE_STATISTICS " +
				"SET actorId = '" + actor.getActorId() + "', " + 
				"actorFirstName = '" + actor.getActorFirstName() + "', " +
				"actorLastName = '" + actor.getActorLastName() + "', " +
				"actorEmail = '" + actor.getActorEmail() + "', " +
				"type = '" + actor.getType() + "', " +
				"project = '" + actor.getProject() + "', " +
				"role = '" + actor.getRole() + "', " + 
				"activityDate = TO_TIMESTAMP('" + actor.getActivityDate() + "','YYYY-MM-DD HH24:MI:SS.FF1'), " +
				"creationDate = sysdate, " +
				"days = " + actor.getDays() + ", " +
				"agentName = '" + actor.getAgentName() + "', " +
				"agentEmail = '" + actor.getAgentEmail() + "'" +
				" WHERE ID = '" + actor.getId() + "'";
			
			int count = stmt.executeUpdate(query);
			stmt.close();
	        conn = null;
	        return count;
	}
	
	public int deleteActorRateStatistics(long id) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
	        
	        Statement stmt = conn.createStatement();
	        
			String query = "DELETE FROM ACTOR_RATE_STATISTICS WHERE ID = '" + id + "'";
			
			int count = stmt.executeUpdate(query);
			stmt.close();
	        conn = null;
	        return count;
	}
	
	public void getActorRateStatisticsByActorId(long actorId, List<Actor> actors) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
		Actor a;
		final PreparedStatement s = conn.prepareStatement("SELECT * FROM ACTOR_RATE_STATISTICS WHERE ACTORID = '" + actorId + "'");
	      
	      final ResultSet r = s.executeQuery();
	      
	      while (getNextRecord(r)) {
	    	  a = readActorData(r);
	          actors.add(a);
	      }
	      s.close();
    	  r.close();
	      conn = null;
	}
	
	public void getActorRateStatisticsByLastName(String lastName, List<Actor> actors) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
		
		Actor a;
		final PreparedStatement s = conn.prepareStatement("SELECT * FROM ACTOR_RATE_STATISTICS WHERE ACTORLASTNAME = '" + lastName + "'");
	    final ResultSet r = s.executeQuery();
	      
	      while (getNextRecord(r)) {
	    	  a = readActorData(r);
	          actors.add(a);
	      }
	      s.close();
    	  r.close();
	        conn = null;
	}
	
	public void getActorsRateStatistics(List<Actor> actors) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
		      final PreparedStatement s = conn.prepareStatement("SELECT * FROM ACTOR_RATE_STATISTICS");
		      
		      final ResultSet r = s.executeQuery();
		      
		      while (getNextRecord(r)) {
		    	  final Actor a = readActorData(r);
		          actors.add(a);
		      }
		      s.close();
	    	  r.close();
	        conn = null;
	}
	
	private Actor readActorData(ResultSet r) throws SQLException {
		Actor a = new Actor();
		a.setId(r.getLong("ID"));
		a.setActorId(r.getLong("ACTORID"));
		a.setActorFirstName(r.getString("ACTORFIRSTNAME"));
		a.setActorLastName(r.getString("ACTORLASTNAME"));
		a.setActorEmail(r.getString("ACTOREMAIL"));
		a.setCreationDate(r.getTimestamp("CREATIONDATE"));
		a.setType(r.getString("TYPE"));
		a.setActivityDate(r.getTimestamp("ACTIVITYDATE"));
		a.setProject(r.getString("PROJECT"));
		a.setRole(r.getString("ROLE"));
		a.setAgentEmail(r.getString("AGENTEMAIL"));	
		a.setAgentName(r.getString("AGENTNAME"));;
		return a;
	}
	
	private Rate readRateData(ResultSet r) throws SQLException {
		Rate rateRec = new Rate();
		rateRec.setActivity(r.getString("TYPE"));
		rateRec.setRate(r.getInt("COUNT"));
		return rateRec;
	}
	
	private Rate readPerformanceData(ResultSet r) throws SQLException {
		Rate rateRec = new Rate();
		rateRec.setActivity(r.getString("TYPE"));
		rateRec.setRate(r.getInt("COUNT"));
		rateRec.setYear(r.getString("YEAR"));
		return rateRec;
	}
	
	private Rate readStatisticsData(ResultSet r) throws SQLException {
		Rate rateRec = new Rate();
		rateRec.setActivity(r.getString("TYPE"));
		rateRec.setYear(r.getString("YEAR"));
		return rateRec;
	}
	
	private Rate readDashboardData(ResultSet r) throws SQLException {
		Rate rateRec = new Rate();
		rateRec.setActorId(r.getLong("ACTORID"));
		rateRec.setActorName(r.getString("ACTORFIRSTNAME") + " " + r.getString("ACTORLASTNAME"));
		rateRec.setRate(r.getInt("COUNT"));
		return rateRec;
	}
	
	public boolean getNextRecord(ResultSet r) throws SQLException {
	    final boolean next = r.next();
	    if (next)
	      readRecords++;
	    return next;
	  }
	
	public void getActorRateByActorId(long actorId, List<Rate> rates) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
	        
		Rate rate;
		final PreparedStatement s = conn.prepareStatement("SELECT TYPE, count(*) as COUNT FROM ACTOR_RATE_STATISTICS WHERE ACTORID = '" + actorId + "' and TYPE != 'Cancellation' group by TYPE");
	      
	      final ResultSet r = s.executeQuery();
	    
        float audition = 0;
        float selfTape = 0;
        float recall = 0;
        float onHold = 0;
        float booking = 0;
	      
	      while (getNextRecord(r)) {
	    	  rate = readRateData(r);
	    	  if("Audition".equals(rate.getActivity())) {
	    		  audition = rate.getRate();
	    	  } else if("SelfTape".equals(rate.getActivity())) {
	    		  selfTape = rate.getRate();
	    	  } else if("Recall".equals(rate.getActivity())) {
	    		  recall = rate.getRate();
	    		  rates.add(rate);
	    	  } else if("OnHold".equals(rate.getActivity())) {
	    		  onHold = rate.getRate();
	    		  rates.add(rate);
	    	  } else if("Booking".equals(rate.getActivity())) {
	    		  booking = rate.getRate();
	    		  rates.add(rate);
	    	  }
	      }

	      float ratio;
	      float totalAuditions = audition + selfTape;
	      for (int i = 0; i < rates.size(); i++) {
	    	  rate = (Rate) rates.get(i);
	    	  if("Recall".equals(rate.getActivity())) {
	    		  ratio = recall/totalAuditions*100;
	    		  rate.setRate(Math.round(ratio));
	    	  } else if("OnHold".equals(rate.getActivity())) {
	    		  ratio = onHold/totalAuditions*100;
	    		  rate.setRate(Math.round(ratio));
	    		  rate.setActivity("On Hold");
	    	  } else if("Booking".equals(rate.getActivity())) {
	    		  ratio = booking/totalAuditions*100;
	    		  rate.setRate(Math.round(ratio));
	    	  }
	      }
	      s.close();
    	  r.close();
	      conn = null;
	}
	
	public void getActorPerformanceByActorId(long actorId, List<Rate> performances) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
	        
		Rate rate;
		final PreparedStatement s = conn.prepareStatement("SELECT EXTRACT(YEAR FROM ACTIVITYDATE) as YEAR, TYPE, count(*) as COUNT FROM ACTOR_RATE_STATISTICS WHERE ACTORID = '" + actorId + "' group by EXTRACT(YEAR FROM ACTIVITYDATE), TYPE");
	    final ResultSet r = s.executeQuery();
	      
	      while (getNextRecord(r)) {
	    	  rate = readPerformanceData(r);
	    	  performances.add(rate);
	      }
	      s.close();
    	  r.close();
	      conn = null;
	}
	
	public void getActorSuccessStatisticsByActorId(long actorId, List<Rate> rates) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
		Rate rate;
		final PreparedStatement s = conn.prepareStatement("SELECT TYPE, EXTRACT(YEAR FROM ACTIVITYDATE) as YEAR FROM ACTOR_RATE_STATISTICS WHERE ACTORID = '" + actorId + "' and TYPE != 'OnHold' and TYPE != 'Cancellation'");
	      
	      final ResultSet r = s.executeQuery();
	      
	      while (getNextRecord(r)) {
	    	  rate = readStatisticsData(r);
	    	  rates.add(rate);
	      }
	      s.close();
    	  r.close();
	      conn = null;
	}
	
	public void getActorsDashboard(List<Rate> dashboardList) throws SQLException {
		conn = dbAccess.getDBConnection();
		if (conn == null)
	        throw new SQLException();
	        
		Rate rate;
		final PreparedStatement s = conn.prepareStatement("SELECT ACTORID, ACTORFIRSTNAME, ACTORLASTNAME, count(*) as COUNT FROM ACTOR_RATE_STATISTICS WHERE TYPE != 'Cancellation' group by ACTORID, ACTORFIRSTNAME, ACTORLASTNAME");
	      
        final ResultSet r = s.executeQuery();
	      
        while (getNextRecord(r)) {
          rate = readDashboardData(r);
          dashboardList.add(rate);
	    }
	    s.close();
    	r.close();
	    conn = null;
	}
}
