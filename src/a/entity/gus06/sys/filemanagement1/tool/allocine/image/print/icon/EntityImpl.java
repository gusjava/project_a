package a.entity.gus06.sys.filemanagement1.tool.allocine.image.print.icon;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201018";}

	private Service printIcon;

	private Icon icon;

	public EntityImpl() throws Exception
	{
		printIcon = Outside.service(this,"gus06.awt.bufferedimage.print.icon.se10");
		icon = (Icon) Outside.resource(this,"icon#WEBSITE_allocine");
	}
	
	public Object t(Object obj) throws Exception
	{
		return printIcon.t(new Object[]{obj,icon});
	}
}
