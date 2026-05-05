package a.entity.gus06.swing.action.builder1;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140811";}


	private Service exeToAction;
	private Service repaint;
	private Service localizer;
	private Service findKey;

	public EntityImpl() throws Exception
	{
		exeToAction = Outside.service(this,"gus.y.convert1.executetoaction.th");
		repaint = Outside.service(this,"gus06.swing.action.cust2.display");
		localizer = Outside.service(this,"gus06.ling.localize.manager");
		findKey = Outside.service(this,"gus06.swing.action.builder1.lingkey");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String display = (String) o[0];
		E execute = (E) o[1];
		
		Action action = (Action) exeToAction.t(execute);
		repaint.v(display,action);
		
		String lingKey = (String) findKey.t(execute);
		if(lingKey!=null) localizer.v(lingKey,action);
		
		return action;
	}
}
