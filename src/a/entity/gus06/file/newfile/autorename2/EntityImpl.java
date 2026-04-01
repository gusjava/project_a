package a.entity.gus06.file.newfile.autorename2;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150619";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(!file.exists()) return file;
		
		File parent = file.getParentFile();
		String name = file.getName();
		
		String ext = getExtension(name);
		String name0 = getName0(name,ext);
		int index = getIndex(name0);
		String name1 = getName1(name0,index);
		
		while(file.exists())
		{
			index++;
			file = new File(parent,buildName(name1,ext,index));
		}
		return file;
	}
	
	
	
	
	
	private String getExtension(String name)
	{
		if(!name.contains(".")) return "";
		String[] n = name.split("\\.");
		return n[n.length-1];
	}
	
	private String getName0(String name, String ext)
	{
		if(ext.equals("")) return name;
		return name.substring(0,name.length()-ext.length()-1);
	}
	
	private int getIndex(String name)
	{
		if(!name.contains("_")) return -1;
		String[] n = name.split("_");
		return int_(n[n.length-1]);
	}
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){return -1;}
	}
	
	private String getName1(String name, int index)
	{
		if(index==-1) return name;
		String[] n = name.split("_");
		String s = n[n.length-1];
		return name.substring(0,name.length()-s.length()-1);
	}
	
	
	
	private String buildName(String name, String ext, int index)
	{
		StringBuffer b = new StringBuffer();
		b.append(name);
		b.append("_");
		b.append(format(index));
		if(!ext.equals("")) b.append("."+ext);
		return b.toString();
	}
	
	private String format(int index)
	{return index<10?"0"+index:""+index;}
}
