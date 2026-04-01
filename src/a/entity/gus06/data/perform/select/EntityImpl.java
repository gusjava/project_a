package a.entity.gus06.data.perform.select;

import a.framework.*;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180416";}
	
	
	public EntityImpl() throws Exception
	{
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		Object value = o[1];
		
		if(input instanceof JComboBox)
		{
			JComboBox c = (JComboBox) input;
			c.setSelectedItem(value);
			return;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
}
