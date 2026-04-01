package a.entity.gus06.file.image.perform.rotate180;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151001";}


	private Service applyTransform;
	private Service op;
	
	public EntityImpl() throws Exception
	{
		applyTransform = Outside.service(this,"gus06.file.image.perform.apply.t");
		op = Outside.service(this,"gus06.awt.renderedimage.transform.rotate180");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		applyTransform.p(new Object[]{file,op});
	}
}
