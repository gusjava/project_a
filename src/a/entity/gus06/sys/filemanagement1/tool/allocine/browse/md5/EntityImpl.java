package a.entity.gus06.sys.filemanagement1.tool.allocine.browse.md5;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210306";}


	private Service perform;
	private Service md5ToCode;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.browse.code");
		md5ToCode = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.md5.find.code");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		
		String code = (String) md5ToCode.t(new Object[]{engine,md5});
		perform.p(new Object[]{engine,code});
	}
}