package userHVAC.controller;

import java.util.ArrayList;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;





import userHVAC.databean.ScheduleBean;
import userHVAC.databean.UserBean;

import userHVAC.controller.Action;
import userHVAC.formbean.ScheduleForm;
import userHVAC.formbean.IdForm;
import userHVAC.model.ScheduleDAO;
import userHVAC.model.Model;

public class UpdateAction extends Action {
	private FormBeanFactory<ScheduleForm>  shceduleFormFactory  = FormBeanFactory.getInstance(ScheduleForm.class);
	private FormBeanFactory<IdForm>  idFormFactory  = FormBeanFactory.getInstance(IdForm.class);
	
	private ScheduleDAO scheduleDAO;

	public UpdateAction(Model model) {
		scheduleDAO = model.getScheduleDAO();
	}
	
	public String getName() { return "update.do"; }
	
	public String perform(HttpServletRequest request) {
			List<String> errors = new ArrayList<String>();
		try { 
			ScheduleForm scheduleform = shceduleFormFactory.create(request);
			IdForm idfrom = idFormFactory.create(request);
			double temperature;
		 	errors = idfrom.getValidationErrors();
	        request.setAttribute("errors",errors); 
		    if (errors.size() > 0) {
		    	initialSchedule(request,errors);
		    	return "myschedule.jsp";
		    }
			errors = scheduleform.getValidationErrors();
	        request.setAttribute("errors",errors); 
		    if (errors.size() > 0){
		    	initialSchedule(request,errors);
		    	return "myschedule.jsp";
		    }
		    	int id = idfrom.getIdAsInt();
		    	ScheduleBean bean = scheduleDAO.read(id);
		    	if (bean == null) {
		    		errors.add("No record with id="+id);
		    		initialSchedule(request,errors);
		    		return "myschedule.jsp";
		    	}
		        
		    	temperature = scheduleform.getTemperatureAsDouble();
	       		bean.setTemperature(temperature);	
	       		scheduleDAO.updateSchedule(bean);
	       		initialSchedule(request,errors);
	       		request.setAttribute("scheduleform","scheduleform");
	       		request.setAttribute("message","Schedule update!!");
	       		return "myschedule.jsp";
	        }catch (RollbackException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        } catch (FormBeanException e) {
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	        }
	   }
	public void initialSchedule(HttpServletRequest request, List<String> errors){
		try{
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
		}catch (RollbackException e) {
	    	errors.add(e.getMessage());
		}
	}
}