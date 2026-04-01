package a.entity.gus06.y.entitysys1.buildlocker;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251114";}
	
	public static final int LOCK_MAX = 100;


	private Service fileAccess;
	private Service setToString;
	private Service stringToSet;

	public EntityImpl() throws Exception
	{
		fileAccess = Outside.service(this,"gus06.file.access.string");
		setToString = Outside.service(this,"gus06.tostring.set");
		stringToSet = Outside.service(this,"gus06.string.split.lines1.set");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	
	private class Holder implements G, F, P
	{
		private File file;
		private Set lockSet;
		private Object access;
		
		public Holder(File file) throws Exception
		{
			this.file = file;
			access = fileAccess.t(file);
			lockSet = load();
		}
	
		public Object g() throws Exception
		{return lockSet;}
	
		public void p(Object obj) throws Exception
		{f(obj);}
	
		public boolean f(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			String cmd = (String) o[0];
	
			if (cmd.equals("update"))  return update((List) o[1]);
			if (cmd.equals("lock"))  return lock(o[1], (List) o[2]);
			if (cmd.equals("unlock"))  return unlock(o[1]);
			return true;
		}
	
		private boolean update(List names)
		{return lockSet.retainAll(names);}
	
		/*
		 * LOCK
		 */
	
		private boolean lock(Object value, List names) throws Exception
		{
			if (value instanceof String) return lockString((String) value, names);
			if (value instanceof List) return lockList((List) value, names);
			throw new Exception("Unsupported value type: " + value.getClass().getName());
		}
	
		private boolean lockString(String name, List names) throws Exception
		{
			if (lockSet.size() >= LOCK_MAX) return false;
			if (lockSet.contains(name)) return false;
			if (!names.contains(name)) return false;
			lockSet.add(name);
			write();
			return true;
		}
	
		private boolean lockList(List list, List names) throws Exception
		{
			boolean added = false;
			for (int i = 0; i < list.size(); i++)
			if (lock((String) list.get(i), names)) added = true;
			
			if(added) write();
			return added;
		}
	
		/*
		 * UNLOCK
		 */
	
		private boolean unlock(Object value) throws Exception
		{
			if (value instanceof String) return unlockString((String) value);
			if (value instanceof List) return unlockList((List) value);
			throw new Exception("Unsupported value type: " + value.getClass().getName());
		}
	
		private boolean unlockString(String name) throws Exception
		{
			boolean removed = lockSet.remove(name);
			if(removed) write();
			return removed;
		}
	
		private boolean unlockList(List list) throws Exception
		{
			boolean removed = lockSet.removeAll(list);
			if(removed) write();
			return removed;
		}
	
		/*
		 * WRITE
		 */
		
		private void write() throws Exception
		{((P)access).p(setToString.t(lockSet));}
	
		/*
		 * LOAD
		 */
		
		private Set load() throws Exception
		{
			String s = ((String) ((G) access).g()).trim();
			if(s.equals("")) return new HashSet();
			return (Set) stringToSet.t(s);
		}
	}
}