package a.entity.gus06.x.javasrc.toarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}

	public Object t(Object obj) throws Exception
	{
		if (obj instanceof String[]) return obj;
		if (obj instanceof String) return toArray((String) obj);
		throw new Exception("Invalid data type: " + obj.getClass().getName());
	}

	private String[] toArray(String text)
	{
		String[] lines = text.split("[\n\r]+");
		for (int i = 0; i < lines.length; i++) lines[i] = format(lines[i]);
		return lines;
	}

	private String format(String line)
	{
		line = line.replace("\t", "").trim();
		while (line.contains("  ")) line = line.replace("  ", " ");
		return line;
	}
}
