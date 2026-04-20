package a.entity.gus.y.entitysys1.analyze.entity;

import java.io.File;
import java.io.FileFilter;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240112";}
	
	public static final String KEY_ENTITY_NAME = "entity_name";
	public static final String KEY_FEATURES = "features";
	public static final String KEY_CREATION_DATE = "creation_date";
	public static final String KEY_LENGTH = "length";
	public static final String KEY_CALL_NB = "call_nb";
	public static final String KEY_FILE_NB = "file_nb";
	public static final String KEY_HASH = "hash";

	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_SERVICES = "services";
	public static final String KEY_LINKS = "links";
	public static final String KEY_MISSING_LINKS = "missing_links";

	private Service readFile;
	private Service nameToFile;
	private Service extract;
	private Service matchesName;

	public EntityImpl() throws Exception {
		readFile = Outside.service(this, "gus.x.file.string.read.n");
		nameToFile = Outside.service(this, "gus.x.entity.src.find.entityfile");
		extract = Outside.service(this, "gus.y.entitysys1.analyze.entity.extract");
		matchesName = Outside.service(this, "gus.x.entity.name.extract");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);
			
		String entityName = (String) o[0];
		File rootDir = (File) o[1];
		Set setRoot = (Set) o[2];                                                                                                                                                                     

		File javaFile = (File) nameToFile.t(new Object[] { rootDir, entityName });
		return extractDataFrom(javaFile, entityName, setRoot);
	}

	private Map extractDataFrom(File javaFile, String entityName, Set setRoot) throws Exception {
		try {
			String javaSrc = readFile(javaFile);
			Map extractedData = (Map) extract.t(javaSrc);

			if (!extractedData.containsKey(KEY_PACKAGE)) throw new Exception("Package not found");
			if (!extractedData.containsKey(KEY_FEATURES)) throw new Exception("Features not found");
			if (!extractedData.containsKey(KEY_HASH)) throw new Exception("Hash not found");
			if (!extractedData.containsKey(KEY_CREATION_DATE)) throw new Exception("Creation date not found");
			if (!extractedData.containsKey(KEY_IMPORTS)) throw new Exception("Imports not found");
			if (!extractedData.containsKey(KEY_RESOURCES)) throw new Exception("Resources not found");
			if (!extractedData.containsKey(KEY_SERVICES)) throw new Exception("Services not found");
			if (!extractedData.containsKey(KEY_LINKS)) throw new Exception("Links not found");

			String package1 = (String) extractedData.get(KEY_PACKAGE);
			String features = (String) extractedData.get(KEY_FEATURES);
			String hash = (String) extractedData.get(KEY_HASH);
			String creationDate = (String) extractedData.get(KEY_CREATION_DATE);
			Set imports = (Set) extractedData.get(KEY_IMPORTS);
			Set resources = (Set) extractedData.get(KEY_RESOURCES);
			Set services = (Set) extractedData.get(KEY_SERVICES);
			Set links = (Set) extractedData.get(KEY_LINKS);
			
			if (!package1.equals("a.entity." + entityName))
				throw new Exception("Invalid package value: " + package1);
			
			Set links1 = buildLinks(links, setRoot, entityName);
			Set missingLinks1 = buildMissingLinks(setRoot, resources, services, entityName);
			
			int callNb = resources.size() + services.size();
			int length = javaSrc.length();
			int fileNb = findFileNb(javaFile);

			Map data = new HashMap();
			data.put(KEY_ENTITY_NAME, entityName);
			data.put(KEY_CREATION_DATE, creationDate);
			data.put(KEY_FEATURES, features);
			data.put(KEY_LENGTH, length);
			data.put(KEY_CALL_NB, callNb);
			data.put(KEY_FILE_NB, fileNb);
			data.put(KEY_HASH, hash);
			data.put(KEY_IMPORTS, imports);
			data.put(KEY_RESOURCES, resources);
			data.put(KEY_SERVICES, services);
			data.put(KEY_LINKS, links1);
			data.put(KEY_MISSING_LINKS, missingLinks1);

			return data;
		} catch (Exception e) {
			String message = "Data extraction failed for file: " + javaFile;
			throw new Exception(message, e);
		}
	}

	private int findFileNb(File javaFile) {
		File[] ff = javaFile.getParentFile().listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isFile();
			}
		});
		return ff.length;
	}
	
	// BUILD LINKS
	
	private Set buildLinks(Set links, Set setRoot, String entityName) {
		Set links1 = new HashSet();
		Iterator it = links.iterator();
		while(it.hasNext()) {
			String linkInfo = (String) it.next();
			String[] n = linkInfo.split(":",2);
			int pos = Integer.parseInt(n[0]);
			String link = n[1];
			if(isValidLink(link, setRoot, entityName)) {
				links1.add(linkInfo);
			}
		}
		return links1;
	}
	
	private boolean isValidLink(String link, Set setRoot, String entityName) {
		return setRoot.contains(link) && !link.equals(entityName);
	}
	
	// BUILD MISSING LINKS
	
	private Set buildMissingLinks(Set setRoot, Set resources, Set services, String entityName) throws Exception {
		Set missingLinks = new HashSet();
		
		Set set = new HashSet();
		set.addAll(resources);
		set.addAll(services);
		
		Iterator it = set.iterator();
		while(it.hasNext()) {
			String s = (String) it.next();
			String[] n = s.split(":",2);
			int pos0 = Integer.parseInt(n[0]);
			String s0 = n[1];
			
			if(isValidMissingLink(s0, setRoot, entityName)) {
				missingLinks.add(s);
			}
			else if(s0.contains("#")) {
				String[] m = s0.split("#",2);
				String p1 = m[0];
				String p2 = m[1];
				
				if(isValidProvider(p1) && 
				isValidMissingLink(p2, setRoot, entityName)) {
					
					int pos1 = pos0+p1.length()+1;
					missingLinks.add(pos1+":"+p2);
				}
			}
		}
		return missingLinks;
	}
	
	private boolean isValidMissingLink(String link, Set setRoot, String entityName) throws Exception {
		if(link.equals("launch.class")) return false;
		if(link.equals("launch.args")) return false;
		if(link.equals("launch.date")) return false;
		if(link.equals("core.name")) return false;
		if(link.equals("core.build")) return false;
		if(link.equals("core.id")) return false;
		
		return matchesName.f(link) && !setRoot.contains(link) && !link.equals(entityName);
	}
	
	private boolean isValidProvider(String providerId) throws Exception {
		if(providerId.equals("path")) return false;
		if(providerId.equals("prop")) return false;
		return true;
	}

	private String readFile(File file) throws Exception {
		return (String) readFile.t(file);
	}
}
