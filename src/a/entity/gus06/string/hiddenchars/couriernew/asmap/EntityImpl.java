package a.entity.gus06.string.hiddenchars.couriernew.asmap;

import a.framework.*;
import java.awt.Font;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190316";}
	
	public static Font FONT = new Font("Courier New", Font.PLAIN, 12);


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.hiddenchars.asmap");
	}

	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return perform.f(new Object[]{obj,FONT});
	}
}
