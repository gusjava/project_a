package a.entity.gus06.swing.splitpane.inv;

import a.framework.*;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20161126";}

	
	
	public Object t(Object obj) throws Exception
	{
		p(obj);
		return obj;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JSplitPane split = (JSplitPane) obj;
		int v = split.getOrientation();
		
		if(v==JSplitPane.HORIZONTAL_SPLIT)
			split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		else split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}
}
