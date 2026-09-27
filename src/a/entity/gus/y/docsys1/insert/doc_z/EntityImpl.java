package a.entity.gus.y.docsys1.insert.doc_z;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260901";}

	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_DESCRIPTION = "description";
	public static final String COL_STATE = "state";
	public static final String COL_DIFFICULTY_LEVEL = "difficulty_level";
	public static final String COL_ISSUE_LEVEL = "issue_level";
	public static final String COL_COMMENT = "comment";
	public static final String COL_GENERATOR = "generator";
	public static final String COL_GENERATED_TIME = "generated_time";

	private Service insertRow;
	private Service updateRow;
	private Service deleteTags;
	private Service insertTag;

	public EntityImpl() throws Exception {
		insertRow = Outside.service(this, "gus.y.knowledgedb1.doc_z.insert");
		updateRow = Outside.service(this, "gus.y.knowledgedb1.doc_z.update");
		deleteTags = Outside.service(this, "gus.y.knowledgedb1.doc_z_tag.delete");
		insertTag = Outside.service(this, "gus.y.knowledgedb1.doc_z_tag.insert");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String entityName = (String) o[1];
		Map rawMap = (Map) o[2];
		Map existingRow = (Map) o[3];

		Map data = buildData(entityName, rawMap, existingRow);

		Long id;
		if (existingRow == null) {
			id = (Long) insertRow.t(new Object[] { cx, data });
		} else {
			id = (Long) existingRow.get(COL_ID);
			data.put(COL_ID, id);
			updateRow.p(new Object[] { cx, data });
		}

		deleteTags.p(new Object[] { cx, id });
		insertTags(cx, id, firstBody(rawMap, "tags"));
	}

	private Map buildData(String entityName, Map rawMap, Map existingRow) {
		Map data = new java.util.HashMap();
		data.put(COL_NAME, entityName);
		data.put(COL_DESCRIPTION, firstBody(rawMap, "description"));
		data.put(COL_STATE, firstBody(rawMap, "state"));
		data.put(COL_DIFFICULTY_LEVEL, parseDifficulty(firstBody(rawMap, "difficulty")));
		data.put(COL_COMMENT, firstBody(rawMap, "comment"));
		data.put(COL_GENERATOR, firstBody(rawMap, "generator"));
		data.put(COL_GENERATED_TIME, firstBody(rawMap, "generated_time"));
		data.put(COL_ISSUE_LEVEL, existingRow != null ? existingRow.get(COL_ISSUE_LEVEL) : Integer.valueOf(0));
		return data;
	}

	private void insertTags(Connection cx, Long id, String tagsLine) throws Exception {
		if (tagsLine == null || tagsLine.trim().isEmpty()) return;

		String[] tags = tagsLine.split(",");
		for (int i = 0; i < tags.length; i++) {
			String tag = tags[i].trim();
			if (!tag.isEmpty()) insertTag.p(new Object[] { cx, id, tag });
		}
	}

	private Integer parseDifficulty(String value) {
		if (value == null || value.trim().isEmpty()) return Integer.valueOf(0);
		return Integer.valueOf(value.trim());
	}

	private String firstBody(Map rawMap, String key) {
		List bodies = (List) rawMap.get(key);
		if (bodies == null || bodies.isEmpty()) return null;
		return (String) bodies.get(0);
	}
}
