package a.entity.gus06.sys.base1.builder.search.perform.dir;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}



	private Service buildFilter;
	private Service load;
	private Service getName;



	public EntityImpl() throws Exception
	{
		buildFilter = Outside.service(this,"gus06.filter.map.build.fromstring.rule1");
		load = Outside.service(this,"gus.x.file.prop.read");
		getName = Outside.service(this,"gus.x.file.getname0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object filterData = o[1];
		Object progress = o[2];
		Set interrupt = (Set) o[3];
		
		F filter = buildFilter(filterData);
		File[] ff = dir.listFiles();
		Map result = new HashMap();
		
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			Object prop = load.t(f);
			boolean ok = filter.f(prop);
			if(ok) result.put(toId(f),prop);
			
			if(progress!=null) ((E)progress).e();
			if(!interrupt.isEmpty()) return null;
		}
		
		return result;
	}
	
	
	
	private String toId(File f) throws Exception
	{return (String) getName.t(f);}
	
	
	
	private F buildFilter(Object data) throws Exception
	{
		if(data instanceof F) return (F) data;
		if(data instanceof String) return (F) buildFilter.t(data);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
