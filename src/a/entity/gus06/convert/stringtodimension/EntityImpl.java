package a.entity.gus06.convert.stringtodimension;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140803";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj.equals("")) return null;
		return dim((String) obj);
	}
	
	
	private Dimension dim(String size)
	{
		String[] n = size.split(" ");
		return new Dimension(i_(n[0]),i_(n[1]));
	}


	private int i_(String s)
	{return Integer.parseInt(s);}
}