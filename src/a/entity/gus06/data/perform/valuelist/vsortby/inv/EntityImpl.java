package a.entity.gus06.data.perform.valuelist.vsortby.inv;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220628";}


	private Service readFile;
	private Service performMap;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		performMap = Outside.service(this,"gus06.map.build.sortedvalues.t.inv");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		T trans = (T) o[1];
		
		if(input==null) return null;
		
		if(input instanceof Map) return perform((Map) input, trans);
		if(input instanceof File) return perform((File) input, trans);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private List perform(Map m, T trans) throws Exception
	{return (List) performMap.t(new Object[]{m,trans});}
	
	
	private List perform(File f, T trans) throws Exception
	{
		Map m = (Map) readFile.t(f);
		return (List) performMap.t(new Object[]{m,trans});
	}
}