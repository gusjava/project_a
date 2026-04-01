package a.entity.gus06.file.image.perform.flip.diagonal;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151002";}


	private Service applyTransform;
	private Service op;
	
	public EntityImpl() throws Exception
	{
		applyTransform = Outside.service(this,"gus06.file.image.perform.apply.t");
		op = Outside.service(this,"gus06.awt.renderedimage.transform.flip.diagonal");
	}


	public void p(Object obj) throws Exception
	{
		File file = (File) obj;
		applyTransform.p(new Object[]{file,op});
	}
}
