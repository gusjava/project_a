package a.entity.gus06.file.zip.run.unzip.location2;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.io.FileOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Map;
import java.io.PrintStream;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231205";}
	
	public static final int BUFFER = 2048;
	
	public static final String EXT_REPL = "repl";
	
	public static final String KEY_REPL = "REPL";
	public static final String KEY_TRANS = "TRANS";

	
	private Service buildZipFile;
	private Service isToString;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToString = Outside.service(this,"gus06.io.transfer.tostring.utf8");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=6) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String location = (String) o[2];
		Map replMap = (Map) o[3];
		Object progress = o[4];
		Set interrupt = (Set) o[5];
		
		String ext = (String) get(replMap, KEY_REPL, EXT_REPL);
		T trans = (T) get(replMap, KEY_TRANS, new Replacer(replMap));
		
		ZipFile zipFile = null;
		
		try
		{
			zipFile = (ZipFile) buildZipFile.t(file);
			
			ArrayList list = new ArrayList();
			Enumeration entries = zipFile.entries();
			while(entries.hasMoreElements())
			{
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if(entry.getName().startsWith(location)) list.add(entry);
			}
			
			int rootLen = location.length();
			
			if(progress!=null) ((V)progress).v("size",""+list.size());
			for(int i=0; i<list.size(); i++)
			{
				ZipEntry entry = (ZipEntry) list.get(i);
				String relPath = entry.getName().substring(rootLen);
				
				if(relPath.endsWith("."+ext))
				{
					relPath = relPath.substring(0,relPath.length()-ext.length()-1);
					File entryPath = new File(dir,relPath);
					extractEntry2(zipFile, entry, entryPath, trans);
				}
				else
				{
					File entryPath = new File(dir,relPath);
					extractEntry(zipFile, entry, entryPath);
				}
				if(progress!=null) ((E)progress).e();
				if(interrupt!=null && !interrupt.isEmpty()) break;
			}
		}
		finally
		{
			if(zipFile!=null) zipFile.close();
		}
	}
	
	
	
	private void extractEntry(ZipFile zipFile, ZipEntry entry, File entryPath)
	{
		try
		{
			if(entry.getSize()<0)
				throw new Exception("Invalid entry detected: "+entry.getName()+" (size="+entry.getSize()+")");
			
			if(entry.isDirectory())
			{
				entryPath.mkdirs();
				return;
			}
			
			entryPath.getParentFile().mkdirs();
			entryPath.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(entryPath);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			InputStream is = zipFile.getInputStream(entry);
			
			copyInputStream(is,bos);
		}
		catch(Exception e)
		{Outside.err(this,"extractEntry(ZipFile,ZipEntry,File)",e);}
	}
	
	
	private void extractEntry2(ZipFile zipFile, ZipEntry entry, File entryPath, T trans)
	{
		try
		{
			if(entry.getSize()<0)
				throw new Exception("Invalid entry detected: "+entry.getName()+" (size="+entry.getSize()+")");
			
			if(entry.isDirectory())
			{
				entryPath.mkdirs();
				return;
			}
			InputStream is = zipFile.getInputStream(entry);
			String s = (String) isToString.t(is);
			is.close();
			
			entryPath.getParentFile().mkdirs();
			PrintStream p = new PrintStream(entryPath);
			p.print((String) trans.t(s));
			p.close();
		}
		catch(Exception e)
		{Outside.err(this,"extractEntry2(ZipFile,ZipEntry,File,T)",e);}
	}


	private void copyInputStream(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;
		while((len = in.read(buffer)) >= 0) out.write(buffer,0,len);
		in.close();
		out.close();
	}
	
	
	
	private class Replacer implements T
	{
		private Map m;
		public Replacer(Map m) {this.m = m;}
	
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			Iterator it = m.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) m.get(key);
				s = s.replace("{"+key+"}",value);
			}
			return s;
		}
	}
	
	
	private Object get(Map m, String key, Object defaultValue)
	{
		if(!m.containsKey(key)) return m.get(key);
		return defaultValue;
	}
}