package a.entity.gus06.file.image.perform.convert.self1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180307";}


	private Service toBmp;
	private Service toGif;
	private Service toIco;
	private Service toJpg;
	private Service toPng;
	private Service toTiff;

	public EntityImpl() throws Exception
	{
		toBmp = Outside.service(this,"gus06.file.image.perform.convert.tobmp.self1");
		toGif = Outside.service(this,"gus06.file.image.perform.convert.togif.self1");
		toIco = Outside.service(this,"gus06.file.image.perform.convert.toico.self1");
		toJpg = Outside.service(this,"gus06.file.image.perform.convert.tojpg.self1");
		toPng = Outside.service(this,"gus06.file.image.perform.convert.topng.self1");
		toTiff = Outside.service(this,"gus06.file.image.perform.convert.totiff.self1");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String type = ((String) o[1]).toLowerCase();
		
		if(type.equals("bmp")) return toBmp.t(file);
		if(type.equals("gif")) return toGif.t(file);
		if(type.equals("ico")) return toIco.t(file);
		if(type.equals("jpg")) return toJpg.t(file);
		if(type.equals("png")) return toPng.t(file);
		if(type.equals("tiff")) return toTiff.t(file);
		
		throw new Exception("Unsupported type: "+type);
	}
}
