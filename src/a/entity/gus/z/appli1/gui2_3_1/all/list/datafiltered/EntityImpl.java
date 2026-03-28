package a.entity.gus.z.appli1.gui2_3_1.all.list.datafiltered;

import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240114";}
	
	private Service filterListName;
	private Service filterListFullText;

	public EntityImpl() throws Exception {
		filterListName = Outside.service(this, "gus.z.appli1.gui2_3_1.all.list.filter.name");
		filterListFullText = Outside.service(this,"gus.z.appli1.gui2_3_1.all.list.filter.fulltext");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Invalid data number: " + o.length);

		R engine = (R) o[0];
		List dataFull = (List) o[1];
		String search = (String) o[2];

		if (dataFull == null)
			return null;

		String devId = devId(engine);
		Set lockSet = lockSet(engine);
		Set errorSet = errorSet(engine);

		if(search.startsWith("'"))
			return filterListFullText.t(new Object[] { 
				engine, dataFull, search.substring(1), devId, lockSet, errorSet });
				
		return filterListName.t(new Object[] { 
			dataFull, search, devId, lockSet, errorSet });
	}

	private Map compileErrMap(R engine) throws Exception {
		return (Map) engine.r("compileErrMap");
	}

	private Set errorSet(R engine) throws Exception {
		Map m = compileErrMap(engine);
		return m != null ? m.keySet() : null;
	}

	private String devId(R engine) throws Exception {
		return (String) engine.r("devId");
	}

	private Set lockSet(R engine) throws Exception {
		return (Set) engine.r("lockSet");
	}
}
