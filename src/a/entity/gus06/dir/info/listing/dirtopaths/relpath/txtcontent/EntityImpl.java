package a.entity.gus06.dir.info.listing.dirtopaths.relpath.txtcontent;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251229";}


	private Service isOfTxtFile;
	private Service readString;

	public EntityImpl() throws Exception
	{
		isOfTxtFile = Outside.service(this,"gus06.file.filter.mime.isoftype.text.plain");
		readString = Outside.service(this,"gus06.file.read.string.autodetect");
		
	}

	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		int len = dir.getAbsolutePath().length()+1;
		
		StringBuffer b = new StringBuffer();
    		handleDir(b,dir,len);
		return b.toString().trim();
	}
	
	private void handleDir(StringBuffer b, File dir, int len) throws Exception
	{
		File[] ff = dir.listFiles();
		if(ff!=null) for(File f:ff)
		{
			String path = f.getAbsolutePath().substring(len);
			b.append(path+"\n");
			if(f.isFile()) handleFile(b,f);
			if(f.isDirectory()) handleDir(b,f,len);
		}
	}
	
	private void handleFile(StringBuffer b, File file) throws Exception
	{
		if(!isOfTxtFile.f(file)) return;
		
		b.append("_____________________________\n");
		b.append(readString(file)+"\n");
		b.append("_____________________________\n");
	}
	
	private String readString(File file) throws Exception
	{
		String s = (String) readString.t(file);
		return s.trim();
	}
}
