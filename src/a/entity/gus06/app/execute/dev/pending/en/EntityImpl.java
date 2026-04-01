package a.entity.gus06.app.execute.dev.pending.en;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, P {

	public String creationDate() {return "20191227";}

	public static final String MESSAGE = "Pending functionality";


	private Service show;
	
	public EntityImpl() throws Exception
	{show = Outside.service(this,"gus06.swing.optionpane.showmessage.warning");}
	
	public void e() throws Exception
	{show.p(MESSAGE);}
	
	public void p(Object obj) throws Exception
	{show.p(MESSAGE+": "+obj);}
}
