package a.entity.gus.y.docsys1.insert.rule;

import java.sql.Connection;
import java.util.Map;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260901";}

	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_CONTENT = "content";

	private Service insertRow;
	private Service updateRow;

	public EntityImpl() throws Exception {
		insertRow = Outside.service(this, "gus.y.knowledgedb1.rule.insert");
		updateRow = Outside.service(this, "gus.y.knowledgedb1.rule.update");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String code = (String) o[1];
		String content = (String) o[2];
		Map existingRow = (Map) o[3];

		Map data = new java.util.HashMap();
		data.put(COL_CODE, code);
		data.put(COL_CONTENT, content);

		if (existingRow == null) {
			insertRow.t(new Object[] { cx, data });
		} else {
			data.put(COL_ID, existingRow.get(COL_ID));
			updateRow.p(new Object[] { cx, data });
		}
	}
}
