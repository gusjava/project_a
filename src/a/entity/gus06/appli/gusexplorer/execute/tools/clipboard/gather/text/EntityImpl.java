package a.entity.gus06.appli.gusexplorer.execute.tools.clipboard.gather.text;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20180409";}
	
	public static final String TITLE = "Gather text from clipboard";
	

	private Service recordGui1;
	private Service showInFrame;

	public EntityImpl() throws Exception
	{
		recordGui1 = Outside.service(this,"*gus06.sys.clipboardwatcher1.recordgui1");
		showInFrame = Outside.service(this,"gus06.swing.comp.inframe");
	}
	
	public void e() throws Exception
	{
		Object comp = recordGui1.i();
		showInFrame.v(TITLE,comp);
	}
}
