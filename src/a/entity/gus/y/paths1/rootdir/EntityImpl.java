package a.entity.gus.y.paths1.rootdir;

import java.io.File;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G, R {
	public String creationDate() {return "20231128";}
	
	public static final String KEY_ROOT = "root";
	public static final String TYPE_DEFAULT = "default";
	public static final String TYPE_PARAM = "param";

	private Service fileDisplay;
	private Service pathToFile;
	private Service defaultRoot;
	private Map params;

	private File rootDir;
	private String type;

	public EntityImpl() throws Exception {
		fileDisplay = Outside.service(this, "gus.x.file.getdisplay1");
		pathToFile = Outside.service(this, "gus.x.file.build.path.os");
		defaultRoot = Outside.service(this, "gus.y.paths1.rootdir.defaultroot");
		params = (Map) Outside.resource(this, "params");
	}

	public Object g() throws Exception {
		return getRootDir();
	}

	public Object r(String key) throws Exception {
		if (key.equals("type"))
			return getType();
		if (key.equals("rootDir"))
			return getRootDir();
		if (key.equals("rootPath"))
			return getRootPath();
		if (key.equals("display"))
			return getDisplay();

		if (key.equals("keys"))
			return new String[] { "type", "rootDir", "rootPath", "display" };
		throw new Exception("Unknown key: " + key);
	}
	
	private File getRootDir() throws Exception {
		if (rootDir == null)
			init();
		return rootDir;
	}
	
	private String getRootPath() throws Exception {
		return rootDir != null ? rootDir.getCanonicalPath() : null;
	}

	private String getType() throws Exception {
		if (rootDir == null)
			init();
		return type;
	}

	private String getDisplay() throws Exception {
		return fileDisplay.t(getRootDir()) + " [" + type + "]";
	}

	private void init() throws Exception {
		type = params.containsKey(KEY_ROOT) ? TYPE_PARAM : TYPE_DEFAULT;
		rootDir = build();
		rootDir.mkdirs();
	}

	private File build() throws Exception {
		if (params.containsKey(KEY_ROOT))
			return (File) pathToFile.t(params.get(KEY_ROOT));
		return (File) defaultRoot.g();
	}
}
