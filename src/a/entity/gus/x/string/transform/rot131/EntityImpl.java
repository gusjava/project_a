package a.entity.gus.x.string.transform.rot131;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

	public EntityImpl() throws Exception {
		
	}
	
	public Object t(Object obj) throws Exception {
		String input = (String) obj;
		StringBuilder sb = new StringBuilder();
		for (char c : input.toCharArray()) {
			if (c >= 'a' && c <= 'z') sb.append((char) ('a' + (c - 'a' + 13) % 26));
			else if (c >= 'A' && c <= 'Z') sb.append((char) ('A' + (c - 'A' + 13) % 26));
			else sb.append(c);
		}
		return sb.toString();
	}
}
