package a.entity.gus06.sys.expression1.apply.op._caret_lineorselect;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220825";}


	private Service findLine;

	public EntityImpl() throws Exception
	{
		findLine = Outside.service(this,"gus06.swing.textcomp.caret.find.selection.or.line");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JTextComponent) return findLine((JTextComponent) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String findLine(JTextComponent comp) throws Exception
	{return (String) findLine.t(comp);}
}