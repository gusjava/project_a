package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E, G, F, R, V, ActionListener {
	public String creationDate() {return "20260826";}

	private Service engine;
	private Service locker;

	private List nameList;
	private List xNameList = new ArrayList();
	private String yName;
	private String selectedName;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");

		rebuild();
		((S) engine).addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt)
	{
		try {
			String s = evt.getActionCommand();
			if (s.equals("loaded()")) { rebuild(); loaded(); }
			else if (s.equals("loading()")) {}
			else if (s.equals("entityAdded()")) { rebuild(); lockJustAdded(engine.r("info")); entityAdded(); }
			else if (s.equals("entityRenamed()")) { rebuild(); lockJustRenamed((String[]) engine.r("info")); entityRenamed(); }
			else if (s.equals("entityDuplicated()")) { rebuild(); lockJustDuplicated((String[]) engine.r("info")); entityDuplicated(); }
			else if (s.equals("entityReplaced()")) { rebuild(); entityReplaced(); }
			else if (s.equals("entitiesReplaced()")) { rebuild(); entitiesReplaced(); }
			else if (s.equals("entityDeleted()")) { rebuild(); entityDeleted(); }
			else if (s.equals("entitiesDeleted()")) { rebuild(); entitiesDeleted(); }
			else if (s.equals("entityModified()")) { rebuild(); entityModified(); }
			else if (s.equals("srcSaved()")) { rebuild(); srcSaved(); }
			else if (s.equals("srcCleared()")) { rebuild(); srcCleared(); }
		} catch (Exception e) {
			Outside.err(this, "actionPerformed(ActionEvent)", e);
		}
	}

	private void rebuild() throws Exception
	{
		nameList = (List) engine.r("nameList");
		xNameList = filterByScope(nameList);
		locker.p(new Object[] { "update", xNameList });
	}

	private List filterByScope(List nameList)
	{
		List result = new ArrayList();
		String yPrefix = reconstructPrefix(yName);
		if (yPrefix == null) return result;

		String prefix = yPrefix + ".";
		int nb = nameList.size();
		for (int i = 0; i < nb; i++) {
			String name = (String) nameList.get(i);
			if (name.startsWith(prefix)) result.add(name);
		}
		return result;
	}

	private String reconstructPrefix(String yName)
	{
		if (yName == null) return null;
		int i = yName.indexOf('.');
		if (i < 0) return yName;
		return yName.substring(0, i) + ".y." + yName.substring(i + 1);
	}

	private void lockJustAdded(Object info) throws Exception
	{locker.f(new Object[] {"lock", info, xNameList});}

	private void lockJustRenamed(String[] info) throws Exception
	{locker.f(new Object[] {"lock", info[1], xNameList});}

	private void lockJustDuplicated(String[] info) throws Exception
	{locker.f(new Object[] {"lock", info[1], xNameList});}

	public void e() throws Exception
	{
		engine.e();
	}

	public Object g() throws Exception
	{
		return engine.g();
	}

	public boolean f(Object obj) throws Exception
	{
		return engine.f(obj);
	}

	public Object r(String key) throws Exception
	{
		if (key.equals("lockSet")) return locker.g();
		if (key.equals("selectedName")) return selectedName;
		if (key.equals("xNameList")) return xNameList;
		if (key.equals("nameList")) return nameList;
		if (key.equals("yName")) return yName;
		if (key.equals("yPrefix")) return reconstructPrefix(yName);

		if (key.equals("keys")) return new String[] {
			"lockSet", "selectedName", "xNameList", "nameList", "yName", "yPrefix" };

		return engine.r(key);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("lock")) { lock(obj); return; }
		if (key.equals("unlock")) { unlock(obj); return; }
		if (key.equals("select")) { select((String) obj); return; }
		if (key.equals("scope")) { scope((String) obj); return; }
		engine.v(key, obj);
	}

	private void scope(String newYName) throws Exception
	{
		this.yName = newYName;
		this.selectedName = null;
		rebuild();
		scopeChanged();
	}

	private void lock(Object obj) throws Exception
	{if (locker.f(new Object[] {"lock", obj, xNameList})) locked();}

	private void unlock(Object obj) throws Exception
	{if (locker.f(new Object[] {"unlock", obj, xNameList})) unlocked();}

	private void select(String selectedName) throws Exception
	{
		if (!xNameList.contains(selectedName)) return;
		this.selectedName = selectedName;
		locker.p(new Object[] {"lock", selectedName, xNameList});
		selected();
	}

	/*
	 * EVENTS
	 */

	private void locked() { send(this, "locked()"); }
	private void unlocked() { send(this, "unlocked()"); }
	private void selected() { send(this, "selected()"); }
	private void scopeChanged() { send(this, "scopeChanged()"); }
	private void loaded() { send(this, "loaded()"); }
	private void entityAdded() { send(this, "entityAdded()"); }
	private void entityRenamed() { send(this, "entityRenamed()"); }
	private void entityDuplicated() { send(this, "entityDuplicated()"); }
	private void entityReplaced() { send(this, "entityReplaced()"); }
	private void entitiesReplaced() { send(this, "entitiesReplaced()"); }
	private void entityDeleted() { send(this, "entityDeleted()"); }
	private void entitiesDeleted() { send(this, "entitiesDeleted()"); }
	private void entityModified() { send(this, "entityModified()"); }
	private void srcSaved() { send(this, "srcSaved()"); }
	private void srcCleared() { send(this, "srcCleared()"); }
}
