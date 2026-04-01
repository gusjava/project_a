package a.entity.gus06.app.persister1.data.filelist;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20141210";}


	private Service persister;

	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	public Object r(String key) throws Exception
	{return stringToList((String) persister.r(key));}
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,listToString((List) obj));}
	
	
	
	
	
	private List stringToList(String s)
	{
		List l = new ArrayList();
		if(s==null) return l;
		if(s.equals("")) return l;
		
		String[] n = s.trim().split("\n");
		for(int i=0;i<n.length;i++)
		{
			File file = stringToFile(n[i]);
			if(file!=null) l.add(file);
		}
		return l;
	}
	
	
	private String listToString(List l)
	{
		if(l==null) return null;
		StringBuffer b = new StringBuffer();
		for(int i=0;i<l.size();i++)
		{
			File file = (File) l.get(i);
			String path = fileToString(file);
			if(path!=null) b.append(path+"\n");
		}
		return b.toString();
	}
	
	
	
	
	
	
	private String fileToString(File f)
	{
		if(f==null) return null;
		return f.getAbsolutePath();
	}
	
	
	private File stringToFile(String s)
	{
		if(s==null || s.equals("")) return null;
		return new File(s);
	}
		
}