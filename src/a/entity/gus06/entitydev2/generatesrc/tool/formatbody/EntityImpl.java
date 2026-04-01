package a.entity.gus06.entitydev2.generatesrc.tool.formatbody;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251205";}

	public Object t(Object obj) throws Exception
	{
		String body = (String) obj;
		String raw = body.trim();
		if(raw.startsWith("{")) raw = raw.substring(1);
		if(raw.endsWith("}")) raw = raw.substring(0, raw.length()-1);
		raw = raw.trim();
		
		String[] lines = raw.split("\\r?\\n");
		StringBuilder sb = new StringBuilder();
		for(String line : lines)
		sb.append("\t\t").append(line.trim()).append("\n");
		return sb.toString();
	}
}
