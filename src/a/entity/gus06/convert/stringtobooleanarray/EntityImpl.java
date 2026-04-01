package a.entity.gus06.convert.stringtobooleanarray;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20171014";}


	private Service stringArrayToBooleanArray;

	public EntityImpl() throws Exception
	{
		stringArrayToBooleanArray = Outside.service(this,"gus06.convert.stringarraytobooleanarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String) obj);
	}
	
	private boolean[] convert(String s) throws Exception
	{
		String[] n = s.split(" ");
		return (boolean[]) stringArrayToBooleanArray.t(n);
	}
}
