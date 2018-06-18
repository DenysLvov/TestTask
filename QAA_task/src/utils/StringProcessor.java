package utils;
import java.util.regex.*;

public class StringProcessor {
	
	  private Pattern pattern;  
	  private Matcher matcher;
	  private String regExp;
		
	public boolean searchWordInStrng(String searchString, String inputString ){
		pattern = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(inputString);
		return matcher.matches();
			
		}
	
	public void setRegExp(String s){
		regExp = s;		
	}
	}
