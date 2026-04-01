package a.entity.gus06.app.mainframe.check.isinside;

import a.framework.*;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200403";}


	private JFrame frame;
	
	public EntityImpl() throws Exception
	{
		frame = (JFrame) Outside.resource(this,"mainframe");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object root = SwingUtilities.getWindowAncestor((Component) obj);
		
		return frame==root;
	}
}
