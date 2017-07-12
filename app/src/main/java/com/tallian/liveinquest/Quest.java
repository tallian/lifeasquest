package com.tallian.liveinquest;
import android.app.*;
import android.content.*;
import com.tallian.liveinquest.engine.*;
import java.io.*;
import com.tallian.liveinquest.utils.*;
import android.util.*;
import org.json.*;

public class Quest extends Application {
	private Profile profile;
	private String defaultProfileName = "test";
	
	public FileUtils fileUtils = new FileUtils(this);

	public void setDefaultProfileName(String defaultProfileName)
	{
		this.defaultProfileName = defaultProfileName;
	}

	public String getDefaultProfileName()
	{
		return defaultProfileName;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		if (profile != null) {
			return profile;
		} else {
			try {
				readProfile();
			} catch (JSONException e) {}
			if (profile == null) {
				createProfile();
			} 
		}
		return profile;
	}
	
	public Profile createProfile() {
		profile = new Profile("test");
		this.setProfile(profile);
		
		return profile;
	}
	
	public Profile readProfile() throws JSONException {
		String textProfile = fileUtils.readFile(defaultProfileName);
		if (textProfile == null) {
			fileUtils.createFile(defaultProfileName);
			profile = new Profile(defaultProfileName);
		//	profile.setName(defaultProfileName);
		} else {
			JSONObject jProfile = new JSONObject(textProfile);
			
			profile = new Profile(defaultProfileName);
		//	profile.setName(defaultProfileName);
			
			JSONArray array = (JSONArray) jProfile.get("tasks");
			
			for (int i = 0; i < array.length(); i++) {
				JSONObject jObj = (JSONObject) array.get(i);
				Task task = new Task((String) jObj.get("name"));
				profile.getAllTasks().add(task);
				profile.getMainTasks().add(task);
			}
		}
		return profile;
	}
	
	public Boolean saveProfile() {
		try {
			fileUtils.removeFile(profile.getName());
			fileUtils.writeToFile(profile.toJson().toString(), profile.getName());
			return true;
		}
		catch (JSONException e)	{
			return false;
		}
	}
}
