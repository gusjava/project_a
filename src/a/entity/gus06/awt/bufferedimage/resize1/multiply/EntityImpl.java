package a.entity.gus06.awt.bufferedimage.resize1.multiply;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180408";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.bufferedimage.resize");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		BufferedImage image = (BufferedImage) o[0];
		Double k = (Double) o[1];
		
		String percent = (k*100.0)+"%";
		
		Map map = new HashMap();
		map.put("dim",percent);
		
		return perform.t(new Object[]{image,map});
	}
}
