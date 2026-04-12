package a.entity.gus.z.appli1.tool.entitylocker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.P;

public class EntityImpl implements Entity, G, F, P {
	public String creationDate() {return "20240113";}
	
	public static final int LOCK_MAX = 100;

	private Set lockSet;

	public EntityImpl() throws Exception {
		lockSet = new HashSet();
	}

	public Object g() throws Exception {
		return lockSet;
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		String cmd = (String) o[0];

		if (cmd.equals("update")) {
			return update((List) o[1]);
		}
		if (cmd.equals("lock")) {
			return lock(o[1], (List) o[2]);
		}
		if (cmd.equals("unlock")) {
			return unlock(o[1]);
		}
		return true;
	}

	private boolean update(List names) {
		return lockSet.retainAll(names);
	}

	/*
	 * LOCK
	 */

	private boolean lock(Object value, List names) throws Exception {
		if (value instanceof String)
			return lockString((String) value, names);
		if (value instanceof List)
			return lockList((List) value, names);
		throw new Exception("Unsupported value type: " + value.getClass().getName());
	}

	private boolean lockString(String name, List names) throws Exception {
		if (lockSet.size() >= LOCK_MAX)
			return false;
		if (lockSet.contains(name))
			return false;
		if (!names.contains(name))
			return false;
		lockSet.add(name);
		return true;
	}

	private boolean lockList(List list, List names) throws Exception {
		boolean added = false;
		for (int i = 0; i < list.size(); i++)
			if (lock((String) list.get(i), names))
				added = true;
		return added;
	}

	/*
	 * UNLOCK
	 */

	private boolean unlock(Object value) throws Exception {
		if (value instanceof String)
			return unlockString((String) value);
		if (value instanceof List)
			return unlockList((List) value);
		throw new Exception("Unsupported value type: " + value.getClass().getName());
	}

	private boolean unlockString(String name) {
		return lockSet.remove(name);
	}

	private boolean unlockList(List list) {
		return lockSet.removeAll(list);
	}
}
