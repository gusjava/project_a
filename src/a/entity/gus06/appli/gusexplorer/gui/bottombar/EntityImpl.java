package a.entity.gus06.appli.gusexplorer.gui.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.util.List;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150609";}


	private Service taskLabel;
	private Service scriptLabel;
	private Service schedulingLabel;
	private Service clipboardLabel;
	private Service errorsLabel;
	private Service compHolder;
	private Service buildCustom;


	public EntityImpl() throws Exception
	{
		taskLabel = Outside.service(this,"*gus06.sys.runtask1.label1");
		scriptLabel = Outside.service(this,"*gus06.sys.script1.manager.label1");
		schedulingLabel = Outside.service(this,"*gus06.appli.gusexplorer.scheduling.manager.label1");
		clipboardLabel = Outside.service(this,"*gus06.sys.clipboard1.gui.displaylabel1");
		errorsLabel = Outside.service(this,"*gus06.app.errors.gui.label");
		compHolder = Outside.service(this,"*gus06.swing.panel.holder.bottombar");
		buildCustom = Outside.service(this,"gus06.appli.gusexplorer.gui.bottombar.custom");
		
		List custom = (List) buildCustom.g();
		if(custom.size()>0)
		{
			for(int i=0;i<custom.size();i++)
			compHolder.v("w", custom.get(i));
			compHolder.v("w", "separator");
		}
		
		compHolder.v("w",taskLabel.i());
		compHolder.v("w",scriptLabel.i());
		compHolder.v("w",schedulingLabel.i());
		
		compHolder.v("e",errorsLabel.i());
		compHolder.v("e",clipboardLabel.i());
	}
	
	
	public Object i() throws Exception
	{return compHolder.i();}
}
