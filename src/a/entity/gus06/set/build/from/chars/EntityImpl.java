package a.entity.gus06.set.build.from.chars;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Set set = new HashSet();
		for(int i=0;i<s.length();i++) set.add(""+s.charAt(i));
		return set;
	}
}
