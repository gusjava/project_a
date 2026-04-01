package a.entity.gus06.sys.expression1.apply.op._tray_msg_info;

import a.framework.*;
import java.awt.TrayIcon;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161003";}
	
	public final static String DEFAULT_TITLE = "Information";
	public final static TrayIcon.MessageType TYPE = TrayIcon.MessageType.INFO;
	
	public final static String KEY_MESSAGE = "message";
	public final static String KEY_TITLE = "title";
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof TrayIcon) return new T1((TrayIcon) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private TrayIcon t;
		public T1(TrayIcon t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{return new E1(t,obj);}
	}
	
	
	private class E1 implements E
	{
		private TrayIcon t;
		private Object data;
		
		public E1(TrayIcon t, Object data)
		{
			this.t = t;
			this.data = data;
		}
		
		public void e() throws Exception
		{show(t,data);}
	}
		
	
	
	
	private void show(TrayIcon t, Object data) throws Exception
	{
		String[] n = getInfos(data);
		
		String message = n[0];
		String title = n[1];
		
		t.displayMessage(title,message,TYPE);
	}
	
	
	
	
	private String[] getInfos(Object data) throws Exception
	{
		if(data instanceof String) return new String[]{(String) data,DEFAULT_TITLE};
		if(data instanceof Map) return getInfos((Map) data);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	private String[] getInfos(Map map) throws Exception
	{
		String message = get(map,KEY_MESSAGE,null);
		String title = get(map,KEY_TITLE,DEFAULT_TITLE);
		
		if(message==null) throw new Exception("No message provided");
		return new String[]{message,title};
	}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}
