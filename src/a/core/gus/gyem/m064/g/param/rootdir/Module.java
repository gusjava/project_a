package a.core.gus.gyem.m064.g.param.rootdir;

import java.io.File;
import java.util.Map;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {

	public static final String KEY_ROOT = "root";

	private File rootDir;
	private boolean initialized = false;

	public Object g() throws Exception {
		if (!initialized) init();
		return rootDir;
	}

	private void init() throws Exception {
		initialized = true;
		Map params = (Map) moduleG(M008_G_PARAM).g();
		if (!params.containsKey(KEY_ROOT)) return;
		rootDir = new File((String) params.get(KEY_ROOT));
	}
}
