package a.entity.gus06.data.perform.play;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.io.InputStream;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250524";}

	public static final String KEY_TYPE = "type";
	public static final String KEY_SOURCE = "source";

	private Service generic;
	
	private Service playFileMp3;
	private Service playFileWav;
	
	private Service playIsMp3;
	private Service playIsWav;
	
	
	public EntityImpl() throws Exception
	{
		generic = Outside.service(this,"gus06.file.audio.play.generic");
		
		playFileMp3 = Outside.service(this,"gus06.file.mp3.play");
		playFileWav = Outside.service(this,"gus06.file.wav.play");
		
		playIsMp3 = Outside.service(this,"gus06.io.inputstream.mp3.play");
		playIsWav = Outside.service(this,"gus06.io.inputstream.wav.play");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map) {play((Map) obj);return;}
		if(obj instanceof File) {play((File) obj);return;}
		if(obj instanceof File[]) {play((File[]) obj);return;}
		if(obj instanceof List) {play((List) obj);return;}
	}
	
	private void play(Map map) throws Exception
	{
		String type = (String) get(map, KEY_TYPE);
		Object source = get(map, KEY_SOURCE);
		
		if(type.equals("mp3")) playMp3(source);
		else if(type.equals("wav")) playWav(source);
	}
	
	private void playMp3(Object source) throws Exception
	{
		if(source instanceof File)
		{
			playFileMp3.p(source);
			return;
		}
		if(source instanceof InputStream)
		{
			playIsMp3.p(source);
			return;
		}
		if(source instanceof G)
		{
			playMp3(((G) source).g());
			return;
		}
		if(source instanceof Object[])
		{
			Object[] array = (Object[]) source;
			for(Object element : array) playMp3(element);
			return;
		}
		if(source instanceof List)
		{
			List list = (List) source;
			for(Object element : list) playMp3(element);
		}
	}
	
	private void playWav(Object source) throws Exception
	{
		if(source instanceof File)
		{
			playFileWav.p(source);
			return;
		}
		if(source instanceof InputStream)
		{
			playIsWav.p(source);
			return;
		}
		if(source instanceof G)
		{
			playWav(((G) source).g());
			return;
		}
		if(source instanceof Object[])
		{
			Object[] array = (Object[]) source;
			for(Object element : array) playWav(element);
			return;
		}
		if(source instanceof List)
		{
			List list = (List) source;
			for(Object element : list) playWav(element);
		}
	}
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private void play(File file) throws Exception
	{
		generic.p(file);
	}
	
	private void play(File[] files) throws Exception
	{
		for(File file : files) generic.p(file);
	}
	
	private void play(List list) throws Exception
	{
		for(Object f : list) generic.p((File) f);
	}
}