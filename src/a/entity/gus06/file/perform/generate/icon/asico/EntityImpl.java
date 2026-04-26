package a.entity.gus06.file.perform.generate.icon.asico;

import a.framework.*;
import java.io.File;
import javax.swing.Icon;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160208";}


	private Service fileToIcon;
	private Service writeIco;


	public EntityImpl() throws Exception
	{
		fileToIcon = Outside.service(this,"gus.x.file.icon.os");
		writeIco = Outside.service(this,"gus06.file.write.ico");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Icon icon = (Icon) fileToIcon.t(o[0]);
		writeIco.p(new Object[]{o[1],icon});
	}
}
