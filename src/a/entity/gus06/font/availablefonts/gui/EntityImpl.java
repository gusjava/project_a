package a.entity.gus06.font.availablefonts.gui;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191112";}


	private Service viewer;
	private Service find;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.fontarray");
		find = Outside.service(this,"gus06.font.availablefonts.array.p12");
		viewer.p(find.g());
	}
	
	public Object i() throws Exception
	{return viewer.i();}
}
