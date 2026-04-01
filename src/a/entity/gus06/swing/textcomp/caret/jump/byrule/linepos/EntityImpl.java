package a.entity.gus06.swing.textcomp.caret.jump.byrule.linepos;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220521";}


	private Service ruleToNumber;
	private Service ruleToPos;

	public EntityImpl() throws Exception
	{
		ruleToNumber = Outside.service(this,"gus06.list.ruletonumber1");
		ruleToPos = Outside.service(this,"gus06.list.ruletopos1");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule1 = (String) o[1];
		String rule2 = (String) o[2];
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		Integer number = (Integer) ruleToNumber.t(new Object[]{lines, rule1});
		if(number==null) return true;
		
		int n = 0;
		for(int i=0;i<number-1;i++) n += lines[i].length()+1;
		
		int currentLineLength = lines[number-1].length();
		Integer pos = (Integer) ruleToPos.t(new Object[]{currentLineLength, rule2});
		if(pos==null) return false;
		
		n += pos;
		
		int len = text.length();
		if(pos>len) pos = len;
		comp.setCaretPosition(n);
		return true;
	}
}