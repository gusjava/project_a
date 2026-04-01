package a.entity.gus06.convert.stringtocolor.name;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170226";}


	
	
	public Object t(Object obj) throws Exception
	{return stringToColor((String) obj);}
	
	
	
	
	private Color stringToColor(String s) throws Exception
	{
		if(s==null) return null;
		if(s.equals("")) return null;
		
		s = s.toLowerCase().trim();
		if(s.startsWith("color.")) s = s.substring(6);
		
		if(s.endsWith(".darker"))
		{
			s = s.substring(0,s.length()-7);
			return stringToColor(s).darker();
		}
		
		if(s.endsWith(".brighter"))
		{
			s = s.substring(0,s.length()-9);
			return stringToColor(s).brighter();
		}
        
		if(s.equals("blue"))		return Color.BLUE;
		if(s.equals("red"))		return Color.RED;
		if(s.equals("black"))		return Color.BLACK;
		if(s.equals("cyan"))		return Color.CYAN;
		if(s.equals("dark_gray"))	return Color.DARK_GRAY;
		if(s.equals("darkgray"))	return Color.DARK_GRAY;
		if(s.equals("gray"))		return Color.GRAY;
		if(s.equals("green"))		return Color.GREEN;
		if(s.equals("light_gray"))	return Color.LIGHT_GRAY;
		if(s.equals("lightgray"))	return Color.LIGHT_GRAY;
		if(s.equals("magenta"))		return Color.MAGENTA;
		if(s.equals("orange"))		return Color.ORANGE;
		if(s.equals("pink"))		return Color.PINK;
		if(s.equals("white"))		return Color.WHITE;
		if(s.equals("yellow"))		return Color.YELLOW;
        
		return null;
	}
}
