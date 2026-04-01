package a.entity.gus06.font.couriernew;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190316";}

	public static Font FONT = new Font("Courier New", Font.PLAIN, 12);
	
	public Object g() throws Exception
	{return FONT;}
}
