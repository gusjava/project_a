package a.entity.gus.y.config1.load.text;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service getConfigRoot;
	private Service pathToInputStream;
	private Service inputStreamToText;

	public EntityImpl() throws Exception {
		getConfigRoot = Outside.service(this, "m006_configroot");
		pathToInputStream = Outside.service(this, "m011_read_inputstream");
		inputStreamToText = Outside.service(this, "gus.x.io.build.string");
	}

	public Object t(Object obj) throws Exception {
		String loc = (String) obj;

		String configRoot = (String) getConfigRoot.g();
		String configPath = configRoot + loc;
		InputStream configIs = (InputStream) pathToInputStream.t(configPath);
		if (configIs != null)
			return inputStreamToText(configIs);

		String commonRoot = (String) getConfigRoot.t("gus.common");
		String commonPath = commonRoot + loc;
		InputStream commonIs = (InputStream) pathToInputStream.t(commonPath);
		if (commonIs != null)
			return inputStreamToText(commonIs);

		return null;
	}

	private String inputStreamToText(InputStream is) throws Exception {
		return (String) inputStreamToText.t(is);
	}
}
