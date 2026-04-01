package a.entity.gus06.awt.bufferedimage.transform.color.invert.firstband;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}


	private Service perform;
	private Service buildImage;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.renderedimage.transform.color.invert.firstband");
		buildImage = Outside.service(this,"gus06.find.bufferedimage");
		
	}

	public Object t(Object obj) throws Exception
	{
		return buildImage.t(perform.t(buildImage.t(obj)));
	}
}
