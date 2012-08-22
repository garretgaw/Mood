package org.gg.mood.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gg.mood.model.MoodEntry;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.GetItemRequest;
import com.amazonaws.services.dynamodb.model.GetItemResult;
import com.amazonaws.services.dynamodb.model.Key;

public class MoodEntryDAOTest {
	
	private MoodEntryDAO dao;
	
	@Before
	public void setUp() {
		dao = new MoodEntryDAO();
	}
    
	@Test
	public void testPersistAndDeleteMoodEntry() {

        try {
            // Put data into a domain
            System.out.println("Putting data into " + MoodEntryDAO.getDomain() + " domain.\n");
            
            MoodEntry savedMoodEntry = createAndPersistSampleData();
            
            // Wait - since Amazon takes a few seconds to update
            Thread.currentThread().sleep(1000);
            // Select dinner from a domain and verify it's there
            GetItemResult result = dao.getDynamoDB().getItem(new GetItemRequest(MoodEntryDAO.getDomain(), new Key(new AttributeValue(savedMoodEntry.getId()))));          
            assertEquals("{S: " + savedMoodEntry.getId() + ", }", result.getItem().get("Id").toString());
            System.out.println("    Name: " + result.getItem().get("Id"));

            dao.deleteMoodEntry(savedMoodEntry);
            
            // Wait - since Amazon takes a few seconds to update
            Thread.currentThread().sleep(1000);
            // Select dinner from a domain and verify it's NOT there
            result = dao.getDynamoDB().getItem(new GetItemRequest(MoodEntryDAO.getDomain(), new Key(new AttributeValue(savedMoodEntry.getId()))));          
            assertNull(result.getItem());         
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
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    /**
     * Creates an array of SimpleDB ReplaceableItems populated with sample data.
     *
     * @return An array of sample item data.
     */
    public MoodEntry createAndPersistSampleData() {
    	
    	MoodEntry me = MoodEntry.createMoodEntry(Double.valueOf(82.1435), Double.valueOf(47.3125), "Angry", "48236");
    	dao.persistMoodEntry(me);
    	
    	return me;
    }

}
