package a.entity.gus06.clipboard.access.string.or.filepaths;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200102";}


	private Service accessListFiles;
	private Service accessString;


	public EntityImpl() throws Exception
	{
		accessListFiles = Outside.service(this,"gus06.clipboard.access.listfiles");
		accessString = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public Object g() throws Exception
	{
		String s = (String) accessString.g();
		if(s!=null) return s;
		
		List list = (List) accessListFiles.g();
		if(list==null) return "";
		if(list.isEmpty()) return "";
		
		if(list.size()==1)
		{
			File file = (File) list.get(0);
			return normalizedPath(file);
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			File file = (File) list.get(i);
			b.append(normalizedPath(file)+"\n");
		}
		return b.toString();
	}
	
	
	private String normalizedPath(File file)
	{return file.getAbsolutePath().replace(File.separator,"/");}
}
