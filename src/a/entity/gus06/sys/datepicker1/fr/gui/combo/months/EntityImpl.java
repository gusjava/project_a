package a.entity.gus06.sys.datepicker1.fr.gui.combo.months;

import a.framework.*;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160616";}


	private Service clickNext;
	private Service custWhite;
	private Service months;


	private JComboBox combo;

	public EntityImpl() throws Exception
	{
		clickNext = Outside.service(this,"gus06.swing.combobox.cust.clicknext");
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		months = Outside.service(this,"gus06.data.time.months.name_fr");
		
		String[] data = (String[]) months.g();
		combo = new JComboBox(data);
		
		custWhite.p(combo);
		clickNext.p(combo);
	}
	
	
	public Object i() throws Exception
	{return combo;}
}
