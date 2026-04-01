package a.entity.gus06.web.allocine.convert.videofiletoresultlist;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201020";}


	private Service toQuery;
	private Service queryToResultList;

	public EntityImpl() throws Exception
	{
		toQuery = Outside.service(this,"gus06.web.allocine.convert.videofiletoquery1");
		queryToResultList = Outside.service(this,"gus06.web.allocine.convert.query1toresultlist");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) toQuery.t(obj);
		return queryToResultList.t(query);
	}
}
