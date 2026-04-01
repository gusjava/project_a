package a.entity.gus06.swing.splitpane.reverse;

import a.framework.*;
import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.Dimension;

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
		
		Component r = split.getRightComponent();
		Component l = split.getLeftComponent();
		
//		int loc = split.getDividerLocation();
//		Dimension dr = r.getPreferredSize();
//		Dimension dl = l.getPreferredSize();
		
		split.removeAll();
		
		split.setRightComponent(l);
		split.setLeftComponent(r);
		
//		split.setDividerLocation(loc);
//		r.setPreferredSize(dr);
//		l.setPreferredSize(dl);
	}
}
