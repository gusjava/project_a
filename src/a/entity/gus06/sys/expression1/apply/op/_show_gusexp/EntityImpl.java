package a.entity.gus06.sys.expression1.apply.op._show_gusexp;

import a.framework.*;
import javax.swing.tree.TreeModel;
import javax.swing.JTree;
import java.util.Map;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221012";}


	private Service perform;
	private Service prepare;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show.gusexp");
		prepare = Outside.service(this,"gus06.sys.parser3.prepare");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj instanceof File)		return new E1(prepare.t(readFile.t(obj)));
		if(obj instanceof String)	return new E1(prepare.t(obj));
		if(obj instanceof List)		return new E1(obj);
		if(obj instanceof Map)		return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class E1 implements E
	{
		private Object o;
		public E1(Object o) {this.o = o;}
		
		public void e() throws Exception
		{perform.p(o);}
	}
}