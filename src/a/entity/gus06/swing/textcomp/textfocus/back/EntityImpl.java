package a.entity.gus06.swing.textcomp.textfocus.back;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151107";}


	private Service caretInfos;
	
	
	public EntityImpl() throws Exception
	{
		caretInfos = Outside.service(this,"gus06.swing.textcomp.caret.find.infos");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		List l = (List) o[1];
		
		String text = comp.getText();
		int nb = l.size();
		
		if(nb==0)
		{
			comp.setText(text);
			comp.selectAll();
			return;
		}
		if(nb==1)
		{
			String s1 = (String) l.get(0);
			comp.setText(s1+text);
			comp.select(s1.length(),s1.length()+text.length());
			return;
		}
		if(nb==2)
		{
			String s1 = (String) l.get(0);
			String s2 = (String) l.get(1);
			comp.setText(s1+text+s2);
			comp.select(s1.length(),s1.length()+text.length());
			return;
		}
		
		
		
		int[] infos = (int[]) caretInfos.t(comp);
		int lineIndex = infos[0];
		int colIndex = infos[1];
		
		StringBuffer b = new StringBuffer();
		String[] lines = text.split("\n",-1);
		int nb1 = lines.length;
		
		int newPos = -1;
		
		for(int i=0;i<nb;i++)
		{
			b.append((String) l.get(i));
			if(i<nb-1 && i<nb1)
			{
				if(i==lineIndex) newPos = b.length()+colIndex;
				b.append(lines[i]);
			}
		}
		
		comp.setText(b.toString());
		if(newPos!=-1) comp.setCaretPosition(newPos);
	}
}