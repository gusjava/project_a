package a.entity.gus06.data.perform.high.match1.s;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190406";}


	private Service performTextComp;

	public EntityImpl() throws Exception
	{
		performTextComp = Outside.service(this,"gus06.swing.textcomp.highlight.match1.s.map1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		
		if(data instanceof JTextComponent) {performTextComp.p(obj);return;}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
