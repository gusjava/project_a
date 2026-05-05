package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.class1;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}
	

	private Service getClassVer;

	public EntityImpl() throws Exception
	{
		getClassVer = Outside.service(this,"gus.x.file.class1.classversion");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsClass(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsClass(Object engine, Map prop, File file)
	{
		try
		{
			int[] version = (int[]) getClassVer.t(file);
			prop.put("class.version", version[0]+"."+version[1]);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsClass(Object,Map,File)",e);
			prop.put("class.error",e.toString());
		}
	}
}