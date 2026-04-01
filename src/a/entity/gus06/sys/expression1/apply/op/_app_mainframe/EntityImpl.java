package a.entity.gus06.sys.expression1.apply.op._app_mainframe;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}

	public static final String T = "constant";
	

	private JFrame frame;
		
	public EntityImpl() throws Exception
	{
		frame = (JFrame) Outside.resource(this,"mainframe");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return frame;
	}
}
