package a.entity.gus06.file.image.perform.convert.topng;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151023";}


	private Service read;
	private Service write;
	private Service isSvg;
	private Service svgToPng;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.image.generic");
		write = Outside.service(this,"gus06.file.write.image.png");
		isSvg = Outside.service(this,"gus06.file.filter.ext.istype.text.svg");
		svgToPng = Outside.service(this,"gus06.file.svg.convert.topng");
	}


	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input = o[0];
		File output = o[1];
		
		if(isSvg.f(input))
		{
			svgToPng.p(obj);
			return;
		}
		
		Object data = read.t(input);
		write.p(new Object[]{output,data});
	}
}