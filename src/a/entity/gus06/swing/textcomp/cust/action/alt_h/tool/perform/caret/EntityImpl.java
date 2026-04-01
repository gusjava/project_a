package a.entity.gus06.swing.textcomp.cust.action.alt_h.tool.perform.caret;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160902";}


	private Service chooseTrans;


	public EntityImpl() throws Exception
	{
		chooseTrans = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		T t = (T) chooseTrans.g();
		if(t==null) return;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		int nb = lines.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String line1 = (String) t.t(lines[i]);
			b.append(line1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
