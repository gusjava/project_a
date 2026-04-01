package a.entity.gus06.sys.javaprojectviewer1.gui2.jars.list.updater;

import a.framework.*;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import java.io.File;
import java.util.Comparator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170223";}




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
			List jars = (List) data;
			Vector vec = new Vector(jars);
			Collections.sort(vec,new Comparator(){
				public int compare(Object o1, Object o2)
				{
					Object[] t1 = (Object[]) o1;
					Object[] t2 = (Object[]) o2;
					
					File f1 = (File) t1[0];
					File f2 = (File) t2[0];
					
					return f1.getName().compareTo(f2.getName());
				}
			});
			return vec;
		}
		catch(Exception e)
		{Outside.err(this,"toVector(Object)",e);}
		return new Vector();
	}
}
