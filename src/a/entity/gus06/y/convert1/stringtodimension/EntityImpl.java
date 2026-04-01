package a.entity.gus06.y.convert1.stringtodimension;

import java.awt.Dimension;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	public Object t(Object obj) throws Exception
	{
		if (obj == null) return null;
		return dim((String) obj);
	}

	private Dimension dim(String size)
	{
		String[] n = size.split(" ");
		return new Dimension(i_(n[0]), i_(n[1]));
	}

	private int i_(String s)
	{return Integer.parseInt(s);}
}