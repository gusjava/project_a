package a.entity.gus06.file.write.image.imageio.jpg.quality03;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160529";}
	
	public final static String QUALITY = "0.3";


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.write.image.imageio.jpg.quality");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		perform.p(new Object[]{o[0],o[1],QUALITY});
	}
}
