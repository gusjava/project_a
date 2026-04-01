package a.entity.gus06.appli.mosaique.engine.trigger;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141115";}


	private Service engine;
	private Service readFile;
	private Service getFile;
	private Service getTrans;
	private Service getNumber;


	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.appli.mosaique.engine");
		readFile = Outside.service(this,"gus06.file.read.image.imageio");
		getFile = Outside.service(this,"gus06.appli.mosaique.data.file");
		getTrans = Outside.service(this,"gus06.appli.mosaique.data.trans");
		getNumber = Outside.service(this,"gus06.appli.mosaique.parameter.number");
	}
	
	
	
	public void e() throws Exception
	{
		if(engine.f(null)) return;
		
		File file = (File) getFile.g();
		if(file==null || !file.isFile()) return;
		
		T trans = (T) getTrans.g();
		if(trans==null) return;
		
		String number = (String) getNumber.g();
		if(number==null) return;
		
		
		BufferedImage input = (BufferedImage) readFile.t(file);
		
		engine.v("input",input);
		engine.v("trans",trans);
		engine.v("number",number);
		
		engine.e();
	}
}
