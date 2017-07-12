package com.tallian.liveinquest.engine;
import java.util.*;
import org.json.*;

public class Task
{
	private long id;
	private String name;
	private Date dueDate;
	private List<Task> previousTasks = new ArrayList<>();
	private List<Task> nextTasks = new ArrayList<>();
	private List<Task> subTasks = new ArrayList<>();

	public Task(String name)
	{
		this.name = name;
	}

	public Task(String name, Date dueDate, List<Task> previousTasks, List<Task> nextTasks, List<Task> subTasks)
	{
		this.name = name;
		this.dueDate = dueDate;
		this.previousTasks = previousTasks;
		this.nextTasks = nextTasks;
		this.subTasks = subTasks;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setSubTasks(List<Task> subTasks)
	{
		this.subTasks = subTasks;
	}

	public List<Task> getSubTasks()
	{
		return subTasks;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNextTasks(List<Task> nextTasks)
	{
		this.nextTasks = nextTasks;
	}

	public List<Task> getNextTasks()
	{
		return nextTasks;
	}

	public void setPreviousTasks(List<Task> previousTasks)
	{
		this.previousTasks = previousTasks;
	}

	public List<Task> getPreviousTasks()
	{
		return previousTasks;
	}


	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonTask = new JSONObject();
		jsonTask.put("name", this.getName());
		
		JSONArray tasks = new JSONArray();
		for (Task task: this.getSubTasks()) {
			tasks.put(task.getId());
		}

		jsonTask.put("tasks", tasks);

		return jsonTask;
	}
}
