package a.entity.gus06.appli.laboscript.gui.consolegui;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160623";}


	private Service gui1;
	private Service viewer;

	private JSplitPane split;
	private JComponent gui1Comp;
	private JComponent viewerComp;
	

	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.appli.laboscript.gui.consolegui.gui1");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		
		gui1Comp = (JComponent) gui1.i();
		viewerComp = (JComponent) viewer.i();
		
		split = new JSplitPane();
		split.setRightComponent(viewerComp);
		split.setLeftComponent(gui1Comp);
		
		split.setDividerSize(3);
		split.setDividerLocation(400);
		
		viewerComp.setMaximumSize(new Dimension(200,0));
		
		gui1.addActionListener(this); 
		updateViewer();
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{updateViewer();}
	
	
	private void updateViewer()
	{
		try{viewer.p(gui1.r("input"));}
		catch(Exception e)
		{Outside.err(this,"updateViewer()",e);}
	}

}