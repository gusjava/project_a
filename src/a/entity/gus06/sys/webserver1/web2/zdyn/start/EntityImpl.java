package a.entity.gus06.sys.webserver1.web2.zdyn.start;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}
	
	
	private Service module_error;
	private Service module_prepare;
	private Service module_resources;
	private Service uniqueEntity;

	public EntityImpl() throws Exception
	{
		module_error = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.module.error");
		module_prepare = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.module.prepare");
		module_resources = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.module.resources");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		module_prepare.p(obj);
		
		R mr = (R) obj;
		V mv = (V) obj;
		
		String path = (String) mr.r("input path");
		File file = (File) module_resources.r(path);
		if(isFound(file)) {mv.v("output",file);return;}
		
		Map prop = (Map) mr.r("data config prop");
		if(prop==null || !prop.containsKey("main"))
		{
			mv.v("data err_message","Main property not found");
			P h = (P) mr.r("data h");
			h.p(module_error.t(mr.r("data")));
		}
		else
		{
			String mainEntity = (String) prop.get("main");
			P entity = (P) uniqueEntity.t(mainEntity);
			entity.p(mr);
		}
		
		if(mr.r("output") instanceof File)
		throw new Exception("Invalid file output: "+file);
	}
	
	
	
	private boolean isFound(File f)
	{return f!=null && f.isFile();}
}
