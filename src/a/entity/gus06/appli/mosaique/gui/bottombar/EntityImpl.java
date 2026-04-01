package a.entity.gus06.appli.mosaique.gui.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141122";}


	private Service dirLabel;
	private Service fileLabel;
	private Service progressBar;

	private JToolBar bar;
	
	public EntityImpl() throws Exception
	{
		dirLabel = Outside.service(this,"*gus06.appli.mosaique.data.dir.label");
		fileLabel = Outside.service(this,"*gus06.appli.mosaique.data.file.label");
		progressBar = Outside.service(this,"*gus06.appli.mosaique.gui.progressbar");
		
		bar = new JToolBar();
		bar.setFloatable(false);
		
		bar.addSeparator();
		bar.add((JComponent) dirLabel.i());
		bar.addSeparator();
		bar.add((JComponent) fileLabel.i());
		bar.addSeparator();
		bar.add((JComponent) progressBar.i());
		bar.addSeparator();
	}
	
	
	public Object i() throws Exception
	{return bar;}
}
