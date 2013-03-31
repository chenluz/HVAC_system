package userHVAC.controller;

import java.util.ArrayList;





import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import userHVAC.databean.ScheduleBean;
import userHVAC.databean.UserBean;
import userHVAC.model.ScheduleDAO;
import userHVAC.model.Model;

public class MyhomeAction extends Action {
	private ScheduleDAO scheduleDAO;

	public MyhomeAction(Model model) {
		scheduleDAO = model.getScheduleDAO();
	}

	public String getName() { return "myhome.do"; }
    
    public String perform(HttpServletRequest request) { 
    	 	List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
    	try {
	        int userpp = ((UserBean) request.getSession().getAttribute("user")).getId();
	        ScheduleBean[]schedule = scheduleDAO.getUserSchedule(userpp);
	        request.setAttribute("schedule", schedule);
	        ArrayList<ScheduleBean> scheduleMo = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleTu = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleWe = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleTh = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleFr = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleSa = new ArrayList<ScheduleBean>();
	        ArrayList<ScheduleBean> scheduleSu = new ArrayList<ScheduleBean>();
	        ArrayList<Double> temperaturelist = new ArrayList<Double>();

	        int length = schedule.length;
  	        for (int i=0; i<length; i++) {
  	        	temperaturelist.add(schedule[i].getTemperature());
  	        	
  	        	if(schedule[i].getWeek().equals("MONDAY")){ 
  	        		scheduleMo.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("TUESDAY")){ 
  	        		scheduleTu.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("WENDESDAY")){ 
  	        		scheduleWe.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("THURSDAY")){ 
  	        		scheduleTh.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("FRIDAY")){ 
  	        		scheduleFr.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("SATURDAY")){ 
  	        		scheduleSa.add(schedule[i]); 
  	        	}
  	        	if(schedule[i].getWeek().equals("SUNDAY")){ 
  	        		scheduleSu.add(schedule[i]); 
  	        	}
  	        }
       		request.setAttribute("scheduleMo", scheduleMo);
       		request.setAttribute("scheduleTu", scheduleTu);
       		request.setAttribute("scheduleWe", scheduleWe);
       		request.setAttribute("scheduleTh", scheduleTh);
       		request.setAttribute("scheduleFr", scheduleFr);
       		request.setAttribute("scheduleSa", scheduleSa);
       		request.setAttribute("scheduleSu", scheduleSu);
       		request.setAttribute("temperaturelist", temperaturelist);
       		return ("myhome.jsp");
       		
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }

}
