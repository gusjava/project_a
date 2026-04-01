package a.entity.gus06.appli.chessgame.tool.findcolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150411";}

	public static final Color DARK = Color.LIGHT_GRAY;
	public static final Color LIGHT = Color.WHITE;
	
	public static final Color DARK2 = new Color(153,204,255);
	public static final Color LIGHT2 = new Color(255,255,240);
	
	
	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int sum = t[0]+t[1];
		
		return sum%2==0?LIGHT2:DARK2;
	}
}
