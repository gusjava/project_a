package a.entity.gus.y.entitysys1.insert.xyzerr;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_SERVICES = "services";
	public static final String KEY_LINKS = "links";
	public static final String KEY_MISSING_LINKS = "missing_links";
	
	private Service insert;
	
	public EntityImpl() throws Exception {
		insert = Outside.service(this, "gus.y.entitydb1.entity_xyz_err.insert");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		Map data = (Map) o[1];
		
		String entityName = (String) data.get(KEY_ENTITY_NAME);
		Set errLinks = findErrLinks(entityName, data);
		if(errLinks!=null) insertAll(cx, entityName, errLinks);
	}
	
	private Set findErrLinks(String entityName, Map data) {
		String[] n = entityName.split("\\.");
		String type = findType(n);
		
		if(type==null) return null;
		
		Set resources = (Set) data.get(KEY_RESOURCES);
		Set services = (Set) data.get(KEY_SERVICES);
		Set links = (Set) data.get(KEY_LINKS);
		Set missingLinks = (Set) data.get(KEY_MISSING_LINKS);
		
		Set errLinks = new HashSet();
		if(type.equals("x")) handleTypeX(errLinks, resources, services);
		else if(type.equals("y")) handleTypeY(errLinks, links);
		else if(type.equals("z")) handleTypeZ(errLinks, n, links);
		
		return errLinks;
	}
	
	private String findType(String[] n) {
		if(n.length<3) return null;
		if(n[1].equals("x")) return "x";
		if(n[1].equals("y")) return "y";
		if(n[1].equals("z")) return "z";
		return null;
	}
	
	// HANDLE TYPE
	
	private void handleTypeX(Set errLinks, Set resources, Set services) {
		errLinks.addAll(resources);
		errLinks.addAll(services);
	}
	
	private void handleTypeY(Set errLinks, Set links) {
		Iterator it = links.iterator();
		while(it.hasNext()) {
			String link = (String) it.next();
			if(!isValidLinkForY(link)) errLinks.add(link);
		}
	}
	
	private void handleTypeZ(Set errLinks, String[] n, Set links) {
		Iterator it = links.iterator();
		while(it.hasNext()) {
			String link = (String) it.next();
			if(!isValidLinkForZ(n, link)) errLinks.add(link);
		}
	}
	
	// IS VALID LINK
	
	private boolean isValidLinkForY(String link) {
		String[] k = link.split("\\.");
		return k[1].equals("x") || k[1].equals("y");
	}
	
	private boolean isValidLinkForZ(String[] n, String link) {
		String[] k = link.split("\\.");
		return k[1].equals("x") || k[1].equals("y") || (k[1].equals("z") && k[2].equals(n[2]));
	}
	
	// INSERT ALL
	
	private void insertAll(Connection cx, String entityName, Set errLinks) throws Exception {
		Iterator it = errLinks.iterator();
		while (it.hasNext()) {
			String errInfo = (String) it.next();
			String[] n = errInfo.split(":",2);
			Integer pos = Integer.parseInt(n[0]);
			String errLink = n[1];
			insert.p(new Object[] {cx, entityName, errLink, pos});
		}
	}
}
