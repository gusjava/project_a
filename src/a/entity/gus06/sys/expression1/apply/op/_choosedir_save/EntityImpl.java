package a.entity.gus06.sys.expression1.apply.op._choosedir_save;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250323";}

	private Service choose;
	
	public EntityImpl() throws Exception
	{
		choose = Outside.service(this,"gus06.file.choose.save.dir");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return choose.g();
	}
}