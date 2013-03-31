package userHVAC.controller;

import java.util.ArrayList;




import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import userHVAC.model.Model;
import userHVAC.model.RoomDAO;
import userHVAC.databean.RoomBean;
import userHVAC.databean.UserBean;

public class MyroomAction extends Action {

	private RoomDAO roomDAO;

    public MyroomAction(Model model) {
    	roomDAO = model.getRoomDAO();
	}

    public String getName() { return "myroominfo.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	int userpp = ((UserBean) request.getSession().getAttribute("user")).getId();
            RoomBean[] room = roomDAO.getUserRoominfo(userpp);
    		if (room != null) request.setAttribute("roominfo",room);
    		
    		// Note: "/image" is mapped (in the web.xml file) to the ImageServlet
    		// which looks at the "photo" request attribute and sends back the bytes.
    		// If there is no "photo" attribute, it sends back HTTP Error 404 (resource not found).
    		return "myroominfo.jsp";
    	}catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	} 
    }
}
