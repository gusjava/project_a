package a.entity.gus06.swing.textcomp.text.hiddenchars.asstring;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.awt.Font;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190315";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.hiddenchars.asstring");
	}

	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = comp.getText();
		Font font = comp.getFont();
		
		return perform.t(new Object[]{s,font});
	}
}
