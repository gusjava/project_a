package a.entity.gus06.sys.autocomplete1.open;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160521";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.desktop.open.listfiles");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getSelectedText();
		if(text==null || text.equals("")) text = comp.getText();
		
		perform.p(text);
	}
}
