package a.entity.gus06.convert.colortoborder;

import a.framework.*;
import javax.swing.BorderFactory;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250706";}


	
	public Object t(Object obj) throws Exception
	{
		Color color = (Color) obj;
		return BorderFactory.createLineBorder(color);
	}
}