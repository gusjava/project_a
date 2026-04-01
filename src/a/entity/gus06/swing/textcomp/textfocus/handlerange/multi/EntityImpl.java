package a.entity.gus06.swing.textcomp.textfocus.handlerange.multi;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}
	
	
	private Service focusManager;

	public EntityImpl() throws Exception
	{
		focusManager = Outside.service(this,"gus06.swing.textcomp.textfocus.manager");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		int[] range = (int[]) o[1];
		
		int rangeLength = range.length;
		if(rangeLength%2==1) throw new Exception("Invalid range array length: "+rangeLength);
		
		
		int length = comp.getDocument().getLength();
		int offset = 0;
		
		List list = new ArrayList();
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<rangeLength;i++)
		{
			int r = range[i];
			String s = getText(comp,offset,r);
			offset = r;
			
			if(i%2==0) list.add(s);
			else
			{
				if(s.contains("\n")) throw new Exception("Multi-line part found at position "+i);
				b.append(s+"\n");
			}
		}
		
		list.add(getText(comp,offset,length));
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		
		P holder = (P) focusManager.t(comp);
		holder.p(new Object[]{list,b.toString()});
	}
	
	
	
	
	private String getText(JTextComponent comp, int start, int end) throws Exception
	{
		try{return comp.getText(start,end-start);}
		catch(BadLocationException e)
		{
			String message = "Invalid text location: [start="+start+" end="+end+"]";
			throw new Exception(message,e);
		}
	}
}
