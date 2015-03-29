package com.novar.business;

import java.util.Date;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import com.novar.util.StringUtil;
/** this class describes all what is relative to an Event 
 * 
 * @author othmane El kouahy	
 * This class is used in EventManager,EventJdbc,ActivityEventFacade
 * 
 *  @see EventManager
 *  @see EventJdbc
 *  @see ActivityEventFacade
 *
 */
public abstract class Event {
	private int eventID;
	private String eventName;
	private int eventPrice;
	private int eventDuration;
	private int roomID;
	private int actID;
	private String pseudo;
	private Date eventDate;
	private String type;
	private int occurence;
	private String frequency;



	public Event(HashMap<String,Object> data)
	{
		Class[] typeArg = new Class[1];
		Object[] arg = new Object[1];
		ArrayList<String> errors = new ArrayList<String>();

		for (String mapKey : data.keySet())
		{
			String setterName = "set" + StringUtil.toCapitalizeCase(mapKey);
			typeArg[0] = data.get(mapKey).getClass();
			arg[0] = data.get(mapKey);
			Method setter;
			try {
				setter = this.getClass().getMethod(setterName, typeArg);
				try
				{
					setter.invoke(this, arg);
				}
				catch (Exception e)
				{
					errors.add(e.getCause().getMessage());
				}
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public Event() {
		// TODO Auto-generated constructor stub
	}


	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(Integer eventPrice) {
		this.eventPrice = eventPrice;
	}

	public Integer getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(Integer eventDuration) {
		this.eventDuration = eventDuration;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	public Integer getActID() {
		return actID;
	}

	public void setActID(Integer actID) {
		this.actID = actID;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOccurence() {
		return occurence;
	}

	public void setOccurence(Integer occurence) {
		this.occurence = occurence;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public abstract void load();
	public abstract void save();
	public abstract void saveOneTime();
	public abstract void saveRepetitive();
	public abstract void update(int par);
	public abstract void delete();
}
