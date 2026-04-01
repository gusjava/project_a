package a.entity.gus06.appli.mosaique.parameter.number;

import a.framework.*;
import javax.swing.JComboBox;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20141123";}

	public static final String DEFAULTVALUE = "20";
	public static final String[] NUMBER_VALUES = new String[]{"5","10","15","20","30","40","50","80"};
	public static final String KEY = "number";



	private Service optionManager;
	private Service optionRegister;
	private Service comboWhite;

	private JComboBox combo;
	
	
	
	public EntityImpl() throws Exception
	{
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		optionRegister = Outside.service(this,"gus06.sys.option.comp.register");
		comboWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		combo = new JComboBox(NUMBER_VALUES);
		comboWhite.p(combo);
		
		optionRegister.v(KEY,combo);
	}
	
	
	public Object i() throws Exception
	{return combo;}
	
	
	
	public Object g() throws Exception
	{
		String value = (String) optionManager.r(KEY);
		return value!=null?value:DEFAULTVALUE;
	}
}
