package a.entity.gus.y.docsys1.parse.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260901";}

	public static final String R_TITLE_LINE = "^@(\\d+) *- *(.*)$";
	public static final Pattern P_TITLE_LINE = Pattern.compile(R_TITLE_LINE);

	public Object t(Object obj) throws Exception {
		String content = (String) obj;
		String[] lines = content.split("\n", -1);

		List data = new ArrayList();
		String num = null;
		String title = null;
		List bodyLines = null;

		for (int i = 0; i < lines.length; i++) {
			String line = stripCr(lines[i]);
			Matcher m = P_TITLE_LINE.matcher(line);

			if (m.matches()) {
				flush(data, num, title, bodyLines);
				num = m.group(1);
				title = m.group(2).trim();
				bodyLines = new ArrayList();
			} else if (num != null) {
				bodyLines.add(line);
			}
		}
		flush(data, num, title, bodyLines);

		return data;
	}

	private void flush(List data, String num, String title, List bodyLines) {
		if (num == null) return;

		Map row = new HashMap();
		row.put("num", num);
		row.put("title", title);
		row.put("body", join(bodyLines).trim());
		data.add(row);
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
