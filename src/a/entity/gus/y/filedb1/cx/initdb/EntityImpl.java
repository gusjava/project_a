package a.entity.gus.y.filedb1.cx.initdb;

import a.framework.*;

public class EntityImpl implements Entity, G, P {
	public String creationDate() {return "20240125";}

	public static final String STRUCT_LAST_UPDATE = "2024-01-25 12:20:00"; // yyyy-MM-dd HH:mm:ss
	public static final boolean ALWAYS_RESET = false;

	private Service initFile;
	private Service initFileAction;
	private Service initFileLocation;
	private Service initFileName;
	private Service initHDD;
	private Service initHDDState;
	private Service initRoot;
	private Service initScan;
	
	private Service initFK;

	public EntityImpl() throws Exception {
		initFile = Outside.service(this, "gus.y.filedb1.cx.initdb.file");
		initFileAction = Outside.service(this, "gus.y.filedb1.cx.initdb.fileaction");
		initFileLocation = Outside.service(this, "gus.y.filedb1.cx.initdb.filelocation");
		initFileName = Outside.service(this, "gus.y.filedb1.cx.initdb.filename");
		initHDD = Outside.service(this, "gus.y.filedb1.cx.initdb.hdd");
		initHDDState = Outside.service(this, "gus.y.filedb1.cx.initdb.hddstate");
		initRoot = Outside.service(this, "gus.y.filedb1.cx.initdb.root");
		initScan = Outside.service(this, "gus.y.filedb1.cx.initdb.scan");
		
		initFK = Outside.service(this, "gus.y.filedb1.cx.initdb.fk");
	}
	
	public Object g() throws Exception {
		if (ALWAYS_RESET)
			return null;
		return STRUCT_LAST_UPDATE;
	}
	
	public void p(Object obj) throws Exception {
		initFile.p(obj);
		initFileAction.p(obj);
		initFileLocation.p(obj);
		initFileName.p(obj);
		initHDD.p(obj);
		initHDDState.p(obj);
		initRoot.p(obj);
		initScan.p(obj);
		
		initFK.p(obj);
	}
}
