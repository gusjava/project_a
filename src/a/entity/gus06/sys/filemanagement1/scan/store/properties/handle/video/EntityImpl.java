package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.video;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service allocine;
	private Service checkMovie;
	private Service getVideoInfos;

	public EntityImpl() throws Exception
	{
		allocine = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.file.handle");
		checkMovie = Outside.service(this,"gus06.file.video.checksize.movie");
		getVideoInfos = Outside.service(this,"gus06.file.video.generic.infomap");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsVideo(o[0],(Map) o[1],(File) o[2]);
	}
	
	
	
	private void handleAsVideo(Object engine, Map prop, File file)
	{
		try
		{
			if(checkMovie.f(file)) 
				allocine.p(new Object[]{engine,prop,file});
			handleAsVideo1(prop,file);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsVideo(Object,Map,File)",e);
			prop.put("video.error",e.toString());
		}
	}
	
	private void handleAsVideo1(Map prop, File file)
	{
		try
		{
			Map infoMap = (Map) getVideoInfos.t(file);
			Iterator it = infoMap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) infoMap.get(key);
				prop.put("video."+key.toLowerCase(),value);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsVideo1(Map,File)",e);
			prop.put("video.error",e.toString());
		}
	}
}
