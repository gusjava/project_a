package a.entity.gus06.sys.javaprojectviewer1.gui1.class1.list.control;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.sql.Connection;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, ActionListener, V, I, P {

	public String creationDate() {return "20170220";}
	
	
	private Service selectorSup;
	private Service actionBuilder;
	private Service toolbar;


	private JToolBar bar;
	
	private Object data;
	private G selector;
	
	
	
	
	
	public EntityImpl() throws Exception
	{
		selectorSup = Outside.service(this,"*gus06.support.holder");
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toolbar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		selectorSup.addActionListener(this);
		
		bar = (JToolBar) toolbar.i();
	}
	
	
	
	
	public Object i() throws Exception
	{return bar;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		updateGui();
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("selector"))
		{
			selector = (G) obj;
			selectorSup.p(selector);
			updateGui();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
