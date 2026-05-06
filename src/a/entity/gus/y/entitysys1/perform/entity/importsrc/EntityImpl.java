package a.entity.gus.y.entitysys1.perform.entity.importsrc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20260505";}

	private Service hasRights;
	private Service validate;
	private Service extractName;
	private Service extractCreationDate;
	private Service generate;
	private Service findEntityFile;
	private Service updateCreationDate;

	public EntityImpl() throws Exception
	{
		hasRights = Outside.service(this,"gus.x.entity.hasrights");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		extractName = Outside.service(this, "gus06.java.srccode.extract.entity.name");
		extractCreationDate = Outside.service(this, "gus06.java.srcfile.extract.entity.creationdate");
		generate = Outside.service(this,"gus06.java.srcdir.generate.fromsrc");
		findEntityFile = Outside.service(this,"gus.x.entity.src.find.entityfile");
		updateCreationDate = Outside.service(this,"gus.x.entity.src.creationdate.update");
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
		
		File dir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");

		String name = (String) extractName.t(src);
		
		if(!validate.f(name)) return false;
		if(!hasRights.f(new Object[]{devId, name})) return false;

		File entityFile = (File) findEntityFile.t(new Object[]{dir, name});
		
		String date = entityFile.exists()
			? (String) extractCreationDate.t(entityFile)
			: today();
		
		src = (String) updateCreationDate.t(new Object[]{src, date});
		generate.p(new Object[]{src, dir});
		
		((V) engine).v("entityAdded", name);
		return true;
	}
	
	private String today()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
}
