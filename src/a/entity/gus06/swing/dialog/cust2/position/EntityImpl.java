package a.entity.gus06.swing.dialog.cust2.position;

import a.framework.*;
import javax.swing.JDialog;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160605";}


	private Service toIntArray;

	public EntityImpl() throws Exception
	{toIntArray = Outside.service(this,"gus06.convert.stringtointarray");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JDialog) obj);}
	
	
	private void perform(String info, JDialog dialog) throws Exception
	{
		int[] a = (int[]) toIntArray.t(info);
		if(a.length!=2) throw new Exception("Invalid info for dialog position: "+info);
		
		dialog.setLocation(a[0],a[1]);
	}
}
