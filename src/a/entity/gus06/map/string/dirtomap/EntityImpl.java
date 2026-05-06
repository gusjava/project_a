package a.entity.gus06.map.string.dirtomap;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}


	private Service readFile;


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.isDirectory())
			throw new Exception("Invalid directory: "+dir);
		return new Map1(dir);
	}
	
	
	
	
	private class Map1 implements Map
	{
		private File dir;
		
		public Map1(File dir)
		{this.dir = dir;}
		
		
		private File file(Object key)
		{
			if(key==null) return null;
			return new File(dir,(String) key);
		}
		
		private File[] listing()
		{return dir.listFiles();}
		
		
		
		public void clear()
		{
			File[] ff = listing();
			for(File f:ff) delete(f);
		}
		
		public boolean containsKey(Object key)
		{return file(key).exists();}
		
		public boolean containsValue(Object value)
		{return false;}
		
		public Set entrySet()
		{return null;}
		
		public Object get(Object key)
		{return read(file(key));}
		
		public boolean isEmpty()
		{return listing().length==0;}
		
		public Set keySet()
		{return filesToSet(listing());}
		
		
		public Object put(Object key, Object value)
		{
			File f = file(key);
			if(f==null) return null;
			String oldValue = read(f);
			save(f,(String) value);
			return oldValue;
		}
		
		public void putAll(Map m)
		{
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				Object key = it.next();
				Object value = m.get(key);
				save(file(key),(String) value);
			}
		}
		
		
		public Object remove(Object key)
		{
			File f = file(key);
			if(f==null) return null;
			String oldValue = read(f);
			delete(f);
			return oldValue;
		}
		
		
		public int size()
		{return listing().length;}
		
		
		public Collection values()
		{return null;}
		
		
		
		
		private synchronized void save(File f, String value)
		{
			try
			{
				PrintStream p = new PrintStream(f);
				p.print(value);
				p.close();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"save(File,String)",e);}
		}
	
	
		private synchronized void delete(File f)
		{
			try
			{
				boolean done = f.delete();
				if(!done) throw new Exception("Failed to delete file: "+f);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"delete(File)",e);}
		}
	
	
	
		private synchronized void delete(File[] ff)
		{
			for(File f:ff) delete(f);
		}
	
	
		private synchronized String read(File f)
		{
			try
			{
				if(f==null) return null;
				return (String) readFile.t(f);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"read(File)",e);}
			return null;
		}
	
	
		private Set filesToSet(File[] ff)
		{
			Set keys = new HashSet();
			for(File f:ff) keys.add(f.getName());
			return keys;
		}
	}
}
