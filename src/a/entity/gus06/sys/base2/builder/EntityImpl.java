package a.entity.gus06.sys.base2.builder;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150522";}

	public static final String PATH_DATA = "path.data";
	public static final String PATH_BACKUP = "path.backup";
	public static final String PATH_INDEX = "path.index";
	public static final String PATH_STRUCTURE = "path.structure";
	
	
	
	private Service accessBuilder;
	private Service backupBuilder;
	private Service randomId;
	private Service getStateMap;


	public EntityImpl() throws Exception
	{
		accessBuilder = Outside.service(this,"gus06.dir.accessbuilder.properties");
		backupBuilder = Outside.service(this,"gus06.dir.backup.manager");
		randomId = Outside.service(this,"gus06.data.generate.string.random.number14");
		getStateMap = Outside.service(this,"gus06.sys.base2.builder.datamap");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	
	private String randomId() throws Exception
	{return (String) randomId.g();}
	
	
	
	private class Holder extends S1 implements R, V, F, G
	{
		private File dir;
		private Map state;
		
		private Object access;
		private Object backup;
		
		
		public Holder(File dir) throws Exception
		{
			this.dir = dir;
			this.state = (Map) getStateMap.t(dir);
			
			File dataDir = (File) state.get(PATH_DATA);
			File backupDir = (File) state.get(PATH_BACKUP);
			File indexDir = (File) state.get(PATH_INDEX);
			File structureDir = (File) state.get(PATH_STRUCTURE);
			
			access = accessBuilder.t(dataDir);
			backup = backupBuilder.t(new File[]{dataDir,backupDir});
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("backup")) return backup;
			if(ismap(key)) return ((R)access).r(w(key));
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("map"))
			{((V)access).v(randomId(),obj);return;}
			
			if(ismap(key))
			{((V)access).v(w(key),obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String key = (String) obj;
			if(ismap(key)) return ((F)access).f(w(key));
			throw new Exception("Unknown key: "+key);
		}
		
		public Object g() throws Exception
		{return ((G)access).g();}
		
		
		
		private String w(String s)
		{return s.substring(4);}
		
		private boolean ismap(String s)
		{return s.startsWith("map_");}
	}
}