package a.entity.gus06.font.availablefonts.list.p20;

import a.framework.*;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.ArrayList;
import java.awt.Font;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190509";}
	
	
	private List list;


	public EntityImpl() throws Exception
	{
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		list = new ArrayList();
		for(String name:names)
		list.add(new Font(name,Font.PLAIN,20));
	}
	
	
	public Object g() throws Exception
	{return list;}
}
