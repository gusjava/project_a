package a.entity.gus06.sys.expression1.apply.op._jcombobox;

import a.framework.*;
import java.util.Map;
import java.util.List;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180415";}

	
	
	private Service custComp;
	
	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.swing.combobox.cust3.map1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[]) return build((Object[]) obj);
		if(obj instanceof List) return build((List) obj);
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private JComboBox build(Map map) throws Exception
	{
		JComboBox combo = new JComboBox();
		custComp.p(new Object[]{combo,map});
		return combo;
	}
	
	private JComboBox build(Object[] data) throws Exception
	{
		JComboBox combo = new JComboBox();
		for(Object element : data) 
		combo.addItem(element);
		return combo;
	}
	
	private JComboBox build(List data) throws Exception
	{
		JComboBox combo = new JComboBox();
		for(Object element : data) 
		combo.addItem(element);
		return combo;
	}
}
