package a.entity.gus.y.entitysrcedit1.replace;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260414";}

	public static final String CLASS_NAME = "EntityImpl";

	private Service read;
	private Service write;
	private Service findEntityFile;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.entity.src.read1");
		write = Outside.service(this, "gus.x.entity.src.write1");
		findEntityFile = Outside.service(this,"gus.x.entity.src.find.entityfile");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		Object localizer = o[2];
		String replacement = (String) o[3];

		File javaFile = (File) findEntityFile.t(new Object[]{rootDir, entityName});
		if (!javaFile.exists()) return false;

		String src = (String) read.t(javaFile);
		String result = applyLocalizer(src, localizer, replacement);
		if (result == null) return false;

		write.p(new Object[]{javaFile, result});
		return true;
	}

	private String applyLocalizer(String src, Object localizer, String replacement)
	{
		if (localizer instanceof int[])
		{
			int[] range = (int[]) localizer;
			if (range.length != 2) return null;
			int start = range[0];
			int end = range[1];
			String part1 = src.substring(0, Math.min(start, src.length()));
			String part2 = end < src.length() ? src.substring(end) : "";
			return part1 + replacement + part2;
		}
		if (localizer instanceof String)
		{
			String loc = (String) localizer;
			if (loc.length() < 2) return null;
			char mode = loc.charAt(0);
			String search = loc.substring(1);
			if (mode == '+') return replaceFirst(src, search, replacement);
			if (mode == '*') return replaceAll(src, search, replacement);
			if (mode == '-') return replaceLast(src, search, replacement);
		}
		return null;
	}

	private String replaceFirst(String src, String search, String replacement)
	{
		int idx = src.indexOf(search);
		if (idx < 0) return null;
		return src.substring(0, idx) + replacement + src.substring(idx + search.length());
	}

	private String replaceAll(String src, String search, String replacement)
	{
		if (!src.contains(search)) return null;
		return src.replace(search, replacement);
	}

	private String replaceLast(String src, String search, String replacement)
	{
		int idx = src.lastIndexOf(search);
		if (idx < 0) return null;
		return src.substring(0, idx) + replacement + src.substring(idx + search.length());
	}
}
