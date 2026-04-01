package a.entity.gus06.sys.expression1.apply.op._jdialog_choose;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180301";}
	
	public final static String KEY_MESSAGE = "message";
	public final static String KEY_TITLE = "title";
	public final static String KEY_INITVALUE = "initvalue";
	public final static String KEY_SELECTION = "selection";


	private Service findArray;
	
	public EntityImpl() throws Exception
	{
		findArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List)		return choose((List) obj);
		if(obj instanceof Object[])	return choose((Object[]) obj);
		if(obj instanceof Map)		return choose((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Object choose(List list) throws Exception
	{
		return choose(toArray(list));
	}
	
	private Object choose(Object[] selection)
	{
		String message = "Choose";
		String title = "Input text";
		Object initValue = null;
		
		return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,selection,initValue);
	}
	
	private Object choose(Map map) throws Exception
	{
		String message = (String) get(map,KEY_MESSAGE,"");
		String title = (String) get(map,KEY_TITLE,"");
		Object[] selection = toArray(get(map,KEY_SELECTION,null));
		Object initValue = get(map,KEY_INITVALUE,null);
		
		return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,selection,initValue);
	}
	
	private Object get(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	
	private Object[] toArray(Object obj) throws Exception
	{return (Object[]) findArray.t(obj);}
}
