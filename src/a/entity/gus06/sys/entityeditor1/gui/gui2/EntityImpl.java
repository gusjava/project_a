package a.entity.gus06.sys.entityeditor1.gui.gui2;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20251215";}
	
	private Service guiList;
	private Service guiDetail;

	private JSplitPane split;
	

	public EntityImpl() throws Exception
	{
		guiList = Outside.service(this, "*gus.sys.entityeditor1.gui.gui2.list");
		guiDetail = Outside.service(this, "*gus.sys.entityeditor1.gui.gui2.detail");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);
		
		split.setLeftComponent((JComponent) guiList.i());
		split.setRightComponent((JComponent) guiDetail.i());
		
		guiList.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		guiList.v("engine", obj);
		guiDetail.v("engine", obj);
	}
	
	public void actionPerformed(ActionEvent e) 
	{selectionChanged();}
	
	private void selectionChanged() 
	{
		try {guiDetail.p(guiList.g());}
		catch(Exception e) 
		{Outside.err(this, "selectionChanged()", e);}
	}
}