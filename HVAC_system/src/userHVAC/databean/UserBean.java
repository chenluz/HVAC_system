package userHVAC.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")

public class UserBean {
	private int id;
    private String userName;
    private String password;
    private String emailAddr;
  
	private String roomNum;
    private String firstname;
    private String lastname;

    public int    getId()            { return id;       }
    public String getPassword()      { return password; }
    public String getUserName()      { return userName; }
    public String getEmailAddr()     { return emailAddr;}
    public String getFirstname()     { return firstname; }
    public String getLastname()      { return lastname; } 
    public String getRoomNum() 		 { return roomNum;}

    public void setId(int i) 			{ id = i;        }
    public void setPassword(String s)  	{ password = s;  }
    public void setUserName(String s)  	{ userName = s;  }
    public void setEmailAddr(String s) 	{ emailAddr = s; }
    public void setFirstname(String s) 	{ firstname = s; }
    public void setLastname(String s) 	{ lastname = s;  }
	public void setRoomNum(String s) 	{roomNum = s;    }
}