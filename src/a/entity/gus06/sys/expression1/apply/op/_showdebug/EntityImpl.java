package a.entity.gus06.sys.expression1.apply.op._showdebug;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170408";}


	private Service show;
	
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.debug.gui.maingui.show");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return show;
	}
}
