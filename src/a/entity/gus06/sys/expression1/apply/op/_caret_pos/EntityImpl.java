package a.entity.gus06.sys.expression1.apply.op._caret_pos;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170224";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JTextComponent) return pos((JTextComponent) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Integer pos(JTextComponent comp)
	{return Integer.valueOf(comp.getCaretPosition());}
}
