package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.summary.preview;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191214";}
	
	public static final String KEY_MD5 = "md5";
	
	public static final Dimension DIM = new Dimension(250,0);
	


	private Service screen;
	private Service findPreview1;
	private JComponent comp;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image.north");
		findPreview1 = Outside.service(this,"gus06.sys.filemanagement1.tool.preview1.find.image");
		
		comp = (JComponent) screen.i();
		comp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		comp.setMaximumSize(DIM);
		comp.setMinimumSize(DIM);
		comp.setPreferredSize(DIM);
	}
	
	
	public Object i() throws Exception
	{return comp;}
	
	
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
			String md5 = (String) selected.get(KEY_MD5);
			Object preview = findPreview1.t(new Object[]{engine,md5});
			screen.p(preview);
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
			
			screen.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}
