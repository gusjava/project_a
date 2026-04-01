package a.entity.gus06.appli.gusclient1.init.sysouterr;

import a.framework.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140730";}

	private Service outManager;
	private Service errManager;
	private Service supToPrintStream;
	private Service alertManager;

	
	public EntityImpl() throws Exception
	{
		outManager = Outside.service(this,"gus06.system.out.manager");
		errManager = Outside.service(this,"gus06.system.err.manager");
		supToPrintStream = Outside.service(this,"gus06.io.printstream.supporttoprintstream");
		alertManager = Outside.service(this,"gus06.appli.gusclient1.alert.manager");
		
		S1 outSup = new S1();
		outManager.p(supToPrintStream.t(outSup));
		alertManager.v("sysout",outSup);
		
		S1 errSup = new S1();
		errManager.p(supToPrintStream.t(errSup));
		alertManager.v("syserr",errSup);
	}
}
