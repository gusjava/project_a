package a.entity.gus06.sys.expression1.apply.op._tofile;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151109";}


	private Service buildFile;
	private Service write;
	
	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.expression1.file.build");
		write = Outside.service(this,"gus06.file.write.generic");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Map opMap = (Map) o[1];
		
		if(value==null) return null;
		if(value instanceof File) return file(value, opMap);
		if(value instanceof File[]) return file(value, opMap);
		if(value instanceof String) return file(value, opMap);
		
		if(value instanceof Object[]) return handleList(toList((Object[]) value), opMap);
		if(value instanceof List) return handleList((List) value, opMap);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private File file(Object value, Map opMap) throws Exception
	{
		if(value instanceof File)
		{
			return ((File) value).getCanonicalFile();
		}
		if(value instanceof File[])
		{
			File[] ff = (File[]) value;
			if(ff.length!=1) throw new Exception("Invalid array length: "+ff.length);
			return ff[0].getCanonicalFile();
		}
		if(value instanceof String)
		{
			return (File) buildFile.t(new Object[]{value, opMap});
		}
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	
	private File handleList(List list, Map opMap) throws Exception
	{
		if(list.size()==2) return handle2(list.get(0), list.get(1), opMap);
		if(list.size()==3) return handle3(list.get(0), list.get(1), list.get(2), opMap);
		
		throw new Exception("Invalid list size: "+list.size());
	}
	
	private File handle2(Object o1, Object o2, Map opMap) throws Exception
	{
		File file = file(o1, opMap);
		write.p(new Object[]{file, o2});
		return file;
	}
	
	private File handle3(Object o1, Object o2, Object o3, Map opMap) throws Exception
	{
		File file = file(o1, opMap);
		boolean strict = toBoolean(o3);
		boolean empty = !file.exists() || file.length()==0;
		if(strict || empty) write.p(new Object[]{file, o2});
		return file;
	}
	
	
	
	
	
	private List toList(Object[] arr)
	{
		List list = new ArrayList();
		for(int i=0;i<arr.length;i++) list.add(arr[i]);
		return list;
	}
	
	private boolean toBoolean(Object obj) throws Exception
	{
		return Boolean.parseBoolean(""+obj);
	}
}