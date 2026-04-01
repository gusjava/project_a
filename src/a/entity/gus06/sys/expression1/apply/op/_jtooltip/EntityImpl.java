package a.entity.gus06.sys.expression1.apply.op._jtooltip;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.Color;
import java.net.URL;
import java.io.File;
import java.awt.Image;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220610";}

	
	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.jtooltip");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Number) return find.t(obj);
		if(obj instanceof String) return find.t(obj);
		if(obj instanceof Font) return find.t(obj);
		if(obj instanceof Color) return find.t(obj);
		if(obj instanceof Icon) return find.t(obj);
		if(obj instanceof Image) return find.t(obj);
		if(obj instanceof Map) return find.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}