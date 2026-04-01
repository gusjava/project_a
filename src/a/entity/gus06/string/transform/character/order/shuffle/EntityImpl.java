package a.entity.gus06.string.transform.character.order.shuffle;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	
	public Object t(Object obj) throws Exception
	{
		StringBuffer in = new StringBuffer((String) obj);
		StringBuffer b = new StringBuffer();
		while(in.length()>0)
		{
			int n = (int) (Math.random()*in.length());
			b.append(in.charAt(n));
			in.deleteCharAt(n);
		}
		return b.toString();
	}
}
