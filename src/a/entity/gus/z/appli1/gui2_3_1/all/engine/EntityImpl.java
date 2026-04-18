package a.entity.gus.z.appli1.gui2_3_1.all.engine;

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
	public String creationDate() {return "20240112";}

	private Service engine;
	private Service locker;

	private List nameList;
	private String selectedName;
	private Object info;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");

		rebuild();
		engine.addActionListener(this);
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
				lockJustAdded();
				entityAdded();
			}
			else if (s.equals("entityRenamed()")) {
				rebuild();
				lockJustRenamed();
				entityRenamed();
			}
			else if (s.equals("entityDuplicated()")) {
				rebuild();
				lockJustDuplicated();
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
		locker.p(new Object[] {"update", nameList});
	}
	
	private void lockJustAdded() throws Exception
	{
		Object info = engine.r("info");
		locker.f(new Object[] {"lock", info, nameList});
	}
	
	private void lockJustRenamed() throws Exception
	{
		String[] info = (String[]) engine.r("info");
		locker.f(new Object[] {"lock", info[1], nameList});
	}
	
	private void lockJustDuplicated() throws Exception
	{
		String[] info = (String[]) engine.r("info");
		locker.f(new Object[] {"lock", info[1], nameList});
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
		if (key.equals("srcSaved")) {
			handleSrcSaved(obj);
			return;
		}
		if (key.equals("srcCleared")) {
			handleSrcCleared(obj);
			return;
		}
		engine.v(key, obj);
	}
	
	/*
	 * ACTIONS
	 */
	
	private void lock(Object obj) throws Exception {
		if(locker.f(new Object[] {"lock", obj, nameList}))
			locked();
	}
	
	private void unlock(Object obj) throws Exception {
		if(locker.f(new Object[] {"unlock", obj, nameList}))
			unlocked();
	}
	
	private void select(String selectedName) throws Exception {
		if(!nameList.contains(selectedName)) return;
		this.selectedName = selectedName;
		locker.p(new Object[] {"lock", selectedName, nameList});
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
	
	private void handleSrcSaved(Object info) throws Exception {
		this.info = info;
		performLoad();
		srcSaved();
	}
	
	private void handleSrcCleared(Object info) throws Exception {
		this.info = info;
		performLoad();
		srcCleared();
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
	
	private void srcSaved() {
		send(this, "srcSaved()");
	}
	
	private void srcCleared() {
		send(this, "srcCleared()");
	}
}
