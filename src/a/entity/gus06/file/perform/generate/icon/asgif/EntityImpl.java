package a.entity.gus06.file.perform.generate.icon.asgif;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150620";}


	private Service fileToIcon;
	private Service writeGif;


	public EntityImpl() throws Exception
	{
		fileToIcon = Outside.service(this,"gus06.file.icon.os");
		writeGif = Outside.service(this,"gus06.file.write.image.gif");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Icon icon = (Icon) fileToIcon.t(o[0]);
		writeGif.p(new Object[]{o[1],icon});
	}
}
