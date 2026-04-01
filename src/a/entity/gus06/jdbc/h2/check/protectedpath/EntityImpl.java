package a.entity.gus06.jdbc.h2.check.protectedpath;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20260107";}

	private static final Set<String> SYSTEM_SCHEMAS = new HashSet<>();
	static {
		SYSTEM_SCHEMAS.add("INFORMATION_SCHEMA");
		SYSTEM_SCHEMAS.add("SYSTEM_LOBS");
	}

	public boolean f(Object obj) throws Exception
	{
		String path = ((String) obj).toUpperCase();
		String base = path.contains(".") ? path.substring(0, path.indexOf('.')) : path;
		return SYSTEM_SCHEMAS.contains(base);
	}
}
