package userHVAC.model;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;

import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;


import userHVAC.databean.ScheduleBean;


public class ScheduleDAO extends GenericDAO<ScheduleBean> {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	
	private String tableName;
	private String jdbcDriver;
	private String jdbcURL;
	
	public ScheduleDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(ScheduleBean.class, tableName, cp);
		this.jdbcDriver = cp.getDriverName();;
		this.jdbcURL    = cp.getURL();
		this.tableName  = tableName;
	}

	//synchronized lock the object,avoid concurrence  
	private synchronized Connection getConnection() throws DAOException {
		if (connectionPool.size() > 0) {
			return connectionPool.remove(connectionPool.size()-1);
		}
		
      try {
          Class.forName(jdbcDriver);
      } catch (ClassNotFoundException e) {
          throw new DAOException(e);
      }

      try {
          return DriverManager.getConnection(jdbcURL);
      } catch (SQLException e) {
          throw new DAOException(e);
      }
	}
	
	private synchronized void releaseConnection(Connection con) {
		connectionPool.add(con);
	}
	
	public void myCreate(ScheduleBean bean) throws DAOException {
		Connection con = null;
  	try {
      	con = getConnection();
      	con.setAutoCommit(false);

          PreparedStatement pstmt = con.prepareStatement(
          		"INSERT INTO " + tableName + " (roomNum,time,week,temperature,userId) VALUES (?,?,?,?,?)");
          pstmt.setString(1, bean.getRoomNum());
          pstmt.setString(2, bean.getTime());
          pstmt.setString(3, bean.getWeek());
          pstmt.setDouble(4, bean.getTemperature());
          pstmt.setInt(5, bean.getUserId());
          pstmt.executeUpdate();
          pstmt.close();
          
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
          rs.next();
          bean.setId(rs.getInt("LAST_INSERT_ID()"));
          
          con.commit();
          con.setAutoCommit(true);
          
          releaseConnection(con);
       
  	} catch (SQLException e) {
          try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
      	throw new DAOException(e);
		}
	}
	
  //takes a user id parameter and returns an array of ItemBeans 
 //with information about the items this user has listed for sale.	
	public ScheduleBean[] getUserSchedule(int userId) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// match constraint argument is userId, match returns all the Item beans which userId equal to the argument
		ScheduleBean[] schedule = match(MatchArg.equals("userId",userId)); 
      return schedule;
	}
	
	public ScheduleBean updateSchedule( ScheduleBean newschedule) throws RollbackException {
		try {
			Transaction.begin();
			
			int id = newschedule.getId();
			
			ScheduleBean oldschedule = read(id);
			if (oldschedule == null) {
				throw new RollbackException("No schedule in database: id="+newschedule.getId()+".  (Someone else must have just deleted it.)");
			}
			
			update(newschedule);
			Transaction.commit();
			
			// return oldEntry so that we can write into the log the old and new values
			return oldschedule;
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
		

}
