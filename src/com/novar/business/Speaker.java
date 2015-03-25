package com.novar.business;

public class Speaker implements Role
{
	private String shortDescription;
	private String detailedDescription;
	
	/**
	 * @param shortDescription
	 * @param detailedDescription
	 */
	public Speaker(String shortDescription, String detailedDescription)
	{
		super();
		this.shortDescription = shortDescription;
		this.detailedDescription = detailedDescription;
	}
	
	public String getShortDescription()
	{
		return shortDescription;
	}
	
	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}
	
	public String getDetailedDescription()
	{
		return detailedDescription;
	}
	
	public void setDetailedDescription(String detailedDescription)
	{
		this.detailedDescription = detailedDescription;
	}
	
	public String toString()
	{
		return ("Speaker");
	}
}
