package a.entity.gus06.swing.textarea.buildarea1;

import a.framework.*;
import javax.swing.JTextArea;
import java.awt.Insets;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170128";}


	private Service undo;

	public EntityImpl() throws Exception
	{
		undo = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
	}
	
	
	public Object i() throws Exception
	{
		JTextArea area = new JTextArea();
    		area.setMargin(new Insets(3,3,3,3));
	    	area.setFont(area.getFont().deriveFont((float)16));
	    	area.setLineWrap(true);
	    	
	    	undo.p(area);
	    	return area;
	}
}
