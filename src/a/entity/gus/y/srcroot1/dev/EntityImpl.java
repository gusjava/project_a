package a.entity.gus.y.srcroot1.dev;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, P, R {
	public String creationDate() {return "20240110";}
	
	public static final String TYPE_UNDEFINED = "undefined";
	public static final String TYPE_PARAM = "param";
	public static final String TYPE_PERSISTED = "persisted";

	private Service persister1;

	private String persistKey = getClass().getName() + "_devId";
	private String paramDevId;
	private String devId;
	private String type = TYPE_UNDEFINED;

	public EntityImpl() throws Exception {
		persister1 = Outside.service(this, "gus.y.persist1.main");
		paramDevId = (String) Outside.resource(this, "param#dev");
	}

	public Object g() throws Exception {
		return getDevId();
	}

	public void p(Object obj) throws Exception {
		devId = (String) obj;
		persister1.v(persistKey, devId);
		type = devId != null ? TYPE_PERSISTED : TYPE_UNDEFINED;
		changed();
	}

	public Object r(String key) throws Exception {
		if (key.equals("type"))
			return getType();
		if (key.equals("devId"))
			return getDevId();
		if (key.equals("display"))
			return getDisplay();

		if (key.equals("keys"))
			return new String[] { "type", "devId", "display" };
		throw new Exception("Unknown key: " + key);
	}

	private String getDevId() throws Exception {
		if (devId == null)
			init();
		return devId;
	}

	private String getType() throws Exception {
		if (devId == null)
			init();
		return type;
	}

	private String getDisplay() throws Exception {
		return getDevId() + " [" + type + "]";
	}

	private void init() throws Exception {
		devId = (String) persister1.r(persistKey);
		if (devId != null) {
			type = TYPE_PERSISTED;
			return;
		}
		devId = paramDevId;
		if (devId != null) {
			type = TYPE_PARAM;
			return;
		}
		type = TYPE_UNDEFINED;
	}

	private void changed() {
		send(this, "changed()");
	}
}
