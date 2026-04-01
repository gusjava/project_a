package a.entity.gus06.sys.autocomplete1.time.today;

import a.framework.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231207";}
	
	
	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.time.today");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextArea comp = (JTextArea) obj;
		String s = (String) find.g();
		comp.insert(s, comp.getCaretPosition());
	}
}