package userHVAC.model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private ScheduleDAO scheduleDAO;
	private UserDAO 	userDAO;
	private RoomDAO 	roomDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			userDAO  = new UserDAO(pool, "chenluz_user");
			roomDAO = new RoomDAO(pool, "chenluz_room");
			scheduleDAO = new ScheduleDAO(pool, "chenluz_schedule");
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public ScheduleDAO getScheduleDAO()  { return scheduleDAO; }
	public UserDAO 		getUserDAO()  { return userDAO; }
	public RoomDAO 		getRoomDAO()  { return roomDAO; }
}
