package a.entity.gus06.convert.stringtoboolean;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20171014";}

	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null data");
		return b_((String) obj);
	}

	private boolean b_(String s) throws Exception
	{
		if(s.equals("true")) return true;
		if(s.equals("1")) return true;
		
		if(s.equals("false")) return false;
		if(s.equals("0")) return false;
		
		throw new Exception("Invalid data: "+s);
	}
}
