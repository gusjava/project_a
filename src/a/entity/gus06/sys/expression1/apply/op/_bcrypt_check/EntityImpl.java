package a.entity.gus06.sys.expression1.apply.op._bcrypt_check;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180308";}



	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.crypto.hash.bcrypt.check");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new F1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class F1 implements F
	{
		private String plain;
		public F1(String plain) {this.plain = plain;}
		
		public boolean f(Object obj) throws Exception
		{
			String hash = (String) obj;
			return perform.f(new String[]{plain,hash});
		}
	}
}
