package org.gg.mood.model;

import java.util.UUID;

public class MoodEntry {

	private String id;
	private Double latitude;
	private Double longitude;
	private String mood;
	private String zipcode;
	private Long timestamp;
	
	public MoodEntry(String id, Double latitude, Double longitude, String mood, String zipcode, Long timestamp) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mood = mood;
		this.zipcode = zipcode;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getMood() {
		return mood;
	}

	public String getZipcode() {
		return zipcode;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public static MoodEntry createMoodEntry(Double latitude, Double longitude, String mood, String zipcode) {    	
    	MoodEntry me = new MoodEntry(UUID.randomUUID().toString(), latitude, longitude, mood, zipcode, System.currentTimeMillis());
    	return me;
    }
	
}
