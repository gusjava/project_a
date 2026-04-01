package a.entity.gus06.awt.bufferedimage.transform.color.hue.yellow;

import java.awt.Color;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151004";}

	public static final Color COLOR = Color.YELLOW;
	
	
	private Service changeHue;
	
	public EntityImpl() throws Exception
	{
		changeHue = Outside.service(this,"gus06.awt.bufferedimage.transform.color.hue");
	}

	public Object t(Object obj) throws Exception
	{
		return changeHue.t(new Object[]{obj,COLOR});
	}
}
