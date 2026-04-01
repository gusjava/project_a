package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.zip;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service getZipSize;

	public EntityImpl() throws Exception
	{
		getZipSize = Outside.service(this,"gus06.file.zip.info.size");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsZip(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsZip(Object engine, Map prop, File file)
	{
		try
		{
			String size = ""+getZipSize.t(file);
			prop.put("zip.size",size);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsZip(Object,Map,File)",e);
			prop.put("zip.error",e.toString());
		}
	}
}
