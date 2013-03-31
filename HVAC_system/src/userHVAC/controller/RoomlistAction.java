
package userHVAC.controller;

import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import userHVAC.databean.RoomBean;
import userHVAC.formbean.SearchForm;

import userHVAC.model.RoomDAO;
import userHVAC.model.Model;

public class RoomlistAction extends Action {
	private FormBeanFactory<SearchForm> formBeanFactory = FormBeanFactory.getInstance(SearchForm.class);
	private RoomDAO roomDAO;

	public RoomlistAction(Model model) {
		roomDAO = model.getRoomDAO();
	}

	public String getName() { return "roomlist.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
    	try {
    	
    	SearchForm searchForm = formBeanFactory.create(request);
    	
		request.setAttribute("searchForm",searchForm);
		
		RoomBean[] rooms = roomDAO.Lookup(searchForm.getSearch());
		request.setAttribute("rooms", rooms);
		
       	return ("roomlist.jsp");
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }catch (FormBeanException e) {
        	e.printStackTrace();
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}