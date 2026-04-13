package a.entity.gus.y.entitysys1.perform.entity.createtree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260413";}

	private Service logger;
	private Service generate;
	private Service validate;
	private Service findEntityFile;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		generate = Outside.service(this, "gus.x.entity.src.generate2");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		findEntityFile = Outside.service(this, "gus.x.entity.src.find.entityfile");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		List tree = (List) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");

		// Collect all nodes in DFS post-order (leaves first)
		List nodes = new ArrayList(); // each: Object[]{entityName, features, List<depEntityNames>}
		collectNodes(tree, devId, nodes);

		// Check that none already exist
		for (int i = 0; i < nodes.size(); i++) {
			Object[] node = (Object[]) nodes.get(i);
			String entityName = (String) node[0];
			File entityFile = (File) findEntityFile.t(new Object[]{rootDir, entityName});
			if (entityFile.exists())
				return false;
		}

		// Create all entities
		List entityNames = new ArrayList();
		for (int i = 0; i < nodes.size(); i++) {
			Object[] node = (Object[]) nodes.get(i);
			String entityName = (String) node[0];
			String features = (String) node[1];
			List deps = (List) node[2];
			boolean done = (Boolean) generate.f(new Object[]{rootDir, entityName, features, deps});
			if (!done)
				return false;
			entityNames.add(entityName);
			log("Entity added: " + entityName);
		}

		((V) engine).v("entitiesAdded", entityNames);
		return true;
	}

	private String parseEntityName(String desc, String devId) {
		int dashIdx = desc.lastIndexOf("-");
		String name = dashIdx >= 0 ? desc.substring(0, dashIdx) : desc;
		if (devId != null && !name.startsWith(devId + "."))
			name = devId + "." + name;
		return name;
	}

	private String parseFeatures(String desc) {
		int dashIdx = desc.lastIndexOf("-");
		return dashIdx >= 0 ? desc.substring(dashIdx + 1) : "";
	}

	private void collectNodes(List tree, String devId, List result) throws Exception {
		for (int i = 0; i < tree.size(); i++) {
			Object[] node = (Object[]) tree.get(i);
			String desc = (String) node[0];
			List children = (List) node[1];

			// Post-order: children first
			collectNodes(children, devId, result);

			String entityName = parseEntityName(desc, devId);
			String features = parseFeatures(desc);

			if (!validate.f(entityName))
				throw new Exception("Invalid entity name: " + entityName + " (expected: " + validate.g() + ")");

			List depNames = new ArrayList();
			for (int j = 0; j < children.size(); j++) {
				Object[] child = (Object[]) children.get(j);
				depNames.add(parseEntityName((String) child[0], devId));
			}

			result.add(new Object[]{entityName, features, depNames});
		}
	}

	/*
	 * LOGGER
	 */

	private void log(String msg) throws Exception {
		logger.p(new Object[]{this, msg});
	}
}