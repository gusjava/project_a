package a.entity.gus06.sys.keystroke1.holder.comp;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170513";}


	private Service handleOther;
	private Service handleTextComp;

	public EntityImpl() throws Exception
	{
		handleOther = Outside.service(this,"gus06.sys.keystroke1.holder.comp.other");
		handleTextComp = Outside.service(this,"gus06.sys.keystroke1.holder.comp.textcomp");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		if(o[2] instanceof JTextComponent)
			return handleTextComp.t(obj);
		return handleOther.t(obj);
	}
}
