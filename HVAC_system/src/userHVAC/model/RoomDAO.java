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

import userHVAC.databean.RoomBean;

public class RoomDAO extends GenericDAO<RoomBean> {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	
	private String tableName;
	private String jdbcDriver;
	private String jdbcURL;
		
	public RoomDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(RoomBean.class, tableName, cp);
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
	
	public void myCreate(RoomBean bean) throws DAOException {
		Connection con = null;
  	try {
      	con = getConnection();
      	con.setAutoCommit(false);

          PreparedStatement pstmt = con.prepareStatement(
          		"INSERT INTO " + tableName + " (roomNum,occupancyStatus,temperature,userId,hvacStatus,lightStatus) VALUES (?,?,?,?,?,?)");
          pstmt.setString(1, bean.getRoomNum());
          pstmt.setBoolean(2, bean.isOccupancyStatus());
          pstmt.setDouble(3, bean.getTemperature());
          pstmt.setInt(4, bean.getUserId());
          pstmt.setBoolean(5, bean.isHvacStatus());
          pstmt.setBoolean(6, bean.isLightStatus());
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
	
	public RoomBean[] getUserRoominfo(int userId) throws RollbackException {
		
		// Calls GenericDAO's match() method.
		// match constraint argument is userId, match returns all the Item beans which userId equal to the argument
		RoomBean[] schedule = match(MatchArg.equals("userId",userId)); 
      return schedule;
	}
	
	public RoomBean[] Lookup(String KeywordDescription) throws RollbackException {
		RoomBean[] rooms;
		//if search infor is null, display all the items for sale
		if(KeywordDescription==null||KeywordDescription.length() == 0)
		{
			// This no match constraint arguments, match returns all the Item beans 
			rooms = match();
		}
		else
		{
		//if search infor in not null, display 
		// Calls GenericDAO's match() method.
		// match constraint argument is description,
		//match returns all the Item beans which description similar to the argument
			rooms = match(MatchArg.containsIgnoreCase("description",KeywordDescription));
		} 
	     return rooms;
	}
}
