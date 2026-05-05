package a.entity.gus06.icon.builder2;

import a.framework.*;
import javax.swing.Icon;
import java.io.File;


public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20200828";}

	private Service iconBuilder;
	private Service iconLoader2;
	private Service iconsToIcon;
	private Service extensionToIcon;
	

	public EntityImpl() throws Exception
	{
		iconBuilder = Outside.service(this,"gus06.icon.builder");
		iconLoader2 = Outside.service(this,"gus06.icon.loader2");
		iconsToIcon = Outside.service(this,"gus.y.convert1.iconstoicon");
		extensionToIcon = Outside.service(this,"gus06.file.ext.icon.os");
	}
	
	
	public Object r(String key) throws Exception
	{return iconBuilder.t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		return build((File) o[0], (String) o[1]);
	}
	
	
	private Icon load(File dir, String key) throws Exception
	{return (Icon) iconLoader2.t(new Object[]{dir,key});}
	
	
	
	private Icon build(File dir, String key) throws Exception
	{
		if(dir==null) return (Icon) iconBuilder.t(key);
	
		Icon icon = load(dir,key);
		if(icon!=null) return icon;
		
		icon = composeIcon(dir,key);
		if(icon!=null) return icon;
		
		icon = fileIcon(key);
		if(icon!=null) return icon;
		
		return null;
	}
	
	
	
	
	private Icon composeIcon(File dir, String key) throws Exception
	{
		if(!key.contains("_")) return null;
		
		String[] n = key.split("_");
		String id0 = n[n.length-1];
		String id1 = key.substring(0,key.length()-id0.length()-1);
		
		Icon icon0 = load(dir,"PART_"+id0);
		if(icon0==null) return null;
		
		Icon icon1 = load(dir,id1);
		if(icon1==null) return null;
		
		return (Icon) iconsToIcon.t(new Icon[]{icon1,icon0});
	}
	
	
	private Icon fileIcon(String key) throws Exception
	{
		if(key.equals("DIR")) return (Icon) extensionToIcon.g();
		if(key.startsWith("FILE_")) return (Icon) extensionToIcon.t(key.substring(5));
		return null;
	}
}
