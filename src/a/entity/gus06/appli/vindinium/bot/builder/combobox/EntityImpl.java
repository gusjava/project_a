package a.entity.gus06.appli.vindinium.bot.builder.combobox;

import java.util.Collections;
import java.util.Set;
import java.util.Vector;
import a.framework.*;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170923";}

	private Service whiteCombo;
	private Service botBuilder;
	private Vector vec;

	public EntityImpl() throws Exception
	{
		whiteCombo = Outside.service(this,"gus06.swing.combobox.cust.white");
		botBuilder = Outside.service(this,"gus06.appli.vindinium.bot.builder");
		
		Set names = (Set) botBuilder.g();
		vec = new Vector(names);
		Collections.sort(vec);
	}

	public Object g() throws Exception
	{
		JComboBox combo = new JComboBox(vec);
		whiteCombo.p(combo);
		return combo;
	}
}
