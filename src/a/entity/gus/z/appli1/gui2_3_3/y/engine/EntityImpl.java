package a.entity.gus.z.appli1.gui2_3_3.y.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Service getYName;

	private List nameList;
	private List yNameList;
	private Map entitiesByYName;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");
		getYName = Outside.service(this, "gus.x.entity.name.toy");

		entitiesByYName = new HashMap();
		((S) engine).addActionListener(this);
		load();
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("loaded()"))
			load();
		else if(s.equals("entityAdded()"))
			entityAdded();
		else if(s.equals("entityRenamed()"))
			entityRenamed();
		else if(s.equals("entityDuplicated()"))
			entityDuplicated();
		else if(s.equals("entityDeleted()"))
			entityDeleted();
		else if(s.equals("entityModified()"))
			entityModified();
	}

	private void load() {
		try {
			nameList = (List) engine.r("nameList");

			entitiesByYName = new HashMap();
			for (Object elem : nameList) {
				String name = (String) elem;
				String yName = (String) getYName.t(name);
				if (yName != null)
					addEntityToY(yName, name);
			}
			yNameList = new ArrayList(entitiesByYName.keySet());
			Collections.sort(yNameList);

			locker.p(new Object[] { "update", nameList });
			loaded();
		} catch (Exception e) {
			Outside.err(EntityImpl.this, "load()", e);
		}
	}

	private void addEntityToY(String yName, String name) {
		if (!entitiesByYName.containsKey(yName))
			entitiesByYName.put(yName, new ArrayList());
		((List) entitiesByYName.get(yName)).add(name);
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
		if (key.equals("nameList"))
			return nameList;
		if (key.equals("yNameList"))
			return yNameList;
		if (key.equals("entitiesByYName"))
			return entitiesByYName;

		return engine.r(key);
	}

	public void v(String key, Object obj) throws Exception {
		if (key.equals("lock")) {
			if (locker.f(new Object[] { "lock", obj, nameList }))
				lockAdded();
			return;
		}
		if (key.equals("unlock")) {
			if (locker.f(new Object[] { "unlock", obj }))
				lockRemoved();
			return;
		}
		engine.v(key, obj);
	}

	/*
	 * EVENTS
	 */

	private void lockAdded() {
		send(this, "lockAdded()");
	}

	private void lockRemoved() {
		send(this, "lockRemoved()");
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
