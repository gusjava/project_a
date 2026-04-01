package a.entity.gus06.font.derivefont;

import a.framework.*;
import java.awt.Font;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Font font = (Font) o[0];
		String rule = (String) o[1];
		
		String[] nn = rule.split(" ");
		for(String n:nn) font = derive(font,n);
		return font;
	}
	
	
	
	private Font derive(Font font, String value) throws Exception
	{
		if(value.equals("bold"))
			return font.deriveFont(Font.BOLD);
		if(value.equals("italic"))
			return font.deriveFont(Font.ITALIC);
		if(value.equals("plain"))
			return font.deriveFont(Font.PLAIN);
	
		try
		{
			int size = Integer.parseInt(value);
			return font.deriveFont((float) size);
		}
		catch(NumberFormatException e) {}
		
		throw new Exception("Unsupported font derive value: "+value);
	}
}
