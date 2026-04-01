package a.entity.gus06.sys.expression1.apply.op._choosefile_open;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250323";}

	private Service choose;
	private Service chooseByExt;
	private Service toArray;
	
	public EntityImpl() throws Exception
	{
		choose = Outside.service(this,"gus06.file.choose.open.file");
		chooseByExt = Outside.service(this,"gus06.file.choose.save.file.builder.ext");
		toArray = Outside.service(this,"gus06.find.stringarray.len2");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof String[]) return chooseByExt.t(obj);
		if(obj instanceof List) return ((G)chooseByExt.t(toArray.t(obj))).g();
		return choose.g();
	}
}