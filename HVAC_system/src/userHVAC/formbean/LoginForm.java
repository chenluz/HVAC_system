//Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637
package userHVAC.formbean;
import java.util.ArrayList;


import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm  extends FormBean{
    private String email;
    private String password;
    private String action;
	
    public void setEmail(String s)     { email=sanitize(s); }
    public void setPassword(String s)  { password=sanitize(s); }
    public void setAction(String s)    { action = s;    }
    
    public String getEmail()  	 { return email; }
    public String getPassword()  { return password; }
    public String getAction()    { return action;   }
    
    public boolean isPresent()   { return action != null; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if ((email == null || email.length() == 0)&&(password == null || password.length() == 0))
        	errors.add("Email and Password is required");
        if (errors.size() > 0) return errors;
        
        if (email == null || email.length() == 0) errors.add("Email is required");
        if (errors.size() > 0) return errors;
        
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (errors.size() > 0) return errors;
        
        if (action == null) errors.add("Button is required");
        if (errors.size() > 0) return errors;
        
        if (!action.equals("Login") && !action.equals("Register")) errors.add("Invalid button");
      //A valid e-mail address format for this application is <username>@<hostname>, where <username> 
        //and <hostname> each contain at least one character.
        //here email format should be (letters and digit)@(letters and digit).(letters)
        if (!email.matches("[a-z0-9A-Z]+@([a-z0-9A-Z]+\\.)+[a-zA-Z]{1,}$")) 
        	errors.add("Invalid formate for emalil address, " +"\n" +
        			"should be:letters and digits @letters and digits.at least one letters");  
  
        return errors;
    }
    //should not allow users to enter data which will cause your application to 
    //blow-up or present user-supplied HTML tags to a user
	private String sanitize(String s) {
		//if s is null, it can not call function
		if(s == null)
		{
			return s;
		}
    	return s.replace("&", "&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;");
	}
}
