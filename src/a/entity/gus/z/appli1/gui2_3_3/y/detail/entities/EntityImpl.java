package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.*;

public class EntityImpl implements Entity, P, I, ActionListener {
	public String creationDate() {return "20260506";}

	private Service engine;
	private Service guiListing;
	private Service guiDetail;

	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.engine");
		guiListing = Outside.service(this,"*gus.z.appli1.gui2_3_3.y.detail.entities.list");
		guiDetail = Outside.service(this,"*gus.z.appli1.gui2_3_3.y.detail.entities.detail");

		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(220);

		split.setLeftComponent((JComponent) guiListing.i());
		split.setRightComponent((JComponent) guiDetail.i());

		guiListing.addActionListener(this);
	}

	public Object i() throws Exception
	{return split;}

	public void p(Object obj) throws Exception
	{
		engine.v("scope", obj);
	}

	public void actionPerformed(ActionEvent e)
	{
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
