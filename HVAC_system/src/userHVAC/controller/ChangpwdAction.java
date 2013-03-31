//Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637
package userHVAC.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;


import userHVAC.formbean.ChangpwdForm;
import userHVAC.model.UserDAO;
import userHVAC.model.Model;
import userHVAC.databean.UserBean;
import userHVAC.controller.Action;

public class ChangpwdAction extends Action {
	private FormBeanFactory<ChangpwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangpwdForm.class);
	
	private UserDAO userDAO;

	public ChangpwdAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "changepwd.do"; }
    
    public String perform(HttpServletRequest request) {
    	 HttpSession session = request.getSession();
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {    
	        // Load the form parameters into a form bean
	        ChangpwdForm form = formBeanFactory.create(request);  
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "changepwd.jsp";
	        }   
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "changepwd.jsp";
	        }
	
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			// Change the password
        	userDAO.setPassword(user.getEmailAddr(),form.getNewPassword());
        	session.setAttribute("user", user);
       		request.setAttribute("user",user);
			request.setAttribute("message","Password changed for "+user.getEmailAddr());
	        return "changepwd.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
