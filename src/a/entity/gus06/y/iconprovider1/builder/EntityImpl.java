package a.entity.gus06.y.iconprovider1.builder;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250727";}

	private Service iconsToIcon;
	private Service extensionToIcon;

	public EntityImpl() throws Exception
	{
		iconsToIcon = Outside.service(this,"gus.y.convert1.iconstoicon");
		extensionToIcon = Outside.service(this,"gus06.file.ext.icon.os");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Builder((R) obj);}
	
	
	
	private class Builder implements T, R
	{
		private R iconLoader;
		
		public Builder(R iconLoader)
		{this.iconLoader = iconLoader;}
	
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
		
		public Object r(String key) throws Exception
		{
			Icon icon = load(key);
			if(icon!=null) return icon;
			
			icon = composeIcon(key);
			if(icon!=null) return icon;
			
			icon = fileIcon(key);
			if(icon!=null) return icon;
			
			return null;
		}
	
		private Icon load(String key) throws Exception
		{return (Icon) iconLoader.r(key);}
		
		
		private Icon composeIcon(String key) throws Exception
		{
			if(!key.contains("_")) return null;
			
			String[] n = key.split("_");
			String id0 = n[n.length-1];
			String id1 = key.substring(0,key.length()-id0.length()-1);
			
			Icon icon0 = load("PART_"+id0);
			if(icon0==null) return null;
			
			Icon icon1 = load(id1);
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
	
}