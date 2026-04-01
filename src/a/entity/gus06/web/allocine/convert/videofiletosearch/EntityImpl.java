package a.entity.gus06.web.allocine.convert.videofiletosearch;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191024";}


	private Service toQuery;
	private Service search;

	public EntityImpl() throws Exception
	{
		toQuery = Outside.service(this,"gus06.web.allocine.convert.videofiletoquery1");
		search = Outside.service(this,"gus06.web.allocine.convert.query1tosearch");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) toQuery.t(obj);
		return search.t(query);
	}
}
