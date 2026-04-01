package a.entity.gus06.swing.scrollpane.scroll;

import a.framework.*;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191116";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JScrollPane pane = (JScrollPane) o[0];
		int[] n = (int[]) o[1];
		
		pane.getVerticalScrollBar().setValue(n[0]);
		pane.getHorizontalScrollBar().setValue(n[1]);
	}
}
