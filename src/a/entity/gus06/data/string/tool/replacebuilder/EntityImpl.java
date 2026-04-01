package a.entity.gus06.data.string.tool.replacebuilder;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170319";}
	
	private Service forString;
	private Service forArray;
	private Service forList;
	private Service forFile;
	
	public EntityImpl() throws Exception
	{
		forString = Outside.service(this,"gus06.data.string.tool.replacebuilder.forstring");
		forArray = Outside.service(this,"gus06.data.string.tool.replacebuilder.forarray");
		forList = Outside.service(this,"gus06.data.string.tool.replacebuilder.forlist");
		forFile = Outside.service(this,"gus06.data.string.tool.replacebuilder.forfile");
	}
	

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		T t = (T) o[0];
		Object data = o[1];
		
		if(data==null) return null;
		
		if(data instanceof String) return forString.t(new Object[]{t,data});
		if(data instanceof List) return forList.t(new Object[]{t,data});
		if(data instanceof File) return forFile.t(new Object[]{t,data});
		if(data instanceof String[]) return forArray.t(new Object[]{t,data});
		if(data instanceof Object[]) return forList.t(new Object[]{t,arrayToList((Object[]) data)});
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List arrayToList(Object[] arr) throws Exception
	{
		List list = new ArrayList();
		for(int i=0;i<arr.length;i++) list.add(arr[i]);
		return list;
	}
}