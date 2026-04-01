package a.entity.gus06.sys.filebackup1.manager;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, R, V, G {

	public String creationDate() {return "20161017";}
	
	private Service performSave;
	private Service performRetrieve;
	private Service performRetrieveAll;
	private Service performRestore;
	
	private File storeDir;
	private Map cache;



	public EntityImpl() throws Exception
	{
		performSave = Outside.service(this,"gus06.sys.filebackup1.perform.save");
		performRetrieve = Outside.service(this,"gus06.sys.filebackup1.perform.retrieve");
		performRetrieveAll = Outside.service(this,"gus06.sys.filebackup1.perform.retrieveall");
		performRestore = Outside.service(this,"gus06.sys.filebackup1.perform.restore");
		
		storeDir = (File) Outside.resource(this,"defaultdir");
		storeDir.mkdirs();
		cache = new HashMap();
	}
	
	private File dir(String key)
	{
		File dir = new File(storeDir,key);
		dir.mkdirs();
		return dir;
	}
	
	private Holder holder(String key)
	{
		if(!cache.containsKey(key))
		cache.put(key,new Holder(dir(key)));
		return (Holder) cache.get(key);
	}
	
	
	public Object g() throws Exception
	{
		Set set = new HashSet();
		File[] ff = storeDir.listFiles();
		for(File f:ff) set.add(f.getName());
		return set;
	}
	
	
	public Object r(String key) throws Exception
	{return holder(key);}
	
	
	public void v(String key, Object obj) throws Exception
	{holder(key).p(obj);}
	
	
	
	private class Holder implements P, G, R, V
	{
		private File dir;
		public Holder(File dir) {this.dir = dir;}
		
		public void p(Object obj) throws Exception
		{performSave.p(new File[]{dir,(File) obj});}
		
		public Object g() throws Exception
		{return performRetrieveAll.t(dir);}
		
		public Object r(String key) throws Exception
		{return performRetrieve.t(new Object[]{dir,key});}
		
		public void v(String key, Object obj) throws Exception
		{performRestore.p(new Object[]{dir,obj,key});}
	}
}
