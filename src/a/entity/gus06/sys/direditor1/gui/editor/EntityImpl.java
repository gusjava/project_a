package a.entity.gus06.sys.direditor1.gui.editor;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20200531";}


	private Service editor;
	private Service buildListing;
	private Service perform;

	private File dir;
	private List list;
	private Map map;
	private String text;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		perform = Outside.service(this,"gus06.sys.direditor1.perform");
	}
	
	
	public Object i() throws Exception
	{
		return editor.i();
	}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		int len = dir.getAbsolutePath().length();
		
		list = (List) buildListing.t(dir);
		map = new HashMap();
		
		int nb = list.size();
		int rank = (""+nb).length();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String index = getIndex(i,rank);
			File f = (File) list.get(i);
			String path = f.getAbsolutePath().substring(len);
			
			map.put(index,f);
			b.append(index+" "+path+"\n");
		}
		text = b.toString();
		editor.p(text);
	}
	
	
	private String getIndex(int i, int rank)
	{
		String s = ""+(i+1);
		while(s.length()<rank) s = "0"+s;
		return s;
	}
	
	
	
	public Object g() throws Exception
	{
		String text = (String) editor.g();
		String[] lines = text.split("\n",-1);
		
		Set pathDone = new HashSet();
		List list = new ArrayList();
		
		for(String line : lines)
		{
			line = line.trim();
			if(!line.equals(""))
			{
				String[] k = line.split(" ",2);
				if(k.length!=2) throw new Exception("Invalid line: "+line);
				
				String index = k[0];
				String path = k[1];
				
				if(pathDone.contains(path)) throw new Exception("Path found many times: "+path);
				if(!map.containsKey(index)) throw new Exception("Unknown file index: "+index);
				pathDone.add(path);
				
				File input = (File) map.get(index);
				File output = new File(dir,path);
				
				list.add(new File[]{input,output});
			}
		}
		
		return perform.t(new Object[]{dir,new ArrayList(list)});
	}
}
