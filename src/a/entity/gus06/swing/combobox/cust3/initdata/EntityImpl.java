package a.entity.gus06.swing.combobox.cust3.initdata;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.List;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180415";}


	private Service buildFromString;
	
	public EntityImpl() throws Exception
	{
		buildFromString = Outside.service(this,"gus06.list.build.from.string1");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComboBox combo = (JComboBox) o[0];
		Object data = o[1];
		
		if(data instanceof Object[])
		{
			Object[] array = (Object[]) data;
			for(Object element : array) combo.addItem(element);
		}
		else if(data instanceof List)
		{
			List list = (List) data;
			for(Object element : list) combo.addItem(element);
		}
		else if(data instanceof String)
		{
			List list = (List) buildFromString.t(data);
			for(Object element : list) combo.addItem(element);
		}
		else throw new Exception("Invalid data type: "+data.getClass().getName());
	}

}