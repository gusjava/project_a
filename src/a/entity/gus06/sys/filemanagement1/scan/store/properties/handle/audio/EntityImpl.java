package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.audio;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service getAudioInfos;
	private Service isTypeMp3;
	private Service getMp3Infos;

	public EntityImpl() throws Exception
	{
		getAudioInfos = Outside.service(this,"gus06.file.audio.generic.infomap");
		isTypeMp3 = Outside.service(this,"gus06.file.filter.mime.istype.audio.mpeg");
		getMp3Infos = Outside.service(this,"gus06.file.mp3.extract.prop");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsAudio(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsAudio(Object engine, Map prop, File file)
	{
		try
		{
			if(isTypeMp3.f(file)) handleMp3(prop,file);
			handleAsAudio1(prop,file);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsVideo(Object,Map,File)",e);
			prop.put("audio.error",e.toString());
		}
	}
	
	private void handleAsAudio1(Map prop, File file)
	{
		try
		{
			Map infoMap = (Map) getAudioInfos.t(file);
			Iterator it = infoMap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) infoMap.get(key);
				prop.put("audio."+key.toLowerCase(),value);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsAudio1(Map,File)",e);
			prop.put("audio.error",e.toString());
		}
	}
	
	private void handleMp3(Map prop, File file)
	{
		try
		{
			Map infoMap = (Map) getMp3Infos.t(file);
			Iterator it = infoMap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) infoMap.get(key);
				prop.put("mp3."+key.toLowerCase(),value);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"handleMp3(Map,File)",e);
			prop.put("mp3.error",e.toString());
		}
	}
}