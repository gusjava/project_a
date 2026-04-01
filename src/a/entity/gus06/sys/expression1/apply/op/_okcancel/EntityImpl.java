package a.entity.gus06.sys.expression1.apply.op._okcancel;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.Color;
import java.net.URL;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220614";}

	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JComponent) return perform.f(obj);
		if(obj instanceof I) return perform.f(((I) obj).i());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}