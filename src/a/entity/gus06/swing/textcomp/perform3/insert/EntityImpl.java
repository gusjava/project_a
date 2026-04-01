package a.entity.gus06.swing.textcomp.perform3.insert;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220421";}

	
	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Object index = o[1];
		String data = (String) o[2];
		
		Integer index1 = (Integer) ruleToIndex.t(new Object[]{comp.getText(),index});
		comp.getDocument().insertString(index1.intValue(),data,null);
	}
}