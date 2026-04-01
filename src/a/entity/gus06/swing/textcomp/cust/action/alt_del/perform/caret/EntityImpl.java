package a.entity.gus06.swing.textcomp.cust.action.alt_del.perform.caret;

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
		
		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);
		int x0 = document.getDefaultRootElement().getElementIndex(p);
		
		int start = element.getStartOffset();
		int offset = p-start;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		StringBuffer b = new StringBuffer();
		int rmNb = 0;
		
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			int len = line.length();
			if(len<=offset) b.append(line+"\n");
			else
			{
				String part1 = line.substring(0,offset);
				String part2 = line.substring(offset+1,len);
				if(i<x0 && part2.length()>0) rmNb++;
				b.append(part1+part2+"\n");
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
		comp.setCaretPosition(p-rmNb);
	}
}
