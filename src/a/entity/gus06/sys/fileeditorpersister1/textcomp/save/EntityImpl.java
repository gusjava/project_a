package a.entity.gus06.sys.fileeditorpersister1.textcomp.save;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20200428";}
	
	public static final String KEY_CARET = "caret";


	private Service engine;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"gus06.sys.fileeditorpersister1.textcomp");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		if(comp.getText().equals("")) return;
		
		int caret = comp.getCaretPosition();
		
		Map map = new HashMap();
		map.put(KEY_CARET,""+caret);
		
		engine.v(key,map);
	}
}
