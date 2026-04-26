package a.entity.gus06.sys.filemanagement1.gui.detailpanel.handling;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20200109";}
	
	public static final String KEY_TYPE = "type";
	public static final String TYPE_FILE = "file";
	

	private Service summaryFile;
	private Service summaryDir;
	private Service shiftPanel;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		summaryFile = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file.handling");
		summaryDir = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.handling");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		
		summaryFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{fileModified();}
		});
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
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
			P handler = findHandler();
			handler.p(new Object[]{engine,selected,prop});
			shiftPanel.p(handler);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private P findHandler() throws Exception
	{
		String type = (String) selected.get(KEY_TYPE);
		return type.equals(TYPE_FILE) ? summaryFile : summaryDir;
	}
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
			prop = null;
			
			shiftPanel.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	
	private void fileModified()
	{send(this,"fileModified()");}
}