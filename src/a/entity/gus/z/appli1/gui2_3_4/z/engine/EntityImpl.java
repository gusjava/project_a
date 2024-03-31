package a.entity.gus.z.appli1.gui2_3_4.z.engine;

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
	private Service getZName;

	private List nameList;
	private List zNameList;
	private Map entitiesByZName;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");
		getZName = Outside.service(this, "gus.x.entity.name.toz");

		entitiesByZName = new HashMap();
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

			entitiesByZName = new HashMap();
			for (Object elem : nameList) {
				String name = (String) elem;
				String zName = (String) getZName.t(name);
				if (zName != null)
					addEntityToZ(zName, name);
			}
			zNameList = new ArrayList(entitiesByZName.keySet());
			Collections.sort(zNameList);

			locker.p(new Object[] { "update", nameList });
			loaded();
		} catch (Exception e) {
			Outside.err(EntityImpl.this, "load()", e);
		}
	}

	private void addEntityToZ(String zName, String name) {
		if (!entitiesByZName.containsKey(zName))
			entitiesByZName.put(zName, new ArrayList());
		((List) entitiesByZName.get(zName)).add(name);
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
		if (key.equals("zNameList"))
			return zNameList;
		if (key.equals("entitiesByZName"))
			return entitiesByZName;
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
