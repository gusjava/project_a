package a.entity.gus06.sys.expression1.apply.op._data_required;

import a.framework.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}

	public static final String KEY_KEY = "key";
	public static final String KEY_TYPE = "type";
	public static final String KEY_STORE = "store";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public final static String KEY_SELECTION = "selection";
	public static final String KEY_INITVALUE = "initValue";
	public static final String KEY_RESET = "reset";
	
	public static final String DEFAULT_TYPE = "string";
	public static final String DEFAULT_STORE = "data.properties";	
	public static final String DEFAULT_TITLE = "Data required";	
	public static final String DEFAULT_MESSAGE = "Please, enter data";
	public static final String DEFAULT_RESET = "false";

	public static final String TYPE_STRING = "string";
	public static final String TYPE_INTEGER = "integer";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_BOOLEAN = "boolean";


	private Service buildFile;
	private Service readProp;
	private Service writeProp;
	private Service findArray;

	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		findArray = Outside.service(this,"gus06.find.objectarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value instanceof String) return perform((String) value, opMap);
		if(value instanceof Map) return perform((Map) value, opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private Object perform(String s, Map opMap) throws Exception
	{
		Map m = new HashMap();
		m.put(KEY_KEY,s);
		return perform(m,opMap);
	}
	
	private Object perform(Map m, Map opMap) throws Exception
	{
		String key = (String) get1(m, KEY_KEY);
		Object store = get(m, KEY_STORE, DEFAULT_STORE);
		
		File storeFile = toFile(store, opMap);
		Map storeMap = (Map) readProp.t(storeFile);
		if(storeMap==null) storeMap = new HashMap();
		
		String reset = (String) get(m, KEY_RESET, DEFAULT_RESET);
		
		if(!storeMap.containsKey(key) || reset.equals("true"))
		{
			Object data = askData(m);
			if(data==null) return null;
			
			storeMap.put(key,""+data);
			writeProp.p(new Object[]{storeFile, storeMap});
			return data;
		}
		
		String type = (String) get(m, KEY_TYPE, DEFAULT_TYPE);
		String dataStr = (String) get(storeMap, key);
		return formatData(type, dataStr);
	}
	
	
	
	private Object askData(Map m) throws Exception
	{
		String title = (String) get(m, KEY_TITLE, DEFAULT_TITLE);
		String message = (String) get(m, KEY_MESSAGE, DEFAULT_MESSAGE);
		String initValue = (String) get(m, KEY_INITVALUE);
		Object[] selection = toArray(get(m, KEY_SELECTION));
		
		return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,selection,initValue);
	}
	
	
	private Object formatData(String type, String dataStr) throws Exception
	{
		if(type.equals(TYPE_STRING)) return dataStr;
		if(type.equals(TYPE_INTEGER)) return Integer.parseInt(dataStr);
		if(type.equals(TYPE_DOUBLE)) return Double.parseDouble(dataStr);
		if(type.equals(TYPE_BOOLEAN)) return Boolean.parseBoolean(dataStr);
		
		throw new Exception("Unsupported data type: "+type);
	}
	
	
	private File toFile(Object value, Map opMap) throws Exception
	{
		if(value instanceof File) return (File) value;
		if(value instanceof String) return (File) buildFile.t(new Object[]{value, opMap});
		
		throw new Exception("Invalid value type: "+value.getClass().getName());
	}
	
	
	
	
	
	private Object get(Map m, String key)
	{
		if(!m.containsKey(key)) return null;
		return m.get(key);
	}
	
	private Object get1(Map m, String key) throws Exception
	{
		if(!m.containsKey(key)) throw new Exception("Key not found: "+key);
		return m.get(key);
	}
	
	private Object get(Map m, String key, Object defaultValue)
	{
		if(!m.containsKey(key)) return defaultValue;
		return m.get(key);
	}
	
	private Object[] toArray(Object obj) throws Exception
	{
		if(obj==null) return null;
		return (Object[]) findArray.t(obj);
	}
}