package a.entity.gus06.feature.build.i.jlabel;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, G, I {

	public String creationDate() {return "20150711";}


	public Object g() throws Exception
	{return this;}
	
	public Object i() throws Exception
	{return new JLabel();}
}
