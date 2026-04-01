package a.entity.gus06.data.perform.high.count;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231121";}


	private Service performTextComp;

	public EntityImpl() throws Exception
	{
		performTextComp = Outside.service(this,"gus06.swing.textcomp.highlight.count.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		
		if(data instanceof JTextComponent) return performTextComp.t(obj);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}