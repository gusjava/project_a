package a.entity.gus06.appli.entityhistory.gui.history;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150426";}


	private Service selector;
	private Service viewer;


	private JSplitPane split;
	
	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.appli.entityhistory.gui.history.selector");
		viewer = Outside.service(this,"*gus06.appli.entityhistory.gui.history.viewer");
		
		split = new JSplitPane();
		split.setDividerLocation(250);
		
		split.setLeftComponent((JComponent) selector.i());
		split.setRightComponent((JComponent) viewer.i());
		
		selector.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try
		{
			Object data = selector.g();
			viewer.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}
