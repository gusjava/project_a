package a.entity.gus06.input.choose.color.dialog;

import a.framework.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20190602";}

	
	
	public Object g() throws Exception
	{
		return JColorChooser.showDialog(null,"choose color",Color.BLACK);
	}
	
	public Object t(Object obj) throws Exception
	{
		Color color = (Color) obj;
		return JColorChooser.showDialog(null,"choose color",color);

	}
}
