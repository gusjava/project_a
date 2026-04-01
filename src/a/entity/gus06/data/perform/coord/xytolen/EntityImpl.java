package a.entity.gus06.data.perform.coord.xytolen;

import a.framework.*;
import java.util.List;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}


	private Service performArray2;
	private Service performString;
	private Service performComp;

	public EntityImpl() throws Exception
	{
		performArray2 = Outside.service(this,"gus06.array.d2.objectarray.coord.xytolen");
		performString = Outside.service(this,"gus06.string.coord.xytolen");
		performComp = Outside.service(this,"gus06.swing.textcomp.text.coord.xytolen");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		
		if(data instanceof Object[][])		return performArray2.t(obj);
		if(data instanceof String)		return performString.t(obj);
		if(data instanceof JTextComponent)	return performComp.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
