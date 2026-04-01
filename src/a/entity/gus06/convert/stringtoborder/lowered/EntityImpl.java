package a.entity.gus06.convert.stringtoborder.lowered;

import a.framework.*;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}

	public Object t(Object obj) throws Exception
	{
		if(obj!=null) throw new Exception("Invalid rule for loweredbevelborder: "+obj);
		return BorderFactory.createLoweredBevelBorder();
	}
}
