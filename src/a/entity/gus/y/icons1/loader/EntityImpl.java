package a.entity.gus.y.icons1.loader;

import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, T, F, R {
	public String creationDate() {return "20231128";}

	private Service configIcon;

	public EntityImpl() throws Exception {
		configIcon = Outside.service(this, "configicon");
	}

	public Object t(Object obj) throws Exception {
		return r((String) obj);
	}

	public Object r(String key) throws Exception {
		return (Icon) configIcon.t(key);
	}

	public boolean f(Object obj) throws Exception {
		return configIcon.f(obj);
	}
}