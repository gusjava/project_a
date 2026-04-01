package a.entity.gus06.appli.vindinium.gui.configview.localgame.maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20170923";}

	public static final String TITLE = "Map editor";
	

	private Service dirView;
	private Service mapViewer;
	private Service titledBorder;
	
	private JSplitPane split;

	

	public EntityImpl() throws Exception
	{
		dirView = Outside.service(this,"*gus06.appli.vindinium.gui.configview.localgame.maps.list");
		mapViewer = Outside.service(this,"*gus06.appli.vindinium.map.viewer");
		titledBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.mp10");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		
		split.setLeftComponent((JComponent) dirView.i());
		split.setRightComponent((JComponent) mapViewer.i());
		titledBorder.v(TITLE,split);
		
		dirView.addActionListener(this);
	}


	public Object i() throws Exception
	{return split;}
	
	
	
	public void actionPerformed(ActionEvent e)
	{selected();}

	
	
	private void selected()
	{
		try
		{
			File file = (File) dirView.g();
			mapViewer.p(file);
		}
		catch(Exception e){Outside.err(this,"selected()",e);}
	}
}
