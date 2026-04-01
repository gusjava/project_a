package a.entity.gus06.swing.textcomp.cust.action.alt_h.tool.perform.selection.mono;

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
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		int p1 = comp.getSelectionStart();
		int p2 = comp.getSelectionEnd();
		
		Element element = document.getParagraphElement(p1);
		
		int start = element.getStartOffset();
		int offset1 = p1-start;
		int offset2 = p2-start;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		StringBuffer b = new StringBuffer();
		for(String line:lines)
		{
			int len = line.length();
			int r1 = Math.min(len,offset1);
			int r2 = Math.min(len,offset2);
			
			String line1 = line.substring(0,r1);
			String line2 = (String) t.t(line.substring(r1,r2));
			String line3 = line.substring(r2,len);
			
			b.append(line1+line2+line3+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
