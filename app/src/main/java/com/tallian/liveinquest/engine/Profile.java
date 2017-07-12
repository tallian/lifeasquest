package com.tallian.liveinquest.engine;
import java.util.*;
import org.json.*;

public class Profile
{
	private long id;
	private String name;
	private List<Task> mainTasks = new ArrayList<>();
	private List<Task> allTasks = new ArrayList<>();

	public Profile(String name)
	{
		this.name = name;
	}

	public void setAllTasks(List<Task> allTasks)
	{
		this.allTasks = allTasks;
	}

	public List<Task> getAllTasks()
	{
		return allTasks;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}


	public void setMainTasks(List<Task> mainTasks)
	{
		this.mainTasks = mainTasks;
	}

	public List<Task> getMainTasks()
	{
		return mainTasks;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonProfile = new JSONObject();
		jsonProfile.put("name", this.getName());
		
		JSONArray tasks = new JSONArray();
		for (Task task: this.getAllTasks()) {
			tasks.put(task.toJson());
		}
		
		jsonProfile.put("tasks", tasks);
		
		return jsonProfile;
	}

}
