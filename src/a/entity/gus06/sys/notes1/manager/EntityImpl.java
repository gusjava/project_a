package a.entity.gus06.sys.notes1.manager;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V, R, P, G {

	public String creationDate() {return "20210514";}


	private Service now;
	private Service access;

	private File rootDir;
	private Object holder;
	
	public EntityImpl() throws Exception
	{
		now = Outside.service(this,"gus06.time.now");
		access = Outside.service(this,"gus06.dir.accessbuilder.txt");
	}
	
	public void p(Object obj) throws Exception
	{
		rootDir = (File) obj;
		holder = access.t(rootDir);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(holder==null) throw new Exception("Holder not initialized yet");
		return ((R) holder).r(key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		String timeStamp = key.equals("now") ? now() : key;
		save(timeStamp, (String) obj);
	}
	
	public Object g() throws Exception
	{
		if(holder==null) throw new Exception("Holder not initialized yet");
		return ((G) holder).g();
	}
	
	
	private void save(String key, String content) throws Exception
	{
		if(holder==null) throw new Exception("Holder not initialized yet");
		((V) holder).v(key, content);
	}
	
	
	private String now() throws Exception
	{return (String) now.g();}
}
