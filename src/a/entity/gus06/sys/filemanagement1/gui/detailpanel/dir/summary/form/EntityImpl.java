package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary.form;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200108";}
	
	public static final String KEY_LOCATION = "location";
	public static final String KEY_SIZE = "size";
	public static final String KEY_FILENB = "fileNb";
	public static final String KEY_DIRNB = "dirNb";
	public static final String KEY_FILENB0 = "fileNb0";
	public static final String KEY_DIRNB0 = "dirNb0";
	

	private Service formPanel1;
	private Service formPanel2;
	private Service formPanel3;
	private Service dataSize;
	
	private JPanel panel;
	
	private Object engine;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		formPanel1 = Outside.service(this,"*gus06.swing.panel.formpanel.panel1-1");
		formPanel2 = Outside.service(this,"*gus06.swing.panel.formpanel.panel1-2");
		formPanel3 = Outside.service(this,"*gus06.swing.panel.formpanel.panel1-3");
		dataSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		
		panel = new JPanel(new GridLayout(1,3));
		panel.add((JComponent) formPanel1.i());
		panel.add((JComponent) formPanel2.i());
		panel.add((JComponent) formPanel3.i());
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
			String location = (String) selected.get(KEY_LOCATION);
			long size = (long) selected.get(KEY_SIZE);
			long fileNb = (long) selected.get(KEY_FILENB);
			long dirNb = (long) selected.get(KEY_DIRNB);
			long fileNb0 = (long) selected.get(KEY_FILENB0);
			long dirNb0 = (long) selected.get(KEY_DIRNB0);
			
			String sizeDisplay = (String) dataSize.t(size);
			
			formPanel1.e();
			formPanel2.e();
			formPanel3.e();
			
			formPanel1.v("Location",location);
			formPanel1.v("Size",sizeDisplay);
			
			formPanel2.v("File nb",fileNb0);
			formPanel2.v("Dir nb",dirNb0);
			
			if(dirNb0>0)
			{
				formPanel3.v("Deep File nb",fileNb);
				formPanel3.v("Deep Dir nb",dirNb);
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
			
			formPanel1.e();
			formPanel2.e();
			formPanel3.e();
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}