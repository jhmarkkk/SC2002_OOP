package services;

import interfaces.services.DataServiceable;

import models.Camp;
import models.Enquiry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.Role;



public class CampDataService implements DataServiceable{
	
    public void exporting (String filePath) {
		// TODO Auto-generated method stub
	}
	
	public void importing (String filePath) {
		// TODO Auto-generated method stub
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

			Map<String, Camp> campDataMap = new HashMap<>();

            line = br.readLine();
            System.out.println(line);
            line = br.readLine();
            System.out.println(line);
            
            String[] fields = line.split(",");
            for(int i = 0; i < fields.length; i++) {
            	System.out.println(fields[i]);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		

	// /**
	//  * 
	//  * @param text 
	//  * @return a Map that takes in enquiryID as key and returns its constructed Enquiry object. 
	//  */

	// private Map<Integer, Enquiry> createEnquiryMap(String text){
	// 	Map<Integer, Enquiry> enquiryMap = new HashMap<>();
		
	// 	String[] fields = text.split(",");
	// 	Integer enquiryID = Integer.valueOf(fields[0]);
	// 	String enquirer = fields[1];
	// 	String enquiry = fields[2];
	// 	String replier = fields[3];
	// 	String reply = fields[4];
		
	// 	enquiryMap.put(enquiryID, new Enquiry(enquiryID, enquirer, enquiry, replier, reply)); 
	// 	return enquiryMap;
		
	// }
	
}
