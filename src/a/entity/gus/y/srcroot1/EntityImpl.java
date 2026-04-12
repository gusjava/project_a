package a.entity.gus.y.srcroot1;

import java.io.File;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, G, P, R {
	public String creationDate() {return "20231202";}
	
	public static final String TYPE_UNDEFINED = "undefined";
	public static final String TYPE_DEFAULT = "default";
	public static final String TYPE_PERSISTED = "persisted";

	private Service fileDisplay;
	private Service getDefaultDir;
	private Service persister1;
	
	private String persistKey = getClass().getName() + "_rootDir";
	private File defaultRootDir;
	private File rootDir;
	private String type = TYPE_UNDEFINED;

	public EntityImpl() throws Exception {
		fileDisplay = Outside.service(this, "gus.x.file.getdisplay1");
		getDefaultDir = Outside.service(this, "gus.y.srcroot1.defaultdir");
		persister1 = Outside.service(this, "gus.y.persist1.main");
		defaultRootDir = (File) getDefaultDir.g();
	}

	public Object g() throws Exception {
		return getRootDir();
	}

	public void p(Object obj) throws Exception {
		rootDir = (File) obj;
		persister1.v(persistKey, getRootPath());
		type = rootDir != null ? TYPE_PERSISTED : TYPE_UNDEFINED;
		changed();
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
		String path = (String) persister1.r(persistKey);
		if (path != null) {
			rootDir = new File(path);
			type = TYPE_PERSISTED;
			return;
		}
		rootDir = defaultRootDir;
		if (rootDir != null) {
			type = TYPE_DEFAULT;
			return;
		}
		type = TYPE_UNDEFINED;
	}

	private void changed() {
		send(this, "changed()");
	}
}
