package a.entity.gus06.swing.textarea.factory.console1.black.white;

import a.framework.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20161109";}


	private Service cust;

	public EntityImpl() throws Exception
	{
		cust = Outside.service(this,"gus06.swing.textcomp.cust.console1.black.white");
	}
	
	
	public Object i() throws Exception
	{
		JTextArea area = new JTextArea();
		area.setEditable(false);
		cust.p(area);
		return area;
	}
}
