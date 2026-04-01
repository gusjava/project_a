package a.entity.gus06.font.availablefontnames.array;

import a.framework.*;
import java.awt.GraphicsEnvironment;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190509";}

	private String[] names;

	public EntityImpl() throws Exception
	{
		names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	}
	
	public Object g() throws Exception
	{return names;}
}

