package a.entity.gus06.sys.editor16x16.clipboard.g.colors;

import a.framework.*;
import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20250314";}


	private Service accessString;

	public EntityImpl() throws Exception
	{
		accessString = Outside.service(this,"gus06.clipboard.access.string");
	}

	
	
	public Object g() throws Exception
	{
		String string = (String) accessString.g();
		if(string!=null) return fromString(string);
		
		return null;
	}
	
	
	
	
	public Object fromString(String s) throws Exception
	{
		if(s.equals("")) return null;
		
		Set colors = new HashSet();
		String[] nn = s.split(";");
		for(String n : nn)
		{
			String[] t = n.split("=");
			String c = computeColor(t[t.length-1]);
			if(c!=null) colors.add(c);
		}
		return colors;
	}
	
	private String computeColor(String c) throws Exception
	{
		if(!c.matches("[0-9]+\\-[0-9]+\\-[0-9]+\\-[0-9]+")) return null;
		return c;
	}
}