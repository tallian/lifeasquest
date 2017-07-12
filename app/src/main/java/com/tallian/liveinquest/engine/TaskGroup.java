package com.tallian.liveinquest.engine;

public class TaskGroup
{
	private long id;
	private String name;
	private String description;

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDescription()
	{
		return description;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}


	public void setId(long id)
	{
		this.id = id;
	}

	public long getId()
	{
		return id;
	}
}
