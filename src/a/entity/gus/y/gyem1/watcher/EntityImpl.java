package a.entity.gus.y.gyem1.watcher;

import java.util.List;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl implements Entity, R {
	public String creationDate() {return "20231129";}

	public static final long LAPSE = 1500;

	private Service timer;

	private List listErr;
	private Map mapEntityLoaded;
	private Map mapEntityUnique;

	private int nbErr = 0;
	private int nbEntityLoaded = 0;
	private int nbEntityUnique = 0;

	private S1 supportErr = new S1();
	private S1 supportEntityLoaded = new S1();
	private S1 supportEntityUnique = new S1();

	public EntityImpl() throws Exception {
		timer = Outside.service(this, "gus.y.timer1.register");

		listErr = (List) Outside.resource(this, "errlist");
		mapEntityLoaded = (Map) Outside.resource(this, "entityclassmap");
		mapEntityUnique = (Map) Outside.resource(this, "uniqueentitymap");

		timer.v("" + LAPSE, (E) this::check);
	}

	private void check() {
		int nbErr1 = listErr.size();
		if (nbErr != nbErr1) {
			nbErr = nbErr1;
			supportErr.send(this, "changed()");
		}
		int nbEntityLoaded1 = mapEntityLoaded.size();
		if (nbEntityLoaded != nbEntityLoaded1) {
			nbEntityLoaded = nbEntityLoaded1;
			supportEntityLoaded.send(this, "changed()");
		}

		int nbEntityUnique1 = mapEntityUnique.size();
		if (nbEntityUnique != nbEntityUnique1) {
			nbEntityUnique = nbEntityUnique1;
			supportEntityUnique.send(this, "changed()");
		}
	}

	public Object r(String key) throws Exception {
		if (key.equals("supportErr"))
			return supportErr;
		if (key.equals("supportEntityLoaded"))
			return supportEntityLoaded;
		if (key.equals("supportEntityUnique"))
			return supportEntityUnique;

		if (key.equals("keys"))
			return new String[] { "supportErr", "supportEntityLoaded", "supportEntityUnique" };

		throw new Exception("Unknown key: " + key);
	}
}
