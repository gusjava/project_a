package a.entity.gus06.sys.base1.holder.jdbc.g.infos;

import a.framework.*;
import javax.swing.JComponent;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JTextField;
import java.util.Iterator;

public class EntityImpl implements Entity, G, I {

	public String creationDate() {return "20150808";}
	
	
	public static final String KEY_URL = "url";
	public static final String KEY_USER = "user";
	public static final String KEY_PWD = "pwd";
	public static final String KEY_PATH = "path";
	public static final String KEY_STRUCTURE = "structure";
	
	public static final String STRUCT_MAP1 = "map1";
	public static final String STRUCT_MAP2 = "map2";
	public static final String STRUCT_LINK = "link";
	public static final String STRUCT_BLOB = "blob";
	


	private Service form;
	private Service persist;
	
	private Map fields;

	

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text");
		
		fields = new HashMap();
		
		initField(KEY_URL);
		initField(KEY_USER);
		initField(KEY_PWD);
		initField(KEY_PATH);
		initField(KEY_STRUCTURE);
	}
	
	
	public Object i() throws Exception
	{return form.i();}
	
	
	
	public Object g() throws Exception
	{
		Map map = new HashMap();
		Iterator it = fields.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			JTextField field = (JTextField) fields.get(key);
			String value = field.getText();
			
			map.put(key,value);
		}
		return map;
	}
	
	
	
	private void initField(String key) throws Exception
	{
		JTextField field = new JTextField();
		persist.v(getClass().getName()+"_"+key,field);
		
		fields.put(key,field);
		form.v(key,field);
	}
}
