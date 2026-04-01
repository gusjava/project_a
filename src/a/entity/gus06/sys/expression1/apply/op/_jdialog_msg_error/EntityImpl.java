package a.entity.gus06.sys.expression1.apply.op._jdialog_msg_error;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160909";}
	
	public final static String DEFAULT_TITLE = "Error";
	public final static int MESSAGE_TYPE = JOptionPane.ERROR_MESSAGE;
	
	public final static String KEY_MESSAGE = "message";
	public final static String KEY_TITLE = "title";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return new E1(obj);
		if(obj instanceof String[]) return new E1(obj);
		if(obj instanceof Exception) return new E1(obj);
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof Map) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object data;
		
		public E1(Object data)
		{this.data = data;}
		
		public void e() throws Exception
		{show(data);}
	}
		
	
	
	
	private void show(Object data) throws Exception
	{
		String[] n = getInfos(data);
		
		String message = n[0];
		String title = n[1];
		
		JOptionPane.showMessageDialog(null,message,title,MESSAGE_TYPE);
	}
	
	
	
	
	private String[] getInfos(Object data) throws Exception
	{
		if(data instanceof String[]) return (String[]) data;
		if(data instanceof String) return new String[]{(String) data,DEFAULT_TITLE};
		if(data instanceof Exception) return getInfos((Exception) data);
		if(data instanceof List) return getInfos((List) data);
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
	
	private String[] getInfos(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Invalid list info size: "+list.size());
		String message = (String) list.get(0);
		String title = (String) list.get(1);
		
		return new String[]{message,title};
	}
	private String[] getInfos(Exception e) throws Exception
	{
		String message = e.getMessage();
		String title = "Exception";
		
		return new String[]{message,title};
	}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}