package a.entity.gus06.swing.action.builder0;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}


	private Service exeToAction;
	private Service repaint;

	public EntityImpl() throws Exception
	{
		exeToAction = Outside.service(this,"gus06.convert.executetoaction3");
		repaint = Outside.service(this,"gus06.swing.action.cust2.display");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String display = (String) o[0];
		E execute = (E) o[1];
		
		Action action = (Action) exeToAction.t(execute);
		repaint.v(display,action);
		
		return action;
	}
}
