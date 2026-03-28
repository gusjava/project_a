package a.entity.gus.y.cust1.load.icon;

import java.net.URL;

import javax.swing.ImageIcon;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231129";}

	private Service getConfigRoot;
	private String[] commonConfigIds;

	public EntityImpl() throws Exception {
		getConfigRoot = Outside.service(this, "configroot");
		
		String commonConfigVal = (String) Outside.resource(this, "prop#configid.common");
		commonConfigIds = commonConfigVal!=null ? commonConfigVal.split(";") : new String[0];
	}

	public Object t(Object obj) throws Exception {
		String iconId = (String) obj;
		String loc = "icon/" + iconId + ".gif";

		String configRoot = (String) getConfigRoot.g();
		String configPath = configRoot + loc;
		URL configUrl = getClass().getResource(configPath);
		if (configUrl != null)
			return new ImageIcon(configUrl);

		for (String commonConfigId : commonConfigIds) {
			String commonRoot = (String) getConfigRoot.t(commonConfigId);
			String commonPath = commonRoot + loc;
			URL commonUrl = getClass().getResource(commonPath);
			if (commonUrl != null)
				return new ImageIcon(commonUrl);
		}
		return null;
	}
}
