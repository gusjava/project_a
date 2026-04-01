package a.entity.gus06.file.runtask.decompress;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150630";}


	private Service runUnzip;
	private Service runUnrar;
	
	private Service isZip;
	private Service isRar;

	public EntityImpl() throws Exception
	{
		runUnzip = Outside.service(this,"gus06.file.runtask.zip.unzip");
		runUnrar = Outside.service(this,"gus06.file.runtask.rar.unrar");
		
		isZip = Outside.service(this,"gus06.file.filter.ext.istype.archive.zip");
		isRar = Outside.service(this,"gus06.file.filter.ext.istype.archive.rar");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		P engine = findEngine(file);
		
		if(engine!=null) engine.p(obj);
	}
	
	
	private P findEngine(File f) throws Exception
	{
		if(isZip.f(f)) return runUnzip;
		if(isRar.f(f)) return runUnrar;
		return null;
	}
}
