package a.entity.gus06.file.editor.ext.php.custcomp;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141215";}

	
	private Service custComp;


	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.file.editor.ext.txt.custcomp");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;

		custComp.p(comp);
	}
}
