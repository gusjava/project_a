package a.entity.gus06.sys.expression1.apply.op._inv;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.inv");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return perform.t(obj);
		if(obj instanceof Image) return perform.t(obj);
		if(obj instanceof RenderedImage) return perform.t(obj);
		if(obj instanceof JPanel) return perform.t(obj);
		if(obj instanceof JTabbedPane) return perform.t(obj);
		if(obj instanceof JSplitPane) return perform.t(obj);
		if(obj instanceof Boolean) return perform.t(obj);
		if(obj instanceof Double) return perform.t(obj);
		if(obj instanceof Integer) return perform.t(obj);
		if(obj instanceof Float) return perform.t(obj);
		if(obj instanceof F) return perform.t(obj);
		if(obj instanceof H) return perform.t(obj);
		if(obj instanceof Map) return perform.t(obj);
		
		if(obj instanceof boolean[]) return perform.t(obj);
		if(obj instanceof double[]) return perform.t(obj);
		if(obj instanceof int[]) return perform.t(obj);
		if(obj instanceof float[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
