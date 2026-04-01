package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.moveup;

import a.framework.*;
import java.io.File;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20151006";}


	private Service manager;
	private Service selection;
	private Service threadHandler;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		threadHandler = Outside.service(this,"gus06.feature.thread.v");
	}
	
	
	
	public void e() throws Exception
	{
		File selected = (File) selection.g();
		perform(selected);
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		perform(file);
	}
	
	
	
	private void perform(File file) throws Exception
	{
		if(file==null) return;
		
		File parent = file.getParentFile();
		if(parent==null) return;
		
		File[] data = new File[]{file,parent};
		Runnable r = (Runnable) threadHandler.t(new Object[]{manager,"modify",data});
		SwingUtilities.invokeLater(r);
	}
}
