package a.entity.gus.y.convert1.stringtourl;

import a.framework.*;
import java.net.URL;
import java.net.MalformedURLException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		try{return new URL(s);}
		catch(MalformedURLException e){}
		
		try{return new URL("http://"+s);}
		catch(MalformedURLException e){}
		
		return null;
	}
}