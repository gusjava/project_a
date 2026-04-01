package a.entity.gus06.swing.frame.cust2.bounds;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140912";}


	private Service toIntArray;

	public EntityImpl() throws Exception
	{toIntArray = Outside.service(this,"gus06.convert.stringtointarray");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JFrame) obj);}
	
	
	private void perform(String info, JFrame frame) throws Exception
	{
		int[] a = (int[]) toIntArray.t(info);
		if(a.length!=4) throw new Exception("Invalid info for frame bounds: "+info);
		
		frame.setBounds(a[0],a[1],a[2],a[3]);
	}
}
