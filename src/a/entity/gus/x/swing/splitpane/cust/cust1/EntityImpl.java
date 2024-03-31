package a.entity.gus.x.swing.splitpane.cust.cust1;

import javax.swing.JSplitPane;

import a.framework.Entity;
import a.framework.P;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20231128";}
	
	public void p(Object obj) throws Exception {
		JSplitPane split = (JSplitPane) obj;
		split.setDividerSize(3);
		split.setDividerLocation(250);
	}
}
