package a.entity.gus06.tostring.rectangle;

import a.framework.*;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161203";}

    
	public Object t(Object obj) throws Exception
	{
		Rectangle r = (Rectangle) obj;
		return r.x+" "+r.y+" "+r.width+" "+r.height;
	}
}
