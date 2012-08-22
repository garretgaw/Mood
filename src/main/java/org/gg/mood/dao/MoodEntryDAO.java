package org.gg.mood.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gg.mood.model.MoodEntry;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodb.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodb.model.AttributeValue;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.Key;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.ScanRequest;
import com.amazonaws.services.dynamodb.model.ScanResult;

public class MoodEntryDAO {
	
	static AmazonDynamoDBClient ddb;
	private static String domain = "Mood";
	
	public static String getDomain() {
		return domain;
	}
	
	public MoodEntryDAO() {
		
        try {
        	AWSCredentials credentials = new PropertiesCredentials(
                    MoodEntryDAO.class.getResourceAsStream("AwsCredentials.properties"));

            ddb = new AmazonDynamoDBClient(credentials);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	public AmazonDynamoDBClient getDynamoDB() {
		return ddb;
	}

    public void persistMoodEntry(MoodEntry me) {
    	
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("Id", new AttributeValue(me.getId()));
        item.put("Latitude", new AttributeValue().withN(me.getLatitude().toString()));
        item.put("Longitude", new AttributeValue().withN(me.getLongitude().toString()));
        item.put("Mood", new AttributeValue(me.getMood()));
        item.put("Zipcode", new AttributeValue(me.getZipcode()));
        item.put("Timestamp", new AttributeValue().withN(me.getTimestamp().toString()));

        PutItemRequest putItemRequest = new PutItemRequest(domain, item);
        ddb.putItem(putItemRequest);
    }
    
    public void deleteMoodEntry(MoodEntry d) {
    	System.out.println("Deleting data from " + domain + " domain.\n"); 	   	
        ddb.deleteItem(new DeleteItemRequest(domain, new Key(new AttributeValue(d.getId()))));	
    }
    
    public void deleteMoodEntry(String id) {
    	System.out.println("Deleting data from " + domain + " domain.\n"); 	   	
        ddb.deleteItem(new DeleteItemRequest(domain, new Key(new AttributeValue(id))));	
    }

    public List getAllData() {
/*			 // Scan items for movies with a year attribute greater than 1985
        HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
        Condition condition = new Condition()
            .withComparisonOperator(ComparisonOperator.GT.toString())
            .withAttributeValueList(new AttributeValue().withN("1985"));
        scanFilter.put("year", condition);
*/            
//		ScanRequest scanRequest = new ScanRequest(MoodEntryDAO.getDomain()).withScanFilter(scanFilter);
		ScanRequest scanRequest = new ScanRequest(domain);
        ScanResult scanResult = ddb.scan(scanRequest);
        return composeMoodsFromList(scanResult.getItems());
    }
    
    protected List composeMoodsFromList(List data) {
    	ArrayList coordList = new ArrayList();
		
		for (int i=0; i < data.size(); i++) {
			Map aMap = (Map)data.get(i);
			MoodEntry aMood = new MoodEntry(((AttributeValue)aMap.get("Id")).getS(), Double.valueOf(((AttributeValue)aMap.get("Latitude")).getN()), Double.valueOf(((AttributeValue)aMap.get("Longitude")).getN()), ((AttributeValue)aMap.get("Mood")).getS(), ((AttributeValue)aMap.get("Zipcode")).getS(), Long.valueOf(((AttributeValue)aMap.get("Timestamp")).getN()));
			coordList.add(aMood);
		}
		
		return coordList;
    }
   
}

/**
		SNIPPETS
		
		
	            // List domains
	            System.out.println("Listing all domains in your account:\n");
	            for (String domainName : sdb.listDomains().getDomainNames()) {
	                System.out.println("  " + domainName);
	            }
	            System.out.println();
	            
	            // Delete values from an attribute
	            System.out.println("Deleting Blue attributes in Item_O3.\n");
	            Attribute deleteValueAttribute = new Attribute("Color", "Blue");
	            sdb.deleteAttributes(new DeleteAttributesRequest(myDomain, "Item_03")
	                    .withAttributes(deleteValueAttribute));

	            // Delete an attribute and all of its values
	            System.out.println("Deleting attribute Year in Item_O3.\n");
	            sdb.deleteAttributes(new DeleteAttributesRequest(myDomain, "Item_03")
	                    .withAttributes(new Attribute().withName("Year")));

	            // Replace an attribute
	            System.out.println("Replacing Size of Item_03 with Medium.\n");
	            List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
	            replaceableAttributes.add(new ReplaceableAttribute("Size", "Medium", true));
	            sdb.putAttributes(new PutAttributesRequest(myDomain, "Item_03", replaceableAttributes));

	            // Delete an item and all of its attributes
	            System.out.println("Deleting Item_03.\n");
	            sdb.deleteAttributes(new DeleteAttributesRequest(myDomain, "Item_03"));

	            // Delete a domain
	            System.out.println("Deleting " + myDomain + " domain.\n");
	            sdb.deleteDomain(new DeleteDomainRequest(myDomain));

	        **/
