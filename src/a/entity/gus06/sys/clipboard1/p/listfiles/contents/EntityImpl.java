package a.entity.gus06.sys.clipboard1.p.listfiles.contents;

import a.framework.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151017";}


	private Service access;
	private Service findList;
	
	private Service isAllText;
	private Service isText;
	private Service isImage;
	
	private Service readText;
	private Service readImage;
	
	
	
	
	
	public EntityImpl() throws Exception
	{
		access = Outside.service(this,"gus06.clipboard.access");
		findList = Outside.service(this,"gus06.find.filelist");
		
		isAllText = Outside.service(this,"gus06.sys.filetype1.list.all.text");
		isText = Outside.service(this,"gus06.sys.filetype1.is.text");
		isImage = Outside.service(this,"gus06.sys.filetype1.is.image");
		
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
		readImage = Outside.service(this,"gus06.file.read.image.generic");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		List files = (List) findList.t(obj);
		Object content = listToContent(files);
		if(content!=null) access.p(content);
	}
	
	
	
	private Object listToContent(List files) throws Exception
	{
		if(files.size()==0)	return null;
		if(files.size()==1)	return fileToContent((File) files.get(0));
		if(isAllText.f(files))	return listToString(files);
		
		return files;
	}
	
	
	
	private Object fileToContent(File file) throws Exception
	{
		if(file.isDirectory()) return dirToContent(file);
		if(isText.f(file)) return readText.t(file);
		if(isImage.f(file)) return readImage.t(file);
		return file;
	}
	
	
	private Object dirToContent(File dir)
	{
		File[] ff = dir.listFiles();
		List list = new ArrayList();
		for(File f:ff) list.add(f);
		return list;
	}
	
	
	private String listToString(List files) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<files.size();i++)
		{
			File f = (File) files.get(i);
			String s = (String) readText.t(f);
			b.append(s.trim()+"\n\n");
		}
		return b.toString().trim();
	}
}
