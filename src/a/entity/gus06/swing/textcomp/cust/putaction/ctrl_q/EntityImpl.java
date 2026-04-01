package a.entity.gus06.swing.textcomp.cust.putaction.ctrl_q;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150908";}
	
	public static final String KEY = "ctrl Q";


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.swing.textcomp.cust.putaction");}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		perform.p(new Object[]{o[0],o[1],KEY});
	}
}