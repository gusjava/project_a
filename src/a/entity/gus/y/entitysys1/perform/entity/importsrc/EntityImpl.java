package a.entity.gus.y.entitysys1.perform.entity.importsrc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251125";}

	private Service validate;
	private Service extractName;
	private Service extractCreationDate;
	private Service generate;
	private Service retrieveJavaFile;

	public EntityImpl() throws Exception
	{
		validate            = Outside.service(this, "gus.x.entity.name.validate");
		extractName         = Outside.service(this, "gus06.java.srccode.extract.entity.name");
		extractCreationDate = Outside.service(this, "gus06.java.srcfile.extract.entity.creationdate");
		generate            = Outside.service(this,"gus06.java.srcdir.generate.fromsrc");
		retrieveJavaFile    = Outside.service(this, "gus06.java.srcdir.retrieve.javafile");
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
		if (!validate.f(name)) return false;
//		if (devId != null && !name.startsWith(devId + ".")) return false;

		File entityFile = (File) retrieveJavaFile.t(new Object[]{dir, "a.entity." + name + ".EntityImpl"});
		String date = entityFile.exists()
			? (String) extractCreationDate.t(entityFile)
			: today();
		src = setCreationDate(src, date);
		
		generate.p(new Object[]{src, dir});
		
		((V) engine).v("entityAdded", name);
		return true;
	}

	private String setCreationDate(String src, String date) {
		int start = src.indexOf("creationDate()");
		if (start < 0) return src;
		int startQuote = src.indexOf('"', start);
		if (startQuote < 0) return src;
		int endQuote = src.indexOf('"', startQuote + 1);
		if (endQuote < 0) return src;
		return src.substring(0, startQuote + 1) + date + src.substring(endQuote);
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
}