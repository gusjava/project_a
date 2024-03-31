package a.entity.gus.y.swing1.textcomp.caret.linestart;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	private Service ruleToNumber;

	public EntityImpl() throws Exception
	{
		ruleToNumber = Outside.service(this,"gus.y.ruletonumber1.find1");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = ""+o[1];
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		Integer number = (Integer) ruleToNumber.t(new Object[]{lines,rule});
		if(number==null) return;
		if(number>=lines.length) return;
		
		int len = 0;
		for(int i=0;i<number;i++) len += lines[i].length()+1;
		
		comp.setCaretPosition(len);
	}
}
