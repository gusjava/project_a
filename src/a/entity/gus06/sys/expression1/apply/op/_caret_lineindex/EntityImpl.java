package a.entity.gus06.sys.expression1.apply.op._caret_lineindex;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220825";}


	private Service findIndex;

	public EntityImpl() throws Exception
	{
		findIndex = Outside.service(this,"gus06.swing.textcomp.caret.find.lineindex");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof JTextComponent) return findIndex((JTextComponent) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Integer findIndex(JTextComponent comp) throws Exception
	{return (Integer) findIndex.t(comp);}
}
