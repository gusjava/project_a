package a.entity.gus.x.tostring.set;

import java.util.Iterator;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231128";}

	public Object t(Object obj) throws Exception {
		return setToString((Set) obj);
	}

	private String setToString(Set set) throws Exception {
		StringBuffer b = new StringBuffer();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String el = (String) it.next();
			if (el.contains("\n"))
				throw new Exception("Invalid element: " + el);
			b.append(el + "\n");
		}
		if (b.length() > 0)
			b.deleteCharAt(b.length() - 1);
		return b.toString();
	}
}