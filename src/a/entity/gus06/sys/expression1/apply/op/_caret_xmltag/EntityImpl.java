package a.entity.gus06.sys.expression1.apply.op._caret_xmltag;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.swing.textcomp.caret.xmltag.find");}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JTextComponent) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
