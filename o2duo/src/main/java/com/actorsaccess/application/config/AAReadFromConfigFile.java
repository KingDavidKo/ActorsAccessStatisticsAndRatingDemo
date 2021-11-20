package com.actorsaccess.application.config;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class AAReadFromConfigFile {
	public static HashMap<String, String> conf = null;
	
	public static HashMap<String, String> readConfigFile() {

	  	HashMap<String, String> conf = new HashMap<String, String>();
	  	InputStream is = null;
	  	
	  	try {
	    	is = Thread.currentThread().getContextClassLoader()
	                .getResourceAsStream("Configuration/Configurations.txt");
	        StringBuilder sb = new StringBuilder();
	        
	        if (is != null) {
	        	String[] temp;
	        	BufferedReader reader;
	            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	              sb.append(line + "\n");
	              temp = line.split("=");
	    	      conf.put(temp[0], temp[1]);
	    		  //System.out.println(line);
	            }
	            reader.close();
	  	    }
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        if (is != null) {
              try {
                is.close();
              } catch (IOException e) {
            	// TODO Auto-generated catch block
				e.printStackTrace();
              }
            }
	      }
	  	return conf;
	  }
  
  	public static HashMap<String, String> getConfig(){
  		if (conf == null) {
  			conf = readConfigFile();		
  		}  	
  		return conf;
  	} 
}
 
