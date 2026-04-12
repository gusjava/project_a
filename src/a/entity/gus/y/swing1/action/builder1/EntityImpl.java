package a.entity.gus.y.swing1.action.builder1;

import javax.swing.Action;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240110";}

	private Service exeToAction;
	private Service repaint;

	public EntityImpl() throws Exception {
		exeToAction = Outside.service(this, "gus.y.convert1.executetoaction");
		repaint = Outside.service(this, "gus.y.swing1.action.cust2.display");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		String display = (String) o[0];
		E execute = (E) o[1];

		Action action = (Action) exeToAction.t(execute);
		repaint.v(display, action);

		return action;
	}
}