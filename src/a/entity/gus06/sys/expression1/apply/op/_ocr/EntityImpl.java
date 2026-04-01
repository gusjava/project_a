package a.entity.gus06.sys.expression1.apply.op._ocr;

import a.framework.*;
import java.io.File;
import java.awt.Image;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231106";}

	private Service rectToTxt;
	private Service imageToTxt;
	private Service fileToTxt;
	
	public EntityImpl() throws Exception
	{
		rectToTxt = Outside.service(this,"gus06.sys.tesseract1.imagetotext.printscreen");
		imageToTxt = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		fileToTxt = Outside.service(this,"gus06.sys.tesseract1.engine");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Image)
			return imageToTxt.t(obj);
			
		if(obj instanceof File)
			return fileToTxt.t(obj);
		
		if(obj instanceof Rectangle)
			return rectToTxt.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}