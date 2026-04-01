package a.entity.gus06.sys.javaprojectviewer1.gui1.class1.list.updater;

import java.util.Collections;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170220";}




	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		final Object data = o[0];
		final JList list = (JList) o[1];
		final JLabel label = (JLabel) o[2];
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				Vector vector = toVector(data);
				label.setText(" "+vector.size()+" ");
				list.setListData(vector);
			}
		});
	}
	
	
	
	
	private Vector toVector(Object data)
	{
		try
		{
			if(data==null) return new Vector();
			Map map = (Map) data;
			
			List keys = new ArrayList(map.keySet());
			Collections.sort(keys);
			
			Vector vec = new Vector();
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				Object value = map.get(key);
				vec.add(new Object[]{key,value});
			}
			return vec;
		}
		catch(Exception e)
		{Outside.err(this,"toVector(Object)",e);}
		return new Vector();
	}
}
