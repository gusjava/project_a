package a.entity.gus06.filter.string.timestamp.endswith;

import a.framework.*;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231014";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;

		String str = (String) obj;
		if(str.length()<15) return false;
		
		str = str.substring(str.length()-15, str.length());
		
		try{sdf.parse(str);}
		catch(Exception e) {return false;}
		
		return true;
	}
}