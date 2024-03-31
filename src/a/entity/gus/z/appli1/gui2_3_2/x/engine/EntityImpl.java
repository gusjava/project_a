package a.entity.gus.z.appli1.gui2_3_2.x.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.S;
import a.framework.S1;
import a.framework.Service;
import a.framework.V;

public class EntityImpl extends S1 implements Entity, G, R, V, E, F, ActionListener {
	public String creationDate() {return "20240113";}
	
	private Service engine;
	private Service locker;
	private Service xFilter;
	private Service applyFilter;
	
	private List nameList;
	private List xNameList;
	private String selectedName;
	private Object info;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");
		xFilter = Outside.service(this, "gus.x.entity.name.isx");
		applyFilter = Outside.service(this, "gus.x.list.filter");

		rebuild();
		((S) engine).addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			String s = evt.getActionCommand();
			if (s.equals("loaded()")) {
				rebuild();
				loaded();
			}
			else if (s.equals("entityAdded()")) {
				rebuild();
				entityAdded();
			}
			else if (s.equals("entityRenamed()")) {
				rebuild();
				entityRenamed();
			}
			else if (s.equals("entityDuplicated()")) {
				rebuild();
				entityDuplicated();
			}
			else if (s.equals("entityDeleted()")) {
				rebuild();
				entityDeleted();
			}
			else if (s.equals("entityModified()")) {
				rebuild();
				entityModified();
			}
			else
				throw new Exception("Unsupported command: " + s);
		} catch (Exception e) {
			Outside.err(this, "actionPerformed(ActionEvent)", e);
		}
	}

	private void rebuild() throws Exception {
		nameList = (List) engine.r("nameList");
		xNameList = (List) applyFilter.t(new Object[] {nameList, xFilter});
		locker.p(new Object[] {"update", xNameList});
	}

	public void e() throws Exception {
		engine.e();
	}

	public Object g() throws Exception {
		return engine.g();
	}

	public boolean f(Object obj) throws Exception {
		return engine.f(obj);
	}

	public Object r(String key) throws Exception {
		if (key.equals("lockSet"))
			return locker.g();
		if (key.equals("selectedName"))
			return selectedName;
		if (key.equals("xNameList"))
			return xNameList;
		if (key.equals("nameList"))
			return nameList;
		if (key.equals("info"))
			return info;
		return engine.r(key);
	}

	public void v(String key, Object obj) throws Exception {
		if (key.equals("lock")) {
			lock(obj);
			return;
		}
		if (key.equals("unlock")) {
			unlock(obj);
			return;
		}
		if (key.equals("select")) {
			select((String) obj);
			return;
		}
		if (key.equals("entityAdded")) {
			handleEntityAdded(obj);
			return;
		}
		if (key.equals("entityRenamed")) {
			handleEntityRenamed(obj);
			return;
		}
		if (key.equals("entityDuplicated")) {
			handleEntityDuplicated(obj);
			return;
		}
		if (key.equals("entityDeleted")) {
			handleEntityDeleted(obj);
			return;
		}
		if (key.equals("entityModified")) {
			handleEntityModified(obj);
			return;
		}
		engine.v(key, obj);
	}
	
	/*
	 * ACTIONS
	 */
	
	private void lock(Object obj) throws Exception {
		if(locker.f(new Object[] {"lock", obj, xNameList}))
			locked();
	}
	
	private void unlock(Object obj) throws Exception {
		if(locker.f(new Object[] {"unlock", obj, xNameList}))
			unlocked();
	}
	
	private void select(String selectedName) throws Exception {
		if(!xNameList.contains(selectedName)) return;
		this.selectedName = selectedName;
		locker.p(new Object[] {"lock", selectedName, xNameList});
		selected();
	}

	private void handleEntityAdded(Object info) throws Exception {
		this.info = info;
		performLoad();
		entityAdded();
	}

	private void handleEntityRenamed(Object info) throws Exception {
		this.info = info;
		performLoad();
		entityRenamed();
	}

	private void handleEntityDuplicated(Object info) throws Exception {
		this.info = info;
		performLoad();
		entityDuplicated();
	}

	private void handleEntityDeleted(Object info) throws Exception {
		this.info = info;
		performLoad();
		entityDeleted();
	}

	private void handleEntityModified(Object info) throws Exception {
		this.info = info;
		performLoad();
		entityModified();
	}
	
	private void performLoad() throws Exception {
		engine.e();
	}
	
	/*
	 * EVENTS
	 */

	private void locked() {
		send(this, "locked()");
	}

	private void unlocked() {
		send(this, "unlocked()");
	}

	private void selected() {
		send(this, "selected()");
	}

	private void loaded() {
		send(this, "loaded()");
	}

	private void entityAdded() {
		send(this, "entityAdded()");
	}

	private void entityRenamed() {
		send(this, "entityRenamed()");
	}

	private void entityDuplicated() {
		send(this, "entityDuplicated()");
	}

	private void entityDeleted() {
		send(this, "entityDeleted()");
	}

	private void entityModified() {
		send(this, "entityModified()");
	}
}
