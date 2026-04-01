package a.entity.gus06.sys.autocomplete1.help;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220603";}

	public static final String ENTITY = "gus.swing.textcomp.cust.action.f1.autocomplete";
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.execute.help");
	}

	
	public void p(Object obj) throws Exception
	{
		perform.p(ENTITY);
	}
	
	
}