package a.entity.gus06.sys.expression1.apply.op._selection;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;
import javax.swing.JList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170224";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof JTextComponent)
			return ((JTextComponent) obj).getSelectedText();
		if(obj instanceof JComboBox)
			return ((JComboBox) obj).getSelectedItem();
		if(obj instanceof JList)
			return ((JList) obj).getSelectedValue();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
