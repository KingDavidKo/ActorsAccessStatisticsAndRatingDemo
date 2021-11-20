package com.actorsaccess.application.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.actorsaccess.application.config.AAConfig;
import com.actorsaccess.application.config.AAReadFromConfigFile;
import com.actorsaccess.application.models.Actor;
import com.actorsaccess.application.models.Rate;
import com.actorsaccess.application.persistence.AADatabaseService;
import com.actorsaccess.application.services.common.SendEmailNotification;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RatingController {
	AADatabaseService dbService;

    public RatingController() {
    	dbService = new AADatabaseService();
    }

    @RequestMapping(method = RequestMethod.GET,value="/actors")
    public @ResponseBody ResponseEntity<List<Actor>> getActors() {
    	List<Actor> actors = new ArrayList<Actor>();
		try {
			dbService.getActorsRateStatistics(actors);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (actors.size() == 0) {
			 return new ResponseEntity("Actors Not Found", HttpStatus.NOT_FOUND);
		 }	    
		 return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
    }

	@GetMapping("/rating")
	public String rating(@RequestParam(name="actorId", required=false, defaultValue="0") long actorId, 
			@RequestParam(name="rate", required=false, defaultValue="ActorRate") String rate,
			Model model) {
		model.addAttribute("actorId", actorId);
		rate = "";
		if(actorId > 0) {
		List<Rate> rates = new ArrayList<Rate>();
		try {
			dbService.getActorRateByActorId(actorId, rates);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rate rateRec;
		for(int i = 0; i < rates.size(); i++) {
			rateRec = rates.get(i);
			rate = rate + rateRec.getActivity() + ":" + rateRec.getRate() + ",";
		}
	}
		model.addAttribute("rate", rate);
		return "rating";
	}
	
	@GetMapping("/performance")
	public String performance(@RequestParam(name="actorId", required=false, defaultValue="0") long actorId, 
			@RequestParam(name="perform", required=false, defaultValue="ActorPerformance") String perform,
			Model model) {
		model.addAttribute("actorId", actorId);
		perform = "";
		if(actorId > 0) {
		List<Rate> performances = new ArrayList<Rate>();
		try {
			dbService.getActorPerformanceByActorId(actorId, performances);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rate perfRec;
		for(int i = 0; i < performances.size(); i++) {
			perfRec = performances.get(i);
			perform = perform + perfRec.getActivity() + ":" + perfRec.getRate() + ":" + perfRec.getYear() + ",";
		}
		}
		model.addAttribute("perform", perform);
		return "performance";
	}
	
	@GetMapping("/statistics")
	public String statistics(@RequestParam(name="actorId", required=false, defaultValue="0") long actorId, 
			@RequestParam(name="statistic", required=false, defaultValue="ActorStatistics") String statistic,
			Model model) {
		model.addAttribute("actorId", actorId);
		statistic = "";
		if(actorId > 0) {
		List<Rate> statisticList = new ArrayList<Rate>();
		try {
			dbService.getActorSuccessStatisticsByActorId(actorId, statisticList);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rate rateRec;
		for(int i = 0; i < statisticList.size(); i++) {
			rateRec = statisticList.get(i);
			statistic = statistic + rateRec.getYear() + ":" + rateRec.getActivity() + ",";
		}
	}
		model.addAttribute("statistic", statistic);
		return "statistics";
	}
	
	@GetMapping(value="/actor/{actorId}")
	public ResponseEntity getActorById(@PathVariable("actorId") long actorId){
		List<Actor> actors = new ArrayList<Actor>();
		try {
			dbService.getActorRateStatisticsByActorId(actorId, actors);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (actors.size() == 0) {
				return new ResponseEntity("Actor Not Found", HttpStatus.NOT_FOUND);
			}	    
		    return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
	   
	}

	@GetMapping(value="/actor", params = "lastName")
	public ResponseEntity getUsersByName(@RequestParam(value="lastName") String lastName){
		List<Actor> actors = new ArrayList<Actor>();
		try {
			dbService.getActorRateStatisticsByLastName(lastName, actors);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (actors.size() == 0) {
				return new ResponseEntity("Actor Not Found", HttpStatus.NOT_FOUND);
			}	    
		 return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
	}

	@PostMapping(value="/actor")
	public ResponseEntity add(@RequestBody Actor a) {
		if (a.getActorId() < 1  || a.getType() == null || a.getProject() == null ||
				a.getRole() == null || a.getActivityDate() == null) {
			return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		int count = 0;
		try {
			count = dbService.insertActorRateStatistics(a);
			
			if (count == 0) {
				return new ResponseEntity("Actor Not Created", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			HashMap<String, String> conf = AAReadFromConfigFile.getConfig();
			SendEmailNotification.sendEmailMessage(conf.get("fromAddress"), conf.get("toAddress") + "," + a.getActorEmail() + "," + a.getAgentEmail(), AAConfig.SUBJECT_ADD, 
				AAConfig.getEmailMessageAdd(a), /*mime*/null, AAConfig.DEFAULT_ENCODING, /*bcc*/null, /*attachInfoArr*/null, 
				conf.get("USER"), conf.get("PWD"), conf.get("HOST"), conf.get("PORT"), conf.get("SSL_FLAG"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == 0) {
			return new ResponseEntity("Record Not Created", HttpStatus.NOT_FOUND);
		}
	    return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping(value="/actor")
	public ResponseEntity addOrUpdate(@RequestBody Actor a) {
		if (a.getActorId() < 1  || a.getType() == null || a.getProject() == null ||
				a.getRole() == null || a.getActivityDate() == null) {
			return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		
		int count = 0;
	
	    try {
			count = dbService.updateActorRateStatistics(a);
			if (count == 0) {
				return new ResponseEntity("Actor Not Found", HttpStatus.NOT_FOUND);
			}
			HashMap<String, String> conf = AAReadFromConfigFile.getConfig();
			SendEmailNotification.sendEmailMessage(conf.get("fromAddress"), conf.get("toAddress") + "," + a.getActorEmail() + "," + a.getAgentEmail(), AAConfig.SUBJECT_UPDATE, 
				AAConfig.getEmailMessageUpdate(a), /*mime*/null, AAConfig.DEFAULT_ENCODING, /*bcc*/null, /*attachInfoArr*/null, 
				conf.get("USER"), conf.get("PWD"), conf.get("HOST"), conf.get("PORT"), conf.get("SSL_FLAG"));
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if (count == 0) {
			return new ResponseEntity("Record Not Updated", HttpStatus.NOT_FOUND);
		}
	    return new ResponseEntity(a.getId(), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/actor/{id}")
	public ResponseEntity delete(@PathVariable("id") long id) {
		if (id < 1) {
			return new ResponseEntity("Invalid Request", HttpStatus.BAD_REQUEST);
		}
		int count = 0;
		try {
			count = dbService.deleteActorRateStatistics(id);
		
			if (count == 0) {
				return new ResponseEntity("Actor Not Found", HttpStatus.NOT_FOUND);
			}
			HashMap<String, String> conf = AAReadFromConfigFile.getConfig();
			SendEmailNotification.sendEmailMessage(conf.get("fromAddress"), conf.get("toAddress"), AAConfig.SUBJECT_DELETE, 
				AAConfig.getEmailMessageDelete(id), /*mime*/null, AAConfig.DEFAULT_ENCODING, /*bcc*/null, /*attachInfoArr*/null, 
				conf.get("USER"), conf.get("PWD"), conf.get("HOST"), conf.get("PORT"), conf.get("SSL_FLAG"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == 0) {
			return new ResponseEntity("Record Not Deleted", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);
	}
	
	@GetMapping("/dashboard")
	public String dashboard(@RequestParam(name="dashb", required=false, defaultValue="ActorsDashboard") String dashb, Model model) {
		dashb = "";
		List<Rate> dashboardList = new ArrayList<Rate>();
		try {
			dbService.getActorsDashboard(dashboardList);
			} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Rate rateRec;
		for(int i = 0; i < dashboardList.size(); i++) {
			rateRec = dashboardList.get(i);
			dashb = dashb + rateRec.getActorId() + ":" + rateRec.getActorName() + ":" + rateRec.getRate() + ",";
		}
		model.addAttribute("dashb", dashb);
		return "dashboard";
	}
}
