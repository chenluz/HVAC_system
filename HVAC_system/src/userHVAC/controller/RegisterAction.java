package userHVAC.controller;

import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import userHVAC.formbean.RegisterForm;
import userHVAC.model.UserDAO;
import userHVAC.model.Model;
import userHVAC.databean.UserBean;
import userHVAC.controller.Action;

public class RegisterAction  extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory.getInstance(RegisterForm.class);
	
	private UserDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "register.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
            	      
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	// we can go to register page whenever we already log in or log out,
        	// if we go to register at the time we already log in, page we log out first
        		session.setAttribute("user", null);
        		request.setAttribute("user",null);
  	
        	RegisterForm registerform = formBeanFactory.create(request);
	        request.setAttribute("registerform",registerform);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!registerform.isPresent()) {
	            return "register.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(registerform.getValidationErrors());
	        if (errors.size() != 0) {
	            return "register.jsp";
	        }
	        
       			UserBean[] userlist;
       		// UserName should not be exist in the database,should be new one		
	       		userlist = userDAO.match(MatchArg.equals("userName", registerform.getUserName()));
	       		if (userlist.length > 0)
	       		{ 
	       			errors.add("This username is already exist");
	       			return "register.jsp";
	       		}
	       		// Email should not be exist in the database,should be new one
	       		userlist = userDAO.match(MatchArg.equals("emailAddr", registerform.getEmail()));   
	       		if (userlist.length > 0)
	       		{ 
	       			errors.add("This email address is already exist");
	       			return "register.jsp";
	       		}
	       		//Create new record in database, but this user doesn't has the record of id
	       		UserBean user = new UserBean();
	       		user.setEmailAddr(registerform.getEmail());
	       		user.setFirstname(registerform.getFirstname());
	       		user.setLastname(registerform.getLastname());
	       		user.setPassword(registerform.getPassword());
	       		user.setUserName(registerform.getUserName()); 
	       		user.setRoomNum(registerform.getRoomNum());
       			userDAO.myCreate(user);			
	       		session.setAttribute("user", user);
	       		request.setAttribute("user",user);
	       		
	       		return("add.do"); 
     
        }catch(DAOException e){
        	errors.add(e.getMessage());
        	return "error.jsp";
        }catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }

}
