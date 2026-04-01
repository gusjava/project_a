package a.entity.gus06.sys.expression1.apply.op._mail_msgtotext;

import a.framework.*;
import javax.mail.Message;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240323";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.mail.retrieve.message.content.text");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Message) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}