package a.entity.gus06.awt.bufferedimage.resize1.half;

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
		BufferedImage image = (BufferedImage) obj;
		
		Map map = new HashMap();
		map.put("dim","50%");
		
		return perform.t(new Object[]{image,map});
	}
}
