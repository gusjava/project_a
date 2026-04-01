package a.entity.gus06.find.emptyborder;

import a.framework.*;
import javax.swing.border.Border;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161127";}


	private Service stringToBorder;
	private Service intArrayToBorder;

	public EntityImpl() throws Exception
	{
		stringToBorder = Outside.service(this,"gus06.convert.stringtoborder.empty");
		intArrayToBorder = Outside.service(this,"gus06.convert.intarraytoborder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof EmptyBorder) return obj;
		
		if(obj instanceof Integer) return integerToBorder((Integer) obj);
		if(obj instanceof String) return stringToBorder.t(obj);
		if(obj instanceof int[]) return intArrayToBorder.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Border integerToBorder(Integer n)
	{
		int v = n.intValue();
		return BorderFactory.createEmptyBorder(v,v,v,v);
	}
}
