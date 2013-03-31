//Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637
package userHVAC.formbean;

import java.util.ArrayList;


import java.util.List;

import org.mybeans.form.FormBean;


public class RegisterForm extends FormBean{
	private String userName;
	private String email;
	private String roomNum;
	private String firstname;
	private String lastname;
	private String passwordConfirm;
    private String password;
    private String action;
	

    public void setUserName(String s)   		{ userName=sanitize(s); }
    public void setEmail(String s)     			{ email=sanitize(s); }
    public void setPassword(String s)  			{ password=sanitize(s); }
    public void setFirstname(String s)  		{ firstname=sanitize(s); }
    public void setLastname(String s)   		{ lastname=sanitize(s); }
    public void setPasswordConfirm(String s)  	{ passwordConfirm=sanitize(s); }
    public void setAction(String s)   			{ action = s;    }
	public void setRoomNum(String s)	 		{roomNum = sanitize(s);  }	
	
    
    public String getUserName()  		{ return userName; }
    public String getPassword()  		{ return password; }
    public String getEmail()	 		{ return email; }
    public String getFirstname()  		{ return firstname; }
    public String getLastname()  		{ return lastname; }
    public String getpasswordConfirm()  { return passwordConfirm; }
    public String getAction()    		{ return action;   }
	public String getRoomNum() 			{return roomNum;}
    
    public boolean isPresent()   { return action != null; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
       
        // Valid user names, passwords, first names and last names must 
        //contain at least one non-blank character 
        if (userName.trim().isEmpty()) errors.add("User Name need to contain at least one non-blank character");
        if (roomNum.trim().isEmpty()) errors.add("Room Number need to contain at least one non-blank character");
        if (firstname.trim().isEmpty()) errors.add("First Name need to contain at least one non-blank character");
        if (lastname.trim().isEmpty()) errors.add("Last Name need to contain at least one non-blank character");
        if (password.trim().isEmpty()) errors.add("Password need to contain at least one non-blank character");
        if (action == null) errors.add("Button is required");

        if (errors.size() > 0) return errors;
    
       
        if (!action.equals("Goto Login") && !action.equals("Register")) errors.add("Invalid button");
        if (errors.size() > 0) return errors;
        
        if (userName.matches(".*[<>\"&].*")) errors.add("User Name may not contain angle brackets or quotes or ampersand");
        //A valid e-mail address format for this application is <username>@<hostname>, where <username> 
        //and <hostname> each contain at least one character.
        //here email format should be (letters and digit)@(letters and digit).(letters)
        if (!email.matches("[a-z0-9A-Z]+@([a-z0-9A-Z]+\\.)+[a-zA-Z]{1,}$")) 
        	errors.add("Invalid formate for emalil address, " +"\n" +
        			"should be:letters and digits @letters and digits.at least one letters");  
        if (errors.size() > 0) return errors;
        
          if (!password.matches(passwordConfirm)) errors.add("Password is not matched");
        return errors;
    }
  //should not allow users to enter data which will cause your application to 
    //blow-up or present user-supplied HTML tags to a user
	private String sanitize(String s) {
		//if s is null, s can not call function
		if(s == null)
		{
			return s;
		}
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}