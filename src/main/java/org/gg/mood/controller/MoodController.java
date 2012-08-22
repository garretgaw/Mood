package org.gg.mood.controller;

import java.util.ArrayList;
import java.util.List;

import org.gg.mood.dao.MoodEntryDAO;
import org.gg.mood.model.MoodEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mood")

public class MoodController {

		@RequestMapping(value="/add", method = RequestMethod.POST)
		public ModelAndView addMood(@RequestParam("mood") String mood, @RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {
			
	        if (!mood.isEmpty()) {
	        	MoodEntryDAO dao = new MoodEntryDAO();
	        	
	        	
	        	MoodEntry entry = MoodEntry.createMoodEntry(Double.valueOf(latitude), Double.valueOf(longitude), mood, "48236");
	        	dao.persistMoodEntry(entry);

	        	ModelAndView mav = pullDefaultData();
	        	        	
	        	mav.setViewName("thankyou");
	            return mav;
	        }
	        
	        ModelAndView mav = new ModelAndView();
	        mav.setViewName("invalid");
	        return mav;
	    }
		
		protected ModelAndView pullDefaultData() {
			
			MoodEntryDAO mdao = new MoodEntryDAO();
			
			List moods = mdao.getAllData();
						
			ModelAndView mav = new ModelAndView();
			mav.addObject("moodList", moods);
			
			return mav;
		}
		 
}
