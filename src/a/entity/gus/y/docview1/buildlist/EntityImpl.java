package a.entity.gus.y.docview1.buildlist;

import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231231";}

	public EntityImpl() throws Exception {
		
	}
	
	public Object t(Object obj) throws Exception {
		String text = (String) obj;
		if (text == null)
			return new ArrayList();
		String[] lines = text.split("\n");

		List list = new ArrayList();
		StringBuilder sb = null;
		String head = null;

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.startsWith("@")) {
				if (sb != null) {
					list.add(new String[] { head, sb.toString() });
				}
				head = line.substring(1);
				sb = new StringBuilder();
			} else if (sb != null)
				sb.append(line + "\n");
		}
		if (sb != null)
			list.add(new String[] { head, sb.toString() });
		return list;
	}
}
