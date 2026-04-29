package a.core.gus.gyem.m065.g.prop.outside.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import a.core.gus.gyem.GyemSystem;
import a.framework.G;

public class Module extends GyemSystem implements G {

	public static final String FILENAME = "app.properties";

	public Object g() throws Exception {
		File rootDir = (File) moduleG(M064_G_PARAM_ROOTDIR).g();
		if (rootDir == null) return null;
		File file = new File(rootDir, FILENAME);
		if (!file.isFile()) return null;
		Properties p = new Properties();
		try (FileInputStream fis = new FileInputStream(file)) {
			p.load(fis);
		}
		return p;
	}
}
