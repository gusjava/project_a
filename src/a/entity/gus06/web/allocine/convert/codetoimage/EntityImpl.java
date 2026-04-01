package a.entity.gus06.web.allocine.convert.codetoimage;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191024";}


	private Service mapToImage;
	private Service perform;

	public EntityImpl() throws Exception
	{
		mapToImage = Outside.service(this,"gus06.web.allocine.convert.postertoimage");
		perform = Outside.service(this,"gus06.web.allocine.convert.codetomovie1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object map = perform.t(obj);
		return mapToImage.t(map);
	}
}
