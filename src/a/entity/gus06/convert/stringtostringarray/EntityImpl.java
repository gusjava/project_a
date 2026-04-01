package a.entity.gus06.convert.stringtostringarray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171014";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return array((String) obj);
	}
	
	private String[] array(String s)
	{return s.split(" ");}
}
