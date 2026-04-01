package a.entity.gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180203";}
	
	public static final String TITLE = "Operator Chooser";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	
	private Service listChooser;
	private Service annexe;
	private Service findNames;
	
	private List names;


	public EntityImpl() throws Exception
	{
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		annexe = Outside.service(this,"*gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser.annexe");
		findNames = Outside.service(this,"gus06.swing.textcomp.cust.action.f2.gusscript.autocomplete.op.perform.chooser.names");
		
		names = (List) findNames.g();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String search = (String) obj;
		
		listChooser.v("title",TITLE);
		listChooser.v("width",""+WIDTH);
		listChooser.v("height",""+HEIGHT);
		listChooser.v("annexe",annexe);
		listChooser.v("search",search);
		listChooser.v("persistKey",getClass().getName());
		
		return listChooser.t(names);
	}
}
