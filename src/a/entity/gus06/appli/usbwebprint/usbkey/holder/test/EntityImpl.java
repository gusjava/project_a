package a.entity.gus06.appli.usbwebprint.usbkey.holder.test;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140911";}


	private Service holder;
	private Service viewer;


	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"gus06.appli.usbwebprint.usbkey.holder");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		
		holder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			Map map = (Map) holder.g();
			viewer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}
