package a.entity.gus06.sys.expression1.apply.op._is_visible;

import a.framework.*;
import java.awt.Window;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160916";}


	private Service findWindow;
	
	public EntityImpl() throws Exception
	{
		findWindow = Outside.service(this,"gus06.find.window");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		
		Window window = (Window) findWindow.t(obj);
		return Boolean.valueOf(window.isVisible());
	}
}
