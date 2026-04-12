package a.entity.gus.y.dataview1.object.builder.name;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.I;
import a.framework.P;
import a.framework.R;
import a.framework.T;
import a.framework.V;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	public static final String CLASS = "gus.y.dataview1.class1";
	public static final String EXCEPTION = "gus.y.dataview1.exception";

	public static final String STRING = "gus.y.dataview1.string";
	public static final String DATE = "gus.y.dataview1.date";
	public static final String URL = "gus.y.dataview1.url";
	public static final String FILE = "gus.y.dataview1.file";

	public static final String MAP = "gus.y.dataview1.map";
	public static final String LIST = "gus.y.dataview1.list";
	public static final String SET = "gus.y.dataview1.set";
	public static final String ARRAY = "gus.y.dataview1.array";

	public static final String ENTITY = "gus.y.dataview1.entity";
	public static final String E = "gus.y.dataview1.feature.e";
	public static final String I = "gus.y.dataview1.feature.i";
	public static final String G = "gus.y.dataview1.feature.g";
	public static final String T = "gus.y.dataview1.feature.t";
	public static final String F = "gus.y.dataview1.feature.f";
	public static final String P = "gus.y.dataview1.feature.p";
	public static final String R = "gus.y.dataview1.feature.r";
	public static final String V = "gus.y.dataview1.feature.v";

	public Object t(Object obj) throws Exception {
		Map names = new LinkedHashMap();

		if (obj instanceof Class)
			names.put("Class", CLASS);
		if (obj instanceof Exception)
			names.put("Exception", EXCEPTION);

		if (obj instanceof String)
			names.put("String", STRING);
		if (obj instanceof Date)
			names.put("Date", DATE);
		if (obj instanceof URL)
			names.put("URL", URL);
		if (obj instanceof File)
			names.put("File", FILE);

		if (obj instanceof Map)
			names.put("Map", MAP);
		if (obj instanceof List)
			names.put("List", LIST);
		if (obj instanceof Set)
			names.put("Set", SET);
		if (obj instanceof Object[])
			names.put("Array", ARRAY);

		if (obj instanceof Entity)
			names.put("Entity", ENTITY);
		if (obj instanceof E)
			names.put("E", E);
		if (obj instanceof I)
			names.put("I", I);
		if (obj instanceof G)
			names.put("G", G);
		if (obj instanceof T)
			names.put("T", T);
		if (obj instanceof F)
			names.put("F", F);
		if (obj instanceof P)
			names.put("P", P);
		if (obj instanceof R)
			names.put("R", R);
		if (obj instanceof V)
			names.put("V", V);

		return names;
	}
}
