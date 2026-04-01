package a.entity.gus06.file.read.properties.autosaver.strict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Properties;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161208";}
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		
		if(f==null) return null;
		if(f.isDirectory()) return null;
		
		if(!f.exists()) f.createNewFile();
		
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(f);
		p.load(fis);
		fis.close();
		
		return new Map1(f,p);
	}
	
	
	private void saveFile(File f, Properties p)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(f);
			p.store(fos,"");
			fos.close();
		}
		catch(Exception e)
		{Outside.err(this,"saveFile(File,Properties)",e);}
	}
	
	
	private class Map1 implements Map
	{
		private File f;
		private Properties p;
		
		public Map1(File f, Properties p)
		{
			this.f = f;
			this.p = p;
		}
		
		public int size() 				{return p.size();}
		public boolean isEmpty() 			{return p.isEmpty();}
		public boolean containsKey(Object key) 		{return p.containsKey(key);}
		public boolean containsValue(Object value) 	{return p.containsValue(value);}
		public Set keySet()				{return p.keySet();}
		public Collection values()			{return p.values();}
		public Set entrySet() 				{return p.entrySet();}
		public Object get(Object key)			{return p.get(key);}
		
		
		public Object put(Object key, Object value)
		{
			Object r = p.put(key,value);
			saveFile(f,p);
			return r;
		}
		
		public Object remove(Object key)
		{
			Object r = p.remove(key);
			saveFile(f,p);
			return r;
		}
	
		public void putAll(Map m)
		{
			p.putAll(m);
			saveFile(f,p);
		}
		
		public void clear()
		{
			p.clear();
			saveFile(f,p);
		}
	}
}
