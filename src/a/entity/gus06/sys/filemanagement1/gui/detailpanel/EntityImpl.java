package a.entity.gus06.sys.filemanagement1.gui.detailpanel;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.util.Objects;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191214";}

	public static final String KEY_MD5 = "md5";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MIME = "mime";
	public static final String KEY_TYPE = "type";
	
	public static final String TYPE_FILE = "file";
	public static final String FAILED_MD5 = "###";
	

	private Service titleLabel;
	private Service formatProp;
	private Service shiftPanel;
	
	private Service guiFile;
	private Service guiDir;
	
	private JPanel panel;
	
	private Object engine;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		titleLabel = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.titlelabel");
		formatProp = Outside.service(this,"gus06.sys.filemanagement1.tool.prop.format.map");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		
		guiFile = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.file");
		guiDir = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir");
		
		panel = nc(titleLabel.i(),shiftPanel.i());
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			engine = null;
			selected = null;
			guiFile.p(null);
			guiDir.p(null);
			shiftPanel.p(null);
			titleLabel.p(null);
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		if(selected==null)
		{
			guiFile.p(null);
			guiDir.p(null);
			shiftPanel.p(null);
			titleLabel.p(null);
			return;
		}
		
		String type = (String) selected.get(KEY_TYPE);
		if(!type.equals(TYPE_FILE))
		{
			titleLabel.p(selected);
			guiFile.p(null);
			guiDir.p(new Object[]{engine,selected});
			shiftPanel.p(guiDir);
			return;
		}
		
		String md5 = (String) selected.get(KEY_MD5);
		if(md5.equals(FAILED_MD5))
		{
			guiFile.p(null);
			guiDir.p(null);
			shiftPanel.p(null);
			titleLabel.p("FAILED MD5: "+md5);
			return;
		}
		
		try
		{
			Map prop = (Map) formatProp.t(((R)engine).r("prop:"+md5));
			
			checkSame(prop, KEY_MD5);
			checkSame(prop, KEY_SIZE);
			checkSame(prop, KEY_MIME);
			
			titleLabel.p(selected);
			guiDir.p(null);
			guiFile.p(new Object[]{engine,selected,prop});
			shiftPanel.p(guiFile);
		}
		catch(Exception e)
		{
			throw new Exception("DetailPanel failed for md5: "+md5,e);
		}
	}
	
	
	
	
	private JPanel nc(Object n, Object c)
	{
		JPanel panel = new JPanel(new BorderLayout());
		if(n!=null) panel.add((JComponent) n,BorderLayout.NORTH);
		if(c!=null) panel.add((JComponent) c,BorderLayout.CENTER);
		return panel;
	}
	
	
	
	private void checkSame(Map prop, String key) throws Exception
	{
		if(prop==null) return;
		if(!prop.containsKey(key)) throw new Exception("Key not found inside prop: "+key);
		if(!selected.containsKey(key)) throw new Exception("Key not found inside Selected: "+key);
		
		String v1 = ""+prop.get(key);
		String v2 = ""+selected.get(key);
		if(!Objects.equals(v1,v2)) throw new Exception("Values are not the same for key "+key+": "+v1+" & "+v2);
	}
}