package a.entity.gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.caret;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}


	private Service highlight;
	private Service findPainter;

	public EntityImpl() throws Exception
	{
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.caret.painter");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		Object painter = findPainter.t(comp);
		if(painter!=null) highlight.p(new Object[]{comp,painter});
		else comp.setText("");
	}
}
