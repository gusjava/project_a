package a.entity.gus.y.config1.inputstream.jar;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service getConfigRoot;
	private Service pathToInputStream;
	private String[] commonConfigIds;

	public EntityImpl() throws Exception {
		getConfigRoot = Outside.service(this, "m006_configroot");
		pathToInputStream = Outside.service(this, "m011_read_inputstream");
		
		String commonConfigVal = (String) Outside.resource(this, "prop#configid.common");
		commonConfigIds = commonConfigVal!=null ? commonConfigVal.split(";") : new String[0];
	}

	public Object t(Object obj) throws Exception {
		String jarName = (String) obj;
		String loc = "jar/" + jarName;

		String configRoot = (String) getConfigRoot.g();
		String configPath = configRoot + loc;
		InputStream configIs = (InputStream) pathToInputStream.t(configPath);
		if (configIs != null)
			return configIs;

		for (String commonConfigId : commonConfigIds) {
			String commonRoot = (String) getConfigRoot.t(commonConfigId);
			String commonPath = commonRoot + loc;
			InputStream commonIs = (InputStream) pathToInputStream.t(commonPath);
			if (commonIs != null)
				return commonIs;
		}
		return null;
	}
}
