package a.entity.gus.y.docsys1.parse.entry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260901";}

	public static final String R_KEY_LINE = "^@([a-z_]+)$";
	public static final Pattern P_KEY_LINE = Pattern.compile(R_KEY_LINE);

	public Object t(Object obj) throws Exception {
		String content = (String) obj;
		String[] lines = content.split("\n", -1);

		Map data = new LinkedHashMap();
		String currentKey = null;
		List currentLines = null;

		for (int i = 0; i < lines.length; i++) {
			String line = stripCr(lines[i]);
			Matcher m = P_KEY_LINE.matcher(line.trim());

			if (m.matches()) {
				flush(data, currentKey, currentLines);
				currentKey = m.group(1);
				currentLines = new ArrayList();
			} else if (currentKey != null) {
				currentLines.add(line);
			}
		}
		flush(data, currentKey, currentLines);

		return data;
	}

	private void flush(Map data, String key, List lines) {
		if (key == null) return;

		String body = join(lines).trim();

		List values = (List) data.get(key);
		if (values == null) {
			values = new ArrayList();
			data.put(key, values);
		}
		values.add(body);
	}

	private String join(List lines) {
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < lines.size(); i++) {
			if (i > 0) b.append("\n");
			b.append(lines.get(i));
		}
		return b.toString();
	}

	private String stripCr(String line) {
		return line.endsWith("\r") ? line.substring(0, line.length() - 1) : line;
	}
}
