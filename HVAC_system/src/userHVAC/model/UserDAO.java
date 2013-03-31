package userHVAC.model;

import java.sql.PreparedStatement;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;


import userHVAC.databean.UserBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserDAO extends GenericDAO<UserBean> {
	private List<Connection> connectionPool = new ArrayList<Connection>();
	
	private String tableName;
	private String jdbcDriver;
	private String jdbcURL;
	
	//constructor of UserDAO 
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(UserBean.class, tableName, cp);
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
	//Rewrite create method  creates a new user record in the "user" table 
		//given a UserBean containing all the fields that describe a user, except for the user id
		// the create method in GenericDAO creates user record, but also can set record of id. 
		public void myCreate(UserBean user) throws DAOException {
			Connection con = null;
	      try {
	      	con = getConnection();
	      	con.setAutoCommit(false);    
	         
	      	PreparedStatement pstmt = con.prepareStatement("INSERT INTO " + tableName + " (userName,password,emailAddr,firstname,lastname,roomNum) VALUES (?,?,?,?,?,?)");
	      	
	      	pstmt.setString(1,user.getUserName());
	      	pstmt.setString(2,user.getPassword());
	      	pstmt.setString(3,user.getEmailAddr());
	      	pstmt.setString(4,user.getFirstname());
	      	pstmt.setString(5,user.getLastname());
	    	pstmt.setString(6,user.getRoomNum());
	  
	      	int count = pstmt.executeUpdate();
	      	if (count != 1) throw new SQLException("Insert updated "+count+" rows");
	      	
	      	pstmt.close(); 
	      	
	      	Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
	        rs.next();
	        user.setId(rs.getInt("LAST_INSERT_ID()"));
	        
	      	con.commit();
	        con.setAutoCommit(true);
	        
	      	releaseConnection(con);
	      	
	      } catch (Exception e) {
	          try { if (con != null) con.close(); } catch (SQLException e2) {  /*ignore*/  }
	      	throw new DAOException(e);
	      }
		}
	// Rewrite read method, which can return a UserBean based on key word email, 
	// the read method in GenericDAO only based on the key word PrimaryKey id
	public UserBean read(String email) throws DAOException {
		Connection con = null;
      try {
      	con = getConnection();

      	PreparedStatement pstmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE emailAddr=?");
      	pstmt.setString(1,email);
      	ResultSet rs = pstmt.executeQuery();
      	
      	UserBean user;
      	if (!rs.next()) {
      		user = null;
      	} else {
      		user = new UserBean();
      		user.setId(rs.getInt("id"));
      		user.setEmailAddr(rs.getString("emailAddr"));
      		user.setUserName(rs.getString("userName"));
      		user.setPassword(rs.getString("password"));
      		user.setFirstname(rs.getString("firstname"));
      		user.setLastname(rs.getString("lastname"));
      	}
      	
      	rs.close();
      	pstmt.close();
      	releaseConnection(con);
          return user;
          
      } catch (Exception e) {
          try { if (con != null) con.close(); } catch (SQLException e2) { /* ignore */ }
      	throw new DAOException(e);
      }
	}
	
	public UserBean[] getUsers() throws RollbackException {
		UserBean[] users = match();
		Arrays.sort(users);  // We want them sorted by last and first names (as per User.compareTo());
		return users;
	}
	
	public void setPassword(String emailAddr, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	UserBean dbUser;
        	try{
        	 dbUser = read(emailAddr);
        	}catch (Exception e) {
        		return; 
        	}
			if (dbUser == null) {
				throw new RollbackException("User "+emailAddr+" no longer exists");
			}
			
			dbUser.setPassword(password);
			
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}