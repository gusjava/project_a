package a.entity.gus06.data.perform.setvalue;

import a.framework.*;
import javax.swing.JProgressBar;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250713";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		setValue(o[0],o[1]);
	}
	
	private void setValue(Object data, Object value) throws Exception
	{
		if(data instanceof JProgressBar)
		{setValue((JProgressBar) data,value);return;}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	private void setValue(JProgressBar bar, Object value)
	{
		if(value!=null)
		bar.setValue((Integer) value);
	}
}
