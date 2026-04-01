package a.entity.gus06.web.allocine.convert.videofiletodata1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}


	private Service toQuery;
	private Service queryToData;

	public EntityImpl() throws Exception
	{
		toQuery = Outside.service(this,"gus06.web.allocine.convert.videofiletoquery1");
		queryToData = Outside.service(this,"gus06.web.allocine.convert.query1todata1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String query = (String) toQuery.t(obj);
		return queryToData.t(query);
	}
}
