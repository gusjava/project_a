package a.entity.gus06.font.availablefontnames.list;

import a.framework.*;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190509";}
	
	private List list;

	public EntityImpl() throws Exception
	{
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		list = new ArrayList();
		for(String name:names)
		list.add(name);
	}
	
	public Object g() throws Exception
	{return list;}
}
