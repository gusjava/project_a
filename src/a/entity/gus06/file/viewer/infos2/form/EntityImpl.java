package a.entity.gus06.file.viewer.infos2.form;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160528";}

	public static final String KEY_RWX = "rwx";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_SHA1 = "sha1";
	public static final String KEY_NAME = "name";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MIMETYPE = "mimetype";
	public static final String KEY_MIMEHIER = "mimehier";
	public static final String KEY_CHARSET = "charset";
	public static final String KEY_ENDOFLINE = "endofline";
	public static final String KEY_PAGENUMBER = "pagenumber";
	public static final String KEY_DURATION = "duration";
	
	public static final String KEY_CREATIONTIME = "creationtime";
	public static final String KEY_LASTMODIFIEDTIME = "lastmodifiedtime";
	public static final String KEY_LASTACCESSTIME = "lastaccesstime";
	


	private Service formPanel;
	private Service extractInfos;
	
	private Map comps;

	private File file;
	private JComponent comp;


	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		extractInfos = Outside.service(this,"gus06.file.extract.infomap1");
		
		comp = (JComponent) formPanel.i();
		comp.setOpaque(false);
		
		comps = new HashMap();
		
		initLabel(KEY_NAME,"Name");
		initLabel(KEY_LOCATION,"Location");
		initLabel(KEY_SIZE,"Size");
		initLabel(KEY_RWX,"Rights");
		separator();
		initLabel(KEY_MD5,"MD5");
		initLabel(KEY_SHA1,"SHA1");
		initLabel(KEY_MIMETYPE,"Mime type");
		initLabel(KEY_MIMEHIER,"Mime hier");
		separator();
		initLabel(KEY_CREATIONTIME,"Creation time");
		initLabel(KEY_LASTMODIFIEDTIME,"Last modif time");
		initLabel(KEY_LASTACCESSTIME,"Last access time");
		separator();
		initLabel(KEY_CHARSET,"Charset");
		initLabel(KEY_ENDOFLINE,"End of line");
		initLabel(KEY_PAGENUMBER,"Page nb");
		initLabel(KEY_DURATION,"Duration");
	}
	
	
	public Object i() throws Exception
	{return comp;}
	
	
	
	private void separator() throws Exception
	{formPanel.v(" ",new JLabel(" "));}
	
	
	
	private void initLabel(String key, String title) throws Exception
	{
		JTextField field = new JTextField(" ");
		field.setEditable(false);
		field.setOpaque(false);
		
		formPanel.v(title,field);
		comps.put(key,field);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		Map map = (Map) extractInfos.t(file);
		
		Iterator it = comps.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			JTextField field = (JTextField) comps.get(key);
			String value = map.containsKey(key)? (String) map.get(key) : "";
			
			field.setText(value);
			field.setToolTipText(value);
		}
	}
}