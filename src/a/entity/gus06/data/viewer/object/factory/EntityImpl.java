package a.entity.gus06.data.viewer.object.factory;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}


	private Service newViewer;

	public EntityImpl() throws Exception
	{
		newViewer = Outside.service(this,"factory#gus06.data.viewer.object");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object viewer = newViewer.g();
		((P)viewer).p(obj);
		return viewer;
	}
}
