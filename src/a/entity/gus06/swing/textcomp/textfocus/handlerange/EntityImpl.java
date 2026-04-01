package a.entity.gus06.swing.textcomp.textfocus.handlerange;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;
import java.util.ArrayList;

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
		
		int start = range[0];
		int end = range[1];
		
		int length = comp.getDocument().getLength();
		if(start==0 && end==length) return;
		
		String s1 = comp.getText(0,start);
		String ss = comp.getText(start,end-start);
		String s2 = comp.getText(end,length-end);
		
		List list = new ArrayList();
		list.add(s1);
		list.add(s2);
		
		P holder = (P) focusManager.t(comp);
		holder.p(new Object[]{list,ss});
	}
}
