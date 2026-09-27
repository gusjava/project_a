package a.entity.gus.y.docsys1.dataloader.rule;

import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260901";}

	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_CONTENT = "content";

	private Service logger;
	private Service readFile;
	private Service parseRule;
	private Service insertRule;
	private Service findAll;
	private Service deleteRule;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		readFile = Outside.service(this, "gus.x.file.string.read.n");
		parseRule = Outside.service(this, "gus.y.docsys1.parse.rule");
		insertRule = Outside.service(this, "gus.y.docsys1.insert.rule");
		findAll = Outside.service(this, "gus.y.knowledgedb1.rule.findall");
		deleteRule = Outside.service(this, "gus.y.knowledgedb1.rule.delete");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		File rootDir = (File) o[1];
		Long lastTime = (Long) o[2];

		Map existingByCode = byCode((List) findAll.t(cx));

		File configDir = new File(rootDir, "a/config");
		File[] devDirs = configDir.listFiles(new FileFilter() {
			public boolean accept(File f) { return f.isDirectory(); }
		});
		if (devDirs == null) devDirs = new File[0];

		int devNb = 0;

		for (int i = 0; i < devDirs.length; i++) {
			String dev = devDirs[i].getName();
			File rulesFile = new File(devDirs[i], "doc1/fr/rules/rules.txt");
			if (!rulesFile.isFile()) continue;
			if (rulesFile.lastModified() <= lastTime.longValue()) continue;

			devNb++;
			log("Syncing rules for dev: " + dev);

			String content = (String) readFile.t(rulesFile);
			List parsed = (List) parseRule.t(content);

			Set touchedCodes = new HashSet();
			Iterator it = parsed.iterator();
			while (it.hasNext()) {
				Map row = (Map) it.next();
				String code = dev + "#" + row.get("num");
				String ruleContent = buildContent((String) row.get("title"), (String) row.get("body"));

				touchedCodes.add(code);
				Map existingRow = (Map) existingByCode.get(code);
				insertRule.p(new Object[] { cx, code, ruleContent, existingRow });
			}

			purgeRemoved(cx, dev, existingByCode, touchedCodes);
		}

		log("Rule sync done, dev(s) processed: " + devNb);
		return Integer.valueOf(devNb);
	}

	private void purgeRemoved(Connection cx, String dev, Map existingByCode, Set touchedCodes) throws Exception {
		String prefix = dev + "#";
		Iterator it = existingByCode.keySet().iterator();
		while (it.hasNext()) {
			String code = (String) it.next();
			if (!code.startsWith(prefix)) continue;
			if (touchedCodes.contains(code)) continue;

			Map row = (Map) existingByCode.get(code);
			deleteRule.p(new Object[] { cx, row.get(COL_ID) });
		}
	}

	private String buildContent(String title, String body) {
		if (title == null || title.isEmpty()) return body;
		if (body == null || body.isEmpty()) return title;
		return title + "\n\n" + body;
	}

	private Map byCode(List rows) {
		Map m = new HashMap();
		Iterator it = rows.iterator();
		while (it.hasNext()) {
			Map row = (Map) it.next();
			m.put(row.get(COL_CODE), row);
		}
		return m;
	}

	private void log(String msg) throws Exception {
		logger.p(new Object[] { this, msg });
	}
}
