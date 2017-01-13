package com.mkyong.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

		@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public Map<String, Object> getMovie(@RequestBody String apirequest) {

		try {
			JSONObject jsonobj = new JSONObject(apirequest);
			JSONObject temp = jsonobj.getJSONObject("result").getJSONObject("fulfillment");
			JSONArray array = temp.getJSONArray("messages");
			String region = array.getJSONObject(0).getString("speech");

			String cost = "";
			System.out.println("input" + region);
			if (region.equals("Ahmedabad")) {
				cost = "100";
			} else if (region.equals("Vadodara")) {
				cost = "75";
			}

			String speech = "The cost of shipping to " + region + " is " + cost;

			Map<String, Object> tempMap = new HashMap<String, Object>();

			tempMap.put("speech", speech);
			tempMap.put("displayText", speech);
			tempMap.put("data", new HashMap<String, Object>());
			tempMap.put("contextOut", new ArrayList<String>());
			tempMap.put("source", "Project on github");

			return tempMap;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
}
