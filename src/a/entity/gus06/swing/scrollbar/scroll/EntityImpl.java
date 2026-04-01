package a.entity.gus06.swing.scrollbar.scroll;

import a.framework.*;
import javax.swing.JScrollBar;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191116";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JScrollBar bar = (JScrollBar) o[0];
		Integer n = (Integer) o[1];
		
		bar.setValue(n.intValue());
	}
}
