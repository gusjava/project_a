package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.detail;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20260826";}

	private Service engine;
	private Service editor;

	private String entityName;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.z.appli1.gui2_3_3.y.detail.entities.engine");
		editor = Outside.service(this, "*gus.y.entityeditor1.maingui");
	}

	public Object i() throws Exception
	{
		return editor.i();
	}

	public void p(Object obj) throws Exception
	{
		entityName = (String) obj;
		editor.p(new Object[] {engine, entityName});
	}
}
