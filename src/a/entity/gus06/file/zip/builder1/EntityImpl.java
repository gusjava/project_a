package a.entity.gus06.file.zip.builder1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.Enumeration;
import java.util.Vector;

import a.framework.*;
import java.util.ArrayList;


public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20150329";}

	public static final int BUFFER = 2048;
	public static final Charset CHARSET = Charset.forName("Cp437");
	
	
	private Service buildZipFile;
	
	private File input;
	private File output;
	private Object progress;
	private int level = 7;
	
	
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		
	}

	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("input"))
		{input = (File)obj;return;}
		
		if(key.equals("output"))
		{output = (File)obj;return;}
		
		if(key.equals("progress"))
		{progress = obj;return;}
		
		if(key.equals("level"))
		{level = Integer.parseInt((String)obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(input==null)		throw new Exception("Input is undefined");
		if(output==null)	throw new Exception("Output is undefined");
		
		String cmd = (String) obj;
		if(cmd.equals("zip")){zip();return;}
		if(cmd.equals("unzip")){unzip();return;}
		
		throw new Exception("Unknown command: "+cmd);
	}
	
	
	
	
	private void zip() throws Exception
	{	 
		File[] inputFiles = null;
		String inputRoot = null;
		
		if(input.isFile())
		{
			inputFiles = new File[]{input};
			inputRoot = input.getParent();
		}
		if(input.isDirectory())
		{
			inputFiles = directoryContent(input);
			inputRoot = input.getParent();
		}
		
		FileOutputStream fos = new FileOutputStream(output);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ZipOutputStream zos = new ZipOutputStream(bos,CHARSET);
		
		zos.setMethod(ZipOutputStream.DEFLATED);
		zos.setLevel(level);
		
		if(progress!=null) ((V)progress).v("size",""+inputFiles.length);
		for(int i=0; i<inputFiles.length; i++)
		{
			addEntry(zos,inputFiles[i],inputRoot);
			if(progress!=null) ((E)progress).e();
		}
		zos.finish();
		zos.close();
	}
	
	
	
	
	private void unzip() throws Exception
	{
		if(!input.isFile())
			throw new Exception("Invalid input: "+input);
		if(!output.isDirectory())
			throw new Exception("Invalid output: "+output);
		
		ZipFile zipFile = (ZipFile) buildZipFile.t(input);
		
		ArrayList list = new ArrayList();
		Enumeration entries = zipFile.entries();
		while(entries.hasMoreElements())
		{list.add(entries.nextElement());}
		
		if(progress!=null) ((V)progress).v("size",""+list.size());
		for(int i=0; i<list.size(); i++)
		{
			ZipEntry entry = (ZipEntry) list.get(i);
			File entryPath = new File(output,entry.getName());
			extractEntry(zipFile,entry,entryPath);
			if(progress!=null) ((E)progress).e();
		}
		zipFile.close();
	}
	
	




	private File[] directoryContent(File dir)
	{
		Vector vec = new Vector();
		File[] child = dir.listFiles();
		for(int i=0;i<child.length;i++)
		buildDirectoryContent(vec, child[i]);
		
		File[] content = new File[vec.size()];
		for(int i=0;i<vec.size();i++)
			content[i] = (File)vec.get(i);
		return content;
	}
	
	
	private void buildDirectoryContent(Vector vec, File file)
	{
		vec.add(file);
		if(file.isDirectory())
		{
			File[] child = file.listFiles();
			for(int i=0;i<child.length;i++)
			buildDirectoryContent(vec, child[i]);
		}
	}
	
	
	
	
	
	private void addEntry(ZipOutputStream zos, File inputFile, String inputRoot)
	{
		try
		{
			String entryName = inputFile.getAbsolutePath().substring(inputRoot.length());
			entryName = entryName.replace(File.separatorChar,'/');
			
			if(entryName.startsWith("/"))
				entryName = entryName.substring(1);
			
			if(inputFile.isDirectory())
			if(!entryName.endsWith("/"))
				entryName = entryName+"/";
			
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			
			if(inputFile.isFile())
			{
				int b;
				byte data[] = new byte[BUFFER];
				FileInputStream fis = new FileInputStream(inputFile);
				while((b = fis.read(data, 0, BUFFER)) != -1)
				{zos.write(data, 0, b);}
				fis.close();
			}
			zos.closeEntry();
		}
		catch(Exception e)
		{Outside.err(this,"addEntry(ZipOutputStream,File,String)",e);}
	}
	
		

	private void extractEntry(ZipFile zipFile, ZipEntry entry, File entryPath)
	{
		try
		{
			if(entry.getSize()<0)
				throw new Exception("invalid entry detected: "+entry.getName()+" (size="+entry.getSize()+")");
			
			entryPath.getParentFile().mkdirs();

			if(entry.isDirectory())
			{
				entryPath.mkdir();
				return;
			}
			FileOutputStream fos = new FileOutputStream(entryPath);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			InputStream is = zipFile.getInputStream(entry);
			
			copyInputStream(is,bos);
		}
		catch(Exception e)
		{Outside.err(this,"extractEntry(ZipFile,ZipEntry,File)",e);}
	}



	
	
	public static final void copyInputStream(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;
		while((len = in.read(buffer)) >= 0) out.write(buffer,0,len);
		in.close();
		out.close();
	}
	
	
	
	
	
	
	
	
	/*
	 * CETTE METHODE EST CORROMPUE, POURQUOI ???
	 */
	private void unzip2() throws Exception
	{
		if(!input.isFile())
			throw new Exception("Invalid input: "+input);
		if(!output.isDirectory())
			throw new Exception("Invalid output: "+output);
		
		FileInputStream fis = new FileInputStream(input);
		BufferedInputStream bis = new BufferedInputStream(fis);
		ZipInputStream zis = new ZipInputStream(bis,CHARSET);
		
		ZipEntry entry;
		while((entry = zis.getNextEntry()) != null)
		{ 
			File entryPath = new File(output,entry.getName());
			
			if(entry.isDirectory())
			{
				entryPath.mkdirs();
			}
			else
			{
				if(!entryPath.getParentFile().exists())
					entryPath.getParentFile().mkdirs();

				FileOutputStream fos = new FileOutputStream(entryPath);
				BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER);

				int b;
				byte data[] = new byte[BUFFER];
				while ((b = zis.read(data, 0, BUFFER)) != -1) 
				{bos.write(data, 0, BUFFER);}
				
				bos.flush();
				bos.close();
			}	
		}
		zis.close();
	}
}
