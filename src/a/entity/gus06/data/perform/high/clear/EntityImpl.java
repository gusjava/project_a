package a.entity.gus06.data.perform.high.clear;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190405";}


	private Service performTextComp;

	public EntityImpl() throws Exception
	{
		performTextComp = Outside.service(this,"gus06.swing.textcomp.highlight.clear");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextComponent) {performTextComp.p(obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
