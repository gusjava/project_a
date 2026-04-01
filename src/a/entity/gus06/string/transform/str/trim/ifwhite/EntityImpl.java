package a.entity.gus06.string.transform.str.trim.ifwhite;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151117";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String s_ = s.trim();
		return s_.equals("")?"":s;
	}
}
