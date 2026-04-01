package a.entity.gus06.swing.textcomp.caret.jump.byrule.line;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220521";}


	private Service ruleToNumber;

	public EntityImpl() throws Exception
	{
		ruleToNumber = Outside.service(this,"gus06.list.ruletonumber1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = (String) o[1];
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		Integer number = (Integer) ruleToNumber.t(new Object[]{lines, rule});
		if(number==null) return false;
		
		int n = 0;
		for(int i=0;i<number-1;i++) n += lines[i].length()+1;
		comp.setCaretPosition(n);
		return true;
	}
}