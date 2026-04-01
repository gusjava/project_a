package a.entity.gus06.web.allocine.convert.videofiletoimage;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191024";}


	private Service mapToImage;
	private Service performSearch;

	public EntityImpl() throws Exception
	{
		mapToImage = Outside.service(this,"gus06.web.allocine.convert.postertoimage");
		performSearch = Outside.service(this,"gus06.web.allocine.convert.videofiletosearch");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object map = performSearch.t(obj);
		return mapToImage.t(map);
	}
}
