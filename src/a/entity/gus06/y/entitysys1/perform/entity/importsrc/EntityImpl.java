package a.entity.gus06.y.entitysys1.perform.entity.importsrc;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251125";}

	private Service updateCreationDate;
	private Service validate;
	private Service extractName;
	private Service generate;

	public EntityImpl() throws Exception
	{
		updateCreationDate = Outside.service(this,"gus06.x.entity.src.creationdate.updatenow");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		extractName = Outside.service(this, "gus06.java.srccode.extract.entity.name");
		generate = Outside.service(this,"gus06.java.srcdir.generate.fromsrc");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String src = (String) o[1];
		
		src = src.trim().replace("\r\n","\n").replace("\r","\n");
		
		File dir = (File) ((R) engine).r("srcDir");
		String devId = (String) ((R) engine).r("devId");

		String name = (String) extractName.t(src);
		if (!validate.f(name)) return false;
		if (devId != null && !name.startsWith(devId + ".")) return false;

		src = (String) updateCreationDate.t(src);
		
		generate.p(new Object[]{src, dir});
		
		((V) engine).v("entityAdded", name);
		return true;
	}
}