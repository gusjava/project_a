package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.handling;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200110";}
	
	
	private JPanel panel;
	
	private Object engine;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		refresh();
	}
	
	
	
	private void refresh()
	{
		try
		{
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
			
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}