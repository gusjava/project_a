package a.entity.gus.y.convert1.stringtoset;

import java.util.HashSet;
import java.util.Set;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240121";}

	public Object t(Object obj) throws Exception {
		return stringToSet((String) obj);
	}

	private Set stringToSet(String s) throws Exception {
		String[] lines = s.split("\n");
		Set set = new HashSet();
		for (String line : lines) {
			set.add(line);
		}
		return set;
	}
}