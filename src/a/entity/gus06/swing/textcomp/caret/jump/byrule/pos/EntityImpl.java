package a.entity.gus06.swing.textcomp.caret.jump.byrule.pos;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220521";}


	private Service ruleToPos;

	public EntityImpl() throws Exception
	{
		ruleToPos = Outside.service(this,"gus06.list.ruletopos1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = (String) o[1];
		
		int len = comp.getText().length();
		Integer pos = (Integer) ruleToPos.t(new Object[]{len, rule});
		if(pos==null) return false;
		
		if(pos>len) pos = len;
		comp.setCaretPosition(pos);
		return true;
	}
}