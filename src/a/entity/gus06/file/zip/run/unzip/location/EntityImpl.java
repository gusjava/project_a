package a.entity.gus06.file.zip.run.unzip.location;

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

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231205";}
	
	public static final int BUFFER = 2048;

	
	private Service buildZipFile;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File dir = (File) o[1];
		String location = (String) o[2];
		Object progress = o[3];
		Set interrupt = (Set) o[4];
		
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
				
				File entryPath = new File(dir,relPath);
				extractEntry(zipFile,entry,entryPath);
				
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



	
	
	private void copyInputStream(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;
		while((len = in.read(buffer)) >= 0) out.write(buffer,0,len);
		in.close();
		out.close();
	}
}