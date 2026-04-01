package a.entity.gus06.appli.vindinium.gui.replay;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170917";}

	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		split = new JSplitPane();
	}

	public Object i() throws Exception
	{return split;}
}
