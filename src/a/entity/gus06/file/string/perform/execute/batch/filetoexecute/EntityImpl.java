package a.entity.gus06.file.string.perform.execute.batch.filetoexecute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260313";}


	private Service perform;
	private Service wrap;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.desktop.open");
		wrap = Outside.service(this,"gus06.feature.wrap.po.e");
	}
	
	public Object t(Object obj) throws Exception
	{return wrap.t(new Object[]{perform,obj});}
}
