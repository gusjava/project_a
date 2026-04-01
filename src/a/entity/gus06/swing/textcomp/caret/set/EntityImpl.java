package a.entity.gus06.swing.textcomp.caret.set;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170225";}


	private Service ruleToIndex;

	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = ""+o[1];
		
		Integer index = (Integer) ruleToIndex.t(new Object[]{comp.getText(),rule});
		if(index!=null) comp.setCaretPosition(index.intValue());
	}
}
