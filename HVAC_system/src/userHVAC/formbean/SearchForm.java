//Name:Chenlu Zhang  Date: 03/03/2013 Course number: 15637
package userHVAC.formbean;

import org.mybeans.form.FormBean;

public class SearchForm extends FormBean {
	private String search;
	private String action;
	
	public void setSearch(String s)  { search  = sanitize(s); }
	public void setAction(String s)    { action = s;    }
	
	public String getSearch()  { return search;  }
	public String getAction()    	{ return action;       }
	
	public boolean isPresent()   { return action != null; }

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
