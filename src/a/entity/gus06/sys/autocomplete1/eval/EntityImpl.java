package a.entity.gus06.sys.autocomplete1.eval;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231020";}


	private Service perform;
	private Service show;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.expression.evaluate");
		show = Outside.service(this,"gus06.swing.frame.show.text");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getSelectedText();
		if(text==null || text.equals("")) text = comp.getText();
		
		String result = (String) perform.t(text);
		show.p(result);
	}
}