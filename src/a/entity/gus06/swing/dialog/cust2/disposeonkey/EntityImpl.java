package a.entity.gus06.swing.dialog.cust2.disposeonkey;

import a.framework.*;
import javax.swing.JDialog;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20190625";}


	private Service onKey;

	public EntityImpl() throws Exception
	{onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");}
	
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JDialog) obj);}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JDialog dialog = (JDialog) o[0];
		String key = (String) o[1];
		perform(key,dialog);
	}
	
	
	
	private void perform(String key, final JDialog dialog) throws Exception
	{
		onKey.p(new Object[]{dialog.getContentPane(),key,new E(){
			public void e() throws Exception {dialog.dispose();}
		}});
	}
}
