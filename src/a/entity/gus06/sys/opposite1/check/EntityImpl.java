package a.entity.gus06.sys.opposite1.check;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160721";}
	
	public static final String MINUS = "MINUS";
	public static final String PLUS = "PLUS";


	public boolean f(Object obj) throws Exception
	{
		if(!(obj instanceof R)) return false;
		R r = (R) obj;
		
		try
		{
			String type = (String) r.r("type");
			return type.equals(MINUS);
		}
		catch(Exception e){}
		return false;
	}
}
