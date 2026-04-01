package a.entity.gus06.swing.textcomp.text.hiddenchars.asbool;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.awt.Font;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190316";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.hiddenchars.asbool");
	}

	
	public boolean f(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = comp.getText();
		Font font = comp.getFont();
		
		return perform.f(new Object[]{s,font});
	}
}
