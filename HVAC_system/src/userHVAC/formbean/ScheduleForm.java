package userHVAC.formbean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class ScheduleForm extends FormBean{
	
	private String temperature = "";
	private String hide = "false";
	
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = fixBadChars(temperature);
	}
	
	public String getHide() {
		return hide;
	}
	public void setHide(String hide) {
		this.hide = hide;
	}
	
	public double getTemperatureAsDouble()	{
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be thrown!
		return Double.parseDouble(temperature);
		}
	
	public boolean getHideAsboolean(){
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be thrown!
		return Boolean.parseBoolean(hide);
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		
       //temperature cannot be blank.
		if (temperature == null || temperature.length() == 0) {
			errors.add("Temperature cannot be blank");
			return errors;
		}	
		// temperature must be number and between 0 and 127
		try {
			double temp = Double.parseDouble(temperature);
			if((temp < 0)||(temp > 127))
            {
				errors.add("Temperature must between 0 and 127 F");
            }
			} catch (NumberFormatException e) {
			errors.add("Temperature must be a number");
			}
		
		try {
			Boolean.parseBoolean(hide);
			} catch (NumberFormatException e) {
			errors.add("hide must be a boolean");
			}
		
		return errors;
	}
	//should not allow users to enter data which will cause your application to 
    //blow-up or present user-supplied HTML tags to a user
    private String fixBadChars(String s) {
		if (s == null || s.length() == 0) return s;
		
		Pattern p = Pattern.compile("[<>\"&]");
        Matcher m = p.matcher(s);
        StringBuffer b = null;
        while (m.find()) {
            if (b == null) b = new StringBuffer();
            switch (s.charAt(m.start())) {
                case '<':  m.appendReplacement(b,"&lt;");
                           break;
                case '>':  m.appendReplacement(b,"&gt;");
                           break;
                case '&':  m.appendReplacement(b,"&amp;");
                		   break;
                case '"':  m.appendReplacement(b,"&quot;");
                           break;
                default:   m.appendReplacement(b,"&#"+((int)s.charAt(m.start()))+';');
            }
        }
        
        if (b == null) return s;
        m.appendTail(b);
        return b.toString();
    }
}
