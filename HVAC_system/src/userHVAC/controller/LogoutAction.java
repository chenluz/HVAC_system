//Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637
package userHVAC.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import userHVAC.controller.Action;
import userHVAC.model.Model;

/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);
        request.setAttribute("user",null);

        return "login.jsp";
    }
}
