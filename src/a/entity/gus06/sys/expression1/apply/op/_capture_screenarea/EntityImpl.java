package a.entity.gus06.sys.expression1.apply.op._capture_screenarea;

import a.framework.*;
import java.awt.Rectangle;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200510";}


	private Service printScreen;
	private Service findRect;
	
	
	public EntityImpl() throws Exception
	{
		printScreen = Outside.service(this,"gus06.awt.robot.printscreen");
		findRect = Outside.service(this,"gus06.find.rectangle");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof List) 
			return printScreen.t(findRect.t(obj));
		if(obj instanceof int[]) 
			return printScreen.t(findRect.t(obj));
		if(obj instanceof Rectangle)
			return printScreen.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
}
