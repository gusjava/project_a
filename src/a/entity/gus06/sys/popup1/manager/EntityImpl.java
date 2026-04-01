package a.entity.gus06.sys.popup1.manager;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20161005";}
	
	public static final String KEY_DATE = "date";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";


	private Service getNow;
	private Service displayer;
	
	private List notifs;


	public EntityImpl() throws Exception
	{
		getNow = Outside.service(this,"gus06.time.now");
		displayer = Outside.service(this,"gus06.sys.popup1.displayer");
		
		notifs = new ArrayList();
	}
	
	
	public Object g() throws Exception
	{return notifs;}
	
	
	public void p(Object obj) throws Exception
	{
		Map notif = toMap(obj);
		
		notif.put(KEY_DATE,getNow.g());
		notifs.add(notif);
		
		displayer.p(notif);
	}
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj instanceof Map) return new HashMap((Map) obj);
		if(obj instanceof String)
		{
			Map m = new HashMap();
			m.put(KEY_MESSAGE,obj);
			return m;
		}
		if(obj instanceof String[])
		{
			String[] o = (String[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			Map m = new HashMap();
			m.put(KEY_MESSAGE,o[0]);
			m.put(KEY_TITLE,o[1]);
			return m;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}