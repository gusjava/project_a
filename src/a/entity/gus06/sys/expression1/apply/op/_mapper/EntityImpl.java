package a.entity.gus06.sys.expression1.apply.op._mapper;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160212";}
	
	public static final String KEY_CONTEXT = "__context";
	public static final String C_FILE_MAPPER = "file_mapper";


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		if(data==null) return null;
		if(data instanceof File) return new T1((File) data, opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private File file;
		private Map opMap;
		
		public T1(File file, Map opMap)
		{
			this.file = file;
			this.opMap = opMap;
		}
		
		public Object t(Object obj) throws Exception
		{
			String key = (String) obj;
			return performMapping(opMap,file,key);
		}
	}
	
	
	
	private File performMapping(Map opMap, File file, String key) throws Exception
	{
		Map context = (Map) ((G) get(opMap,KEY_CONTEXT)).g();
		if(context==null) throw new Exception("Context not found inside operators map");
		
		T t = (T) get(context,C_FILE_MAPPER);
		if(t==null) throw new Exception("File mapper not found inside context map");
		
		return (File) t.t(new Object[]{file,key});
	}
	
	
	private Object get(Map m, String k)
	{return m.containsKey(k)?m.get(k):null;}
}
