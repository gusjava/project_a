package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.summary;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200108";}
	

	private Service preview;
	private Service form;
	
	private JPanel panel;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		preview = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.summary.preview");
		form = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.summary.form");
		
		panel = wc(preview.i(),form.i());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		refresh();
	}
	
	
	
	private void refresh()
	{
		try
		{
			Object[] data = new Object[]{engine,selected,prop};
			form.p(data);
			preview.p(data);
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
			prop = null;
			
			form.p(null);
			preview.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	private JPanel wc(Object w, Object c)
	{
		JPanel panel = new JPanel(new BorderLayout());
		if(w!=null) panel.add((JComponent) w,BorderLayout.WEST);
		if(c!=null) panel.add((JComponent) c,BorderLayout.CENTER);
		return panel;
	}
}