package a.entity.gus06.filter.string.build.mask2.validator;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160404";}


	
	public boolean f(Object obj) throws Exception
	{
		char[] c = (char[]) obj;
		if(c.length!=2) throw new Exception("Wrong data number: "+c.length);
		return isValidChar(c[0],c[1]);
	}
	
	
	private boolean isValidChar(char c1, char c2)
	{
		if(c1=='x') return true; 			//n'importe quel caract�re
		
		if(c1=='d') return isDigit((int) c2); 		//digit
		if(c1=='D') return !isDigit((int) c2); 		//non digit
		
		if(c1=='a') return isLowerCase((int) c2); 	//lowercase
		if(c1=='A') return !isLowerCase((int) c2); 	//non lowercase
		
		if(c1=='b') return isUpperCase((int) c2); 	//uppercase
		if(c1=='B') return !isUpperCase((int) c2); 	//non uppercase
		
		if(c1=='w') return isLetter((int) c2); 		//letter
		if(c1=='W') return !isLetter((int) c2); 	//non letter
		
		if(c1=='h') return isAlphanum((int) c2);	//alphanum
		if(c1=='H') return !isAlphanum((int) c2);	//non alphanum
		
		return c1==c2;
	}
	
	private boolean isDigit(int code)
	{return code>47 && code<58;}

	private boolean isLowerCase(int code)
	{return code>96 && code<123;}
	
	private boolean isUpperCase(int code)
	{return code>64 && code<91;}
	
	private boolean isLetter(int code)
	{return isLowerCase(code) || isUpperCase(code);}
	
	private boolean isAlphanum(int code)
	{return isLetter(code) || isDigit(code);}
}
