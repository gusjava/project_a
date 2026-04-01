package a.entity.gus06.sys.datepicker1.fr.gui.combo.years;

import a.framework.*;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160616";}


	private Service clickNext;
	private Service custWhite;


	private JComboBox combo;

	public EntityImpl() throws Exception
	{
		clickNext = Outside.service(this,"gus06.swing.combobox.cust.clicknext");
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		combo = new JComboBox();
		
		for(int i=1900;i<2050;i++)
		combo.addItem(Integer.valueOf(i));
		
		custWhite.p(combo);
		clickNext.p(combo);
	}
	
	
	public Object i() throws Exception
	{return combo;}
}
