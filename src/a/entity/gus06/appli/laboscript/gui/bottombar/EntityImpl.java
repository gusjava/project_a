package a.entity.gus06.appli.laboscript.gui.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20190510";}


	private Service scriptLabel;
	private Service compHolder;


	public EntityImpl() throws Exception
	{
		scriptLabel = Outside.service(this,"*gus06.sys.script1.manager.label1");
		compHolder = Outside.service(this,"*gus06.swing.panel.holder.bottombar");
		
		compHolder.v("w",scriptLabel.i());
	}
	
	
	public Object i() throws Exception
	{return compHolder.i();}
}
