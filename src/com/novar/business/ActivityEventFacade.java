package com.novar.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.novar.exception.FalseFieldsException;
import com.novar.persist.PersistKit;
import com.novar.business.Event;

/** This class is playing the role of a facade managing Activity Event and Registration
 * 
 * @author othmane El kouahy 
 * it is used in class Facade
 * @see MainFacade
 */
public class ActivityEventFacade {

	private PersistKit kit = null;

	public ActivityEventFacade(PersistKit kit)
	{
		this.kit=kit;
	}
	public ArrayList<Activity> getAllActivities()
	{
		ActivityManager Am = kit.makeActivityManager();
		return Am.getAllActivities();
	}

	public void deleteActivity(Activity act)
	{
		act.delete();
	}
	public Activity createActivity(HashMap<String, Object> mapAct) throws FalseFieldsException
	{
		Activity act = kit.makeActivity(mapAct);
		act.save();
		return act;	

	}
	public Activity updateActivity(HashMap<String, Object> mapAct) throws FalseFieldsException 
	{
		Activity act = kit.makeActivity(mapAct);
		act.update();
		return act;	

	}

	public Event createEvent(HashMap<String, Object> mapEv,int par) throws FalseFieldsException
	{
		Event Ev = kit.makeEvent(mapEv);
		Ev.save();
		if(par==0)
			Ev.saveRepetitive();
		else Ev.saveOneTime();
		return Ev;	


	}
	public ArrayList<Event> getAllEvents(User man)
	{
		EventManager Em = kit.makeEventManager();
		return Em.getAllEvents(man);

	}
	public ArrayList<Event> getAllEvents(Activity act)
	{
		EventManager Em = kit.makeEventManager();
		return Em.getAllEvents(act);

	}

	public ArrayList<Registration> getAllRegistrations(Event Ev)
	{
		RegistrationManager Rm = kit.makeRegistrationManager();
		return Rm.getAllRegistrations(Ev);

	}

	public ArrayList<Registration> getAllRegistrations(User mem)
	{
		RegistrationManager Rm = kit.makeRegistrationManager();
		return Rm.getAllRegistrations(mem);

	}

	public void deleteEvent(Event Ev)
	{
		Ev.delete();
	}

	public void deleteRegistration(Registration Reg)
	{
		Reg.delete();
	}

	public Event updateEvent(HashMap<String, Object> mapEv,int par) throws FalseFieldsException 
	{
		Event Ev = kit.makeEvent(mapEv);
		Ev.update(par);


		return Ev;	

	}

	public Registration createRegistration(HashMap<String, Object> mapReg) throws FalseFieldsException
	{
		Registration reg = kit.makeRegistration(mapReg);
		reg.save();
		return reg;	

	}
	public ArrayList<Event> getAllEvents() {
		EventManager Em = kit.makeEventManager();
		return Em.getAllEvents();
	}
	public Registration createRegistrationMember(HashMap<String, Object> mapReg) throws FalseFieldsException {
		Registration reg = kit.makeRegistrationMember(mapReg);
		reg.saveRegistrationByMember();
		return reg;	

	}


}
