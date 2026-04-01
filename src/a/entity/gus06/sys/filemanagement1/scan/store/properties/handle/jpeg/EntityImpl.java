package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.jpeg;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service getExifDate;

	public EntityImpl() throws Exception
	{
		getExifDate = Outside.service(this,"gus06.file.image.extraction.jpegphoto.originaltime.yyyymmdd_hhmmss");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsJpeg(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsJpeg(Object engine, Map prop, File file)
	{
		try
		{
			String exifDate = (String) getExifDate.t(file);
			prop.put("jpeg.exifdate",exifDate);
		}
		catch(Exception e){}
	}
}
