package a.entity.gus.y.entitysys1.analyze.entity.extract;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240112";}
	
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	public static final String KEY_FEATURES = "features";
	public static final String KEY_HASH = "hash";
	public static final String KEY_CREATION_DATE = "creation_date";
	public static final String KEY_RESOURCES = "resources";
	public static final String KEY_SERVICES = "services";
	public static final String KEY_LINKS = "links";

	private Service toArray;
	private Service buildHash;

	public EntityImpl() throws Exception {
		toArray = Outside.service(this,"gus.x.javasrc.toarray");
		buildHash = Outside.service(this,"gus.y.entityhash1.src.build");
	}

	public Object t(Object obj) throws Exception {
		String src = (String) obj;
		String[] lines = (String[]) toArray.t(obj);

		Map data = new HashMap();

		put(data, KEY_PACKAGE, extractPackage(lines));
		put(data, KEY_FEATURES, extractFeatures(lines));
		put(data, KEY_HASH, buildHash(src));
		put(data, KEY_CREATION_DATE, extractCreationDate(lines));

		put(data, KEY_IMPORTS, extractImports(src));
		put(data, KEY_RESOURCES, extractResources(src));
		put(data, KEY_SERVICES, extractServices(src));
		put(data, KEY_LINKS, extractLinks(src));

		return data;
	}

	private Object buildHash(String src) throws Exception {
		try {
			return buildHash.t(src);
		} catch (Exception e) {
			Outside.err(this, "buildHash", e);
			return "###";
		}
	}

	private void put(Map data, String key, Object value) {
		if (value != null) data.put(key, value);
	}

	// PACKAGE

	public static final Pattern P_PACKAGE = Pattern.compile("package +([^;]+);");

	private String extractPackage(String[] lines) throws Exception {
		for (String line : lines)
			if (line.startsWith("package ")) {
				Matcher m = P_PACKAGE.matcher(line);
				if (!m.find()) throw new Exception("Package extraction failed for line: " + line);
				return m.group(1);
			}
		return null;
	}

	// FEATURES

	private String extractFeatures(String[] lines) throws Exception {
		for (String line : lines)
			if (line.startsWith("public class EntityImpl ")) {
				StringBuffer b = new StringBuffer();

				boolean extendsS1 = line.contains(" extends S1 ");
				String[] n = line.split(" implements ");
				if (n.length != 2) throw new Exception("Invalid entity class header: " + line);

				String part = " " + n[1].replace(",", " ") + " ";

				if (part.contains(" B "))
					b.append("b");
				if (part.contains(" E "))
					b.append("e");
				if (part.contains(" F "))
					b.append("f");
				if (part.contains(" G "))
					b.append("g");
				if (part.contains(" H "))
					b.append("h");
				if (part.contains(" I "))
					b.append("i");
				if (part.contains(" P "))
					b.append("p");
				if (part.contains(" R "))
					b.append("r");
				if (extendsS1 || part.contains(" S "))
					b.append("s");
				if (part.contains(" T "))
					b.append("t");
				if (part.contains(" V "))
					b.append("v");
				return b.toString();
			}
		return null;
	}

	// CREATION DATE

	public static final Pattern P_CREATIONDATE = Pattern.compile("\"([^\"]+)\"");

	private String extractCreationDate(String[] lines) throws Exception {
		for (int i = 0; i < lines.length; i++)
			if (lines[i].startsWith("public String creationDate()")) {
				Matcher m = P_CREATIONDATE.matcher(lines[i]);
				if (m.find()) return m.group(1);

				m = P_CREATIONDATE.matcher(lines[i + 1]);
				if (!m.find()) throw new Exception("CreationDate extraction failed for line: " + lines[i + 1]);
				return m.group(1);
			}
		return null;
	}

	// IMPORTS

	public static final String R_IMPORTS = "import\\s+([\\w\\.\\*]+);";
	public static final Pattern P_IMPORTS = Pattern.compile(R_IMPORTS, Pattern.DOTALL);

	public static final String R_STATIC_IMPORTS = "import\\s+(static\\s+)?([\\w\\.\\*]+);";
	public static final Pattern P_STATIC_IMPORTS = Pattern.compile(R_STATIC_IMPORTS, Pattern.DOTALL);

	private Set extractImports(String src) throws Exception {
		Set set = new HashSet();
		Matcher m = P_IMPORTS.matcher(src);
		while(m.find()) {
			String value = m.group(1);
			if(!value.startsWith("a.framework.")) set.add(value);
		}
		m = P_STATIC_IMPORTS.matcher(src);
		while(m.find()) {
			String value = m.group(2);
			if(!value.startsWith("a.framework.")) set.add(value);
		}
		return set;
	}

	// RESOURCES

	public static final String R_RESOURCES = "Outside\\.resource\\( *this *, *\"([^\"]+)\" *\\)";
	public static final Pattern P_RESOURCES = Pattern.compile(R_RESOURCES, Pattern.DOTALL);

	private Set extractResources(String src) throws Exception {
		Set set = new HashSet();
		Matcher m = P_RESOURCES.matcher(src);
		while(m.find()) set.add(groupInfo(m,1));
		return set;
	}

	// SERVICES

	public static final String R_SERVICES = "Outside\\.service\\( *this *, *\"([^\"]+)\" *\\)";
	public static final Pattern P_SERVICES = Pattern.compile(R_SERVICES, Pattern.DOTALL);

	private Set extractServices(String src) throws Exception {
		Set set = new HashSet();
		Matcher m = P_SERVICES.matcher(src);
		while(m.find()) set.add(groupInfo(m,1));
		return set;
	}

	// LINKS
	
	public static final String R_LINKS = "[a-z][a-z0-9]{2,9}(\\.[a-z][a-z0-9_]*)+";
	public static final Pattern P_LINKS = Pattern.compile(R_LINKS, Pattern.DOTALL);

	private Set extractLinks(String src) throws Exception {
		Set set = new HashSet();
		Matcher m = P_LINKS.matcher(src);
		while(m.find()) set.add(groupInfo(m,0));
		return set;
	}
	
	// GROUP INFO
	
	private String groupInfo(Matcher m, int index) {
		int pos = m.start(index);
		String value = m.group(index);
		return pos+":"+value;
	}
}
