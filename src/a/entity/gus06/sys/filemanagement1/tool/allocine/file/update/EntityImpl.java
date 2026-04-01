package a.entity.gus06.sys.filemanagement1.tool.allocine.file.update;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200921";}


	private Service handleFile;

	public EntityImpl() throws Exception
	{
		handleFile = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.file.handle");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		Map prop = (Map) o[2];
		Map movie = (Map) o[3];
		
		updateProp(engine,movie,prop);
	}
	
	
	
	private void updateProp(Object engine, Map movie, Map prop)
	{
		try
		{
			String code = (String) movie.get("code");
			String query = ">"+code;
			
			Map prop1 = new HashMap(prop);
			handleFile.p(new Object[]{engine,prop1,query});
			
			((V)engine).v("writeProp",prop1);
		}
		catch(Exception e)
		{Outside.err(this,"updateProp(Object,Map,Map)",e);}
	}
}
