package a.entity.gus06.framework.doc.en.feature_i_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The I feature is used for a graphical entities to expose their GUI, which is typically a java.swing.JComponent object.
It can also be used to generate such objects.

Entity Example :

package a.entity.gus.swing.panel.hold.green;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140811";}
	
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
		panel.setBackground(Color.GREEN.darker());
	}
	
	public Object i() throws Exception
	{return panel;}
}""";
	}
}