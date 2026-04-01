package a.entity.gus06.web.allocine.convert.videofiletomovie2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}


	private Service queryToMovie;
	private Service performSearch;

	public EntityImpl() throws Exception
	{
		queryToMovie = Outside.service(this,"gus06.web.allocine.convert.query1tomovie2");
		performSearch = Outside.service(this,"gus06.web.allocine.convert.videofiletosearch");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object map = performSearch.t(obj);
		return queryToMovie.t(map);
	}
}
