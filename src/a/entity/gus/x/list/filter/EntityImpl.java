package a.entity.gus.x.list.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import a.framework.Entity;
import a.framework.F;
import a.framework.P;
import a.framework.T;

public class EntityImpl implements Entity, P, T, F {
	public String creationDate() {return "20240113";}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		List list = (List) o[0];
		F filter = (F) o[1];

		List newList = new ArrayList();
		for (Object elem : list) {
			if (filter.f(elem))
				newList.add(elem);
		}
		return newList;
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		List list = (List) o[0];
		F filter = (F) o[1];

		boolean changed = false;
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object elem = it.next();
			if (!filter.f(elem)) {
				it.remove();
				changed = true;
			}
		}
		return changed;
	}
	
	public void p(Object obj) throws Exception {
		f(obj);
	}
}
