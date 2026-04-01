package a.entity.gus06.swing.comp.setfont;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Font;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190508";}


	private Service findFont;

	public EntityImpl() throws Exception
	{
		findFont = Outside.service(this,"gus06.find.font");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Font font = (Font) findFont.t(o[1]);
		
		if(comp instanceof JComponent)		{((JComponent) comp).setFont(font);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}