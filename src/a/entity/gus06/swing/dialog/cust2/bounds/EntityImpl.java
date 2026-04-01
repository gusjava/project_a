package a.entity.gus06.swing.dialog.cust2.bounds;

import a.framework.*;
import javax.swing.JDialog;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20160605";}


	private Service toIntArray;

	public EntityImpl() throws Exception
	{toIntArray = Outside.service(this,"gus06.find.intarray");}
	
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JDialog) obj);}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JDialog dialog = (JDialog) o[0];
		int[] info1 = (int[]) toIntArray.t(o[1]);
		perform(info1,dialog);
	}
	
	
	
	private void perform(String info, JDialog dialog) throws Exception
	{
		int[] info1 = (int[]) toIntArray.t(info);
		perform(info1,dialog);
	}
	
	
	private void perform(int[] info, JDialog dialog) throws Exception
	{
		if(info.length!=4) throw new Exception("Invalid info for dialog bounds: "+info);
		dialog.setBounds(info[0],info[1],info[2],info[3]);
	}
}
