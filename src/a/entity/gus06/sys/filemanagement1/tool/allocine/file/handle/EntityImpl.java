package a.entity.gus06.sys.filemanagement1.tool.allocine.file.handle;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201001";}
	
	public static final String START = "allocine.";


	private Service getMovieMap;
	private Service store;
	private Service writeCode;
	private Service removeKeys;
	private Service flattenMap;

	public EntityImpl() throws Exception
	{
		getMovieMap = Outside.service(this,"gus06.web.allocine.convert.videofiletodata1");
		store = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.store");
		writeCode = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.md5.write.code");
		removeKeys = Outside.service(this,"gus06.map.string.removekeys.startswith");
		flattenMap = Outside.service(this,"gus06.map.flatten.tomap");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map prop = (Map) o[1];
		Object movieObj = o[2];
		
		if(movieObj==null) throw new Exception("Invalid null value for movieObj");
		
		removeKeys.v(START,prop);
		handle(engine,prop,movieObj);
	}
	
	
	private void handle(Object engine, Map prop, Object movieObj)
	{
		try
		{
			Map movieMap = getMovieMap(movieObj);
			String md5 = (String) prop.get("md5");
			String code = (String) movieMap.get("code");
			
			if(code==null || code.equals("")) throw new Exception("Code Allocine not found inside map");
			
			store.p(new Object[]{engine,movieMap});
			writeCode.p(new Object[]{engine,md5,code});
			
			prop.put(START+"code",code);
		}
		catch(Exception e)
		{
			Outside.err(this,"handle(Object,Map,Object)",e);
			prop.put(START+"error",e.toString());
		}
	}
	
	
	private Map getMovieMap(Object movieObj) throws Exception
	{
		Map m = (Map) getMovieMap.t(movieObj);
		if(m==null) throw new Exception("MovieMap not found with "+movieObj);
		return (Map) flattenMap.t(m);
	}
}
