package a.entity.gus06.sys.expression1.apply.op._mouse;

import a.framework.*;
import java.util.Date;
import java.awt.Component;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}


	private Service mousePosition;
	private Service compMousePosition;
	private Service findComp;
	
	public EntityImpl() throws Exception
	{
		mousePosition = Outside.service(this,"gus06.mouse.position");
		compMousePosition = Outside.service(this,"gus06.swing.comp.mouse.position");
		findComp = Outside.service(this,"gus06.find.jcomponent");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return mousePosition.g();
		if(obj instanceof Component) return compMousePosition.t(obj);
		if(obj instanceof I) return compMousePosition.t((JComponent) findComp.t(obj));
		
		return mousePosition.g();
	}
}
