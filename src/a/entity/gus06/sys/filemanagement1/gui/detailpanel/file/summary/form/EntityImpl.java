package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.summary.form;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200108";}
	

	private Service formPanel;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");
	}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
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
			formPanel.e();
			formPanel.p(selected);
			
			if(prop!=null)
			{
				String md5 = (String) prop.get("md5");
				Map prop1 = (Map) ((R)engine).r("prop1:"+md5);
				
				formPanel.p("sep");
				formPanel.p(prop1);
			}
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
			
			formPanel.e();
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}