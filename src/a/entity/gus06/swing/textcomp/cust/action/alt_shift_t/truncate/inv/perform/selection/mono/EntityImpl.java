package a.entity.gus06.swing.textcomp.cust.action.alt_shift_t.truncate.inv.perform.selection.mono;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160903";}


	private Service mono;

	public EntityImpl() throws Exception
	{mono = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_shift_t.truncate.inv.perform.caret");}

	public void p(Object obj) throws Exception
	{mono.p(obj);}
}