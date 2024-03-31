package a.entity.gus.z.appli1.gui2_3_3.y;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20231231";}
	
	private Service guiListing;
	private Service guiDetail;

	private JSplitPane split;
	
	public EntityImpl() throws Exception {
		guiListing = Outside.service(this, "gus.z.appli1.gui2_3_3.y.list");
		guiDetail = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(220);
		
		split.setLeftComponent((JComponent) guiListing.i());
		split.setRightComponent((JComponent) guiDetail.i());
		
		guiListing.addActionListener(this);
	}
	
	public Object i() throws Exception {
		return split;
	}
	
	public void actionPerformed(ActionEvent e) {
		selectionChanged();
	}
	
	private void selectionChanged() {
		try {
			guiDetail.p(guiListing.g());
		}
		catch(Exception e) {
			Outside.err(this, "selectionChanged()", e);
		}
	}
}
