package a.entity.gus06.sys.expression1.apply.op._jdialog_color;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.List;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190602";}


	private Service choose;
	
	public EntityImpl() throws Exception
	{
		choose = Outside.service(this,"gus06.input.choose.color.dialog");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return choose.t(obj);
		return choose.g();
	}
}
