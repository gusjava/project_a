package a.entity.gus06.file.write.wav;

import a.framework.*;
import org.jcodec.api.awt.AWTSequenceEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250913";}

	private Service fromMp3;
	private Service isMp3;
	
	public EntityImpl() throws Exception
	{
		fromMp3 = Outside.service(this,"gus06.file.mp3.perform.convert.towav");
		isMp3 = Outside.service(this,"gus06.file.filter.ext.istype.audio.mp3");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object data = o[1];
		
		if(data instanceof File)
		{
			File inputFile = (File) data;
			
			if(isMp3.f(data))
			{fromMp3.p(new File[]{inputFile, file});return;}
		}
		throw new Exception("Unsupported data type: "+data.getClass().getName());
	}
}