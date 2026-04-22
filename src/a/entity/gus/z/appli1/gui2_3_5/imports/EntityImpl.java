package a.entity.gus.z.appli1.gui2_3_5.imports;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20260421";}
	
	private Service guiList;
	private Service guiDetail;

	private JSplitPane split;
	

	public EntityImpl() throws Exception
	{
		guiList = Outside.service(this,"*gus.z.appli1.gui2_3_5.imports.list");
		guiDetail = Outside.service(this,"*gus.z.appli1.gui2_3_5.imports.detail");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent((JComponent) guiList.i());
		split.setRightComponent((JComponent) guiDetail.i());
		
		guiList.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	public void actionPerformed(ActionEvent e) 
	{selectionChanged();}
	
	private void selectionChanged() 
	{
		try {guiDetail.p(guiList.g());}
		catch(Exception e) 
		{Outside.err(this, "selectionChanged()", e);}
	}
}