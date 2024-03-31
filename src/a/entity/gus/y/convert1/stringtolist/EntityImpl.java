package a.entity.gus.y.convert1.stringtolist;

import java.util.ArrayList;
import java.util.List;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240121";}

	public Object t(Object obj) throws Exception {
		return stringToList((String) obj);
	}

	private List stringToList(String s) throws Exception {
		String[] lines = s.split("\n");
		List list = new ArrayList();
		for (String line : lines) {
			list.add(line);
		}
		return list;
	}
}