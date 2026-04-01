package a.entity.gus06.font.availablefonts.array.p20;

import a.framework.*;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.ArrayList;
import java.awt.Font;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190509";}
	
	
	private Font[] fonts;


	public EntityImpl() throws Exception
	{
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fonts = new Font[names.length];
		for(int i=0;i<names.length;i++)
		fonts[i] = new Font(names[i],Font.PLAIN,20);
	}
	
	
	public Object g() throws Exception
	{return fonts;}
}
