package a.entity.gus06.swing.textcomp.cust.action.alt_del.perform.selection.mono;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Element;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160906";}


	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
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
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			int len = line.length();
			
			if(len<=offset1)
			{
				b.append(line+"\n");
			}
			else if(len<=offset2)
			{
				b.append(line.substring(0,offset1)+"\n");
			}
			else
			{
				b.append(line.substring(0,offset1)+line.substring(offset2,len)+"\n");
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
