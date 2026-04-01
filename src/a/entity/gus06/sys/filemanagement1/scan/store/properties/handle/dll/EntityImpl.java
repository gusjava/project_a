package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.dll;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}
	

	private Service getDllType;

	public EntityImpl() throws Exception
	{
		getDllType = Outside.service(this,"gus06.env.windows.dll.findtype");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsDll(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsDll(Object engine, Map prop, File file)
	{
		try
		{
			String dllType = (String) getDllType.t(file);
			prop.put("dll.type",dllType);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsDll(Object,Map,File)",e);
			prop.put("dll.error",e.toString());
		}
	}
}
