package a.entity.gus06.string.transform.character.order.sort;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		char[] tab = s.toCharArray();
		Arrays.sort(tab);
		return new String(tab);
	}
}
