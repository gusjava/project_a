package a.entity.gus06.icon.builder.findparts;

import a.framework.*;
import javax.swing.Icon;


public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20201207";}

	private Service iconLoader;
	private Service iconsToIcon;
	private Service extensionToIcon;
	

	public EntityImpl() throws Exception
	{
		iconLoader = Outside.service(this,"gus06.icon.loader");
		iconsToIcon = Outside.service(this,"gus06.convert.iconstoicon");
		extensionToIcon = Outside.service(this,"gus06.file.ext.icon.os");
	}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(hasKey(key)) return new String[]{key,null};
		
		String[] infos = composeIcon(key);
		if(infos!=null) return infos;
		
		infos = fileIcon(key);
		if(infos!=null) return infos;
		
		return new String[]{null,null};
	}
	
	
	
	private boolean hasKey(String key) throws Exception
	{return iconLoader.f(key);}
	
	
	private String[] composeIcon(String key) throws Exception
	{
		if(!key.contains("_")) return null;
		
		String[] n = key.split("_");
		String id0 = n[n.length-1];
		String id1 = key.substring(0,key.length()-id0.length()-1);
		
		String partId = "PART_"+id0;
		
		boolean hasPart = hasKey(partId);
		if(!hasPart) return null;
		
		boolean hasMain = hasKey(id1);
		if(!hasMain) return null;
		
		return new String[]{id1,partId};
	}
	
	
	private String[] fileIcon(String key) throws Exception
	{
		if(key.equals("DIR")) return new String[]{key,null};
		if(key.startsWith("FILE_")) return new String[]{key,null};
		return null;
	}
}