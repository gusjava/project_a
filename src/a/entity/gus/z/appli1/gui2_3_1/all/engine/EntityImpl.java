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

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		locker = Outside.service(this, "*gus.z.appli1.tool.entitylocker");

		rebuild();
		engine.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String s = evt.getActionCommand();
			if (s.equals("loaded()"))
			{
				rebuild();
				loaded();
			}
			else if (s.equals("loading()")) {
			}
			else if (s.equals("entityAdded()"))
			{
				rebuild();
				lockJustAdded(engine.r("info"));
				entityAdded();
			}
			else if (s.equals("entityRenamed()"))
			{
				rebuild();
				lockJustRenamed(engine.r("info"));
				entityRenamed();
			}
			else if (s.equals("entityDuplicated()"))
			{
				rebuild();
				lockJustDuplicated(engine.r("info"));
				entityDuplicated();
			}
			else if (s.equals("entityReplaced()"))
			{
				rebuild();
				lockJustReplaced(engine.r("info"));
				entityDuplicated();
			}
			else if (s.equals("entityDeleted()"))
			{
				rebuild();
				entityDeleted();
			}
			else if (s.equals("entityModified()"))
			{
				rebuild();
				entityModified();
			}
			else if (s.equals("srcSaved()"))
			{
				rebuild();
				srcSaved();
			}
			else if (s.equals("srcCleared()"))
			{
				rebuild();
				srcCleared();
			}
			else throw new Exception("Unsupported command: " + s);
		}
		catch (Exception e)
		{Outside.err(this, "actionPerformed(ActionEvent)", e);}
	}

	private void rebuild() throws Exception
	{
		nameList = (List) engine.r("nameList");
		locker.p(new Object[] {"update", nameList});
	}

	private void lockJustAdded(Object info) throws Exception
	{
		locker.f(new Object[] {"lock", info, nameList});
	}

	private void lockJustRenamed(Object info) throws Exception
	{
		locker.f(new Object[] {"lock", ((String[])info)[1], nameList});
	}

	private void lockJustDuplicated(Object info) throws Exception
	{
		locker.f(new Object[] {"lock", ((String[])info)[1], nameList});
	}

	private void lockJustReplaced(Object info) throws Exception
	{
		locker.f(new Object[] {"lock", ((String[])info)[1], nameList});
	}

	public void e() throws Exception
	{engine.e();}

	public Object g() throws Exception
	{return engine.g();}

	public boolean f(Object obj) throws Exception
	{return engine.f(obj);}

	public Object r(String key) throws Exception
	{
		if (key.equals("lockSet")) return locker.g();
		if (key.equals("selectedName")) return selectedName;
		if (key.equals("nameList")) return nameList;
		if (key.equals("info")) return engine.r("info");
		if (key.equals("devId")) return engine.r("devId");
		if (key.equals("compileErrMap")) return engine.r("compileErrMap");
		if (key.equals("xyzErrMap")) return engine.r("xyzErrMap");
		if (key.equals("missingLinkMap")) return engine.r("missingLinkMap");
		if (key.equals("srcSaveMap")) return engine.r("srcSaveMap");
		if (key.equals("rootDir")) return engine.r("rootDir");
		if (key.equals("cx")) return engine.r("cx");

		if (key.equals("keys")) return new String[] {
			"lockSet", "selectedName", "nameList", "info", "devId", 
			"compileErrMap", "xyzErrMap", "missingLinkMap", "srcSaveMap", 
			"rootDir", "cx" };

		throw new Exception("Unknown key: " + key);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("lock"))
		{
			lock(obj);
			return;
		}
		if (key.equals("unlock"))
		{
			unlock(obj);
			return;
		}
		if (key.equals("select"))
		{
			select((String) obj);
			return;
		}
		if (key.equals("entityAdded"))
		{
			engine.v("entityAdded", obj);
			return;
		}
		if (key.equals("entityRenamed"))
		{
			engine.v("entityRenamed", obj);
			return;
		}
		if (key.equals("entityDuplicated"))
		{
			engine.v("entityDuplicated", obj);
			return;
		}
		if (key.equals("entityReplaced"))
		{
			engine.v("entityReplaced", obj);
			return;
		}
		if (key.equals("entityDeleted"))
		{
			engine.v("entityDeleted", obj);
			return;
		}
		if (key.equals("entityModified"))
		{
			engine.v("entityModified", obj);
			return;
		}
		if (key.equals("srcSaved"))
		{
			engine.v("srcSaved", obj);
			return;
		}
		if (key.equals("srcCleared"))
		{
			engine.v("srcCleared", obj);
			return;
		}
		throw new Exception("Unknown key: " + key);
	}

	/*
	 * ACTIONS
	 */

	private void lock(Object obj) throws Exception
	{
		if(locker.f(new Object[] {"lock", obj, nameList}))
			locked();
	}

	private void unlock(Object obj) throws Exception
	{
		if(locker.f(new Object[] {"unlock", obj, nameList}))
			unlocked();
	}

	private void select(String selectedName) throws Exception
	{
		if(!nameList.contains(selectedName)) return;
		this.selectedName = selectedName;
		locker.p(new Object[] {"lock", selectedName, nameList});
		selected();
	}

	/*
	 * EVENTS
	 */

	private void locked()
	{send(this, "locked()");}

	private void unlocked()
	{send(this, "unlocked()");}

	private void selected()
	{send(this, "selected()");}

	private void loaded()
	{send(this, "loaded()");}

	private void entityAdded()
	{send(this, "entityAdded()");}

	private void entityRenamed()
	{send(this, "entityRenamed()");}

	private void entityDuplicated()
	{send(this, "entityDuplicated()");}

	private void entityReplaced()
	{send(this, "entityReplaced()");}

	private void entityDeleted()
	{send(this, "entityDeleted()");}

	private void entityModified()
	{send(this, "entityModified()");}

	private void srcSaved()
	{send(this, "srcSaved()");}

	private void srcCleared()
	{send(this, "srcCleared()");}
}
