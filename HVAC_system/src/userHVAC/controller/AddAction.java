package userHVAC.controller;

import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;

import userHVAC.databean.RoomBean;
import userHVAC.databean.ScheduleBean;
import userHVAC.databean.UserBean;
import userHVAC.model.Model;
import userHVAC.model.RoomDAO;
import userHVAC.model.ScheduleDAO;

public class AddAction extends Action{
	private ScheduleDAO scheduleDAO;
	//for test 
	private RoomDAO roomDAO;


	public AddAction(Model model) {
		scheduleDAO = model.getScheduleDAO();
		//for test
		roomDAO = model.getRoomDAO();
	}
	
	public String getName() { return "add.do"; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
	    request.setAttribute("errors",errors);
		request.setAttribute("myschedule",null);
		 try {
			 
			//create this user's schedule at the same time
	       		ScheduleBean bean = new ScheduleBean();
	       		ScheduleBean[] beanlist = new ScheduleBean[24]; 
	            String[] week;
	            week = new String[7] ; 
	            week[0] = "MONDAY";
	            week[1] = "TUESDAY";
	            week[2] = "WENDESDAY";
	            week[3] = "THURSDAY";
	            week[4] = "FRIDAY";
	            week[5] = "SATURDAY";
	            week[6] = "SUNDAY";
	       		for(int i=0; i<7;i++)   //7 days a week
	       		{
	       			for(int j=0; j<24;j++)
	       			{
	       				bean.setUserId(((UserBean) request.getSession().getAttribute("user")).getId());	
	       				bean.setRoomNum(((UserBean) request.getSession().getAttribute("user")).getRoomNum());	
	       				bean.setWeek(week[i]);
	       				bean.setTime(Integer.toString(j));
	       				bean.setTemperature(65+j);
	       				scheduleDAO.myCreate(bean);
	       				beanlist[j] = bean;
	       			}
	       		}	
	       		
	        	///for test
	        	RoomBean roominfo = new RoomBean();
	        	roominfo.setUserId(((UserBean) request.getSession().getAttribute("user")).getId());	
	        	roominfo.setRoomNum(((UserBean) request.getSession().getAttribute("user")).getRoomNum());	
	        	roominfo.setLightStatus(true);
	        	roominfo.setHvacStatus(true);
	        	roominfo.setOccupancyStatus(true);
	        	roominfo.setTemperature(72.1);
				roomDAO.myCreate(roominfo);
	        	///for test
				
	       		return("myhome.do");		 
		 }catch(DAOException e){
	        	errors.add(e.getMessage());
	        	return "error.jsp";
	    }
	}

}
