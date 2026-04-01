package a.entity.gus06.string.transform.str.length;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150305";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return ""+s.length();
	}
}
