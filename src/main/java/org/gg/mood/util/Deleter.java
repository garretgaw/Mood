package org.gg.mood.util;

import org.gg.mood.dao.MoodEntryDAO;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

public class Deleter {

	private static final String id = "c1da4569-cac1-4a14-aa18-0ff0d6e5b37e";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
        	MoodEntryDAO dao = new MoodEntryDAO();     	
        	dao.deleteMoodEntry(id);         
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon SimpleDB, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with SimpleDB, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        
        System.out.println("Deleted id:" + id);

	}

}
