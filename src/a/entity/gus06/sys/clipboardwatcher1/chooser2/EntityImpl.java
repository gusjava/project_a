package a.entity.gus06.sys.clipboardwatcher1.chooser2;

import a.framework.*;
import java.util.List;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200327";}
	
	public static final String TITLE = "Clipboard history";
	public static final String MESSAGE = "Please choose an input:";


	private Service history;
	private Service clipboard;
	private Service chooser;


	public EntityImpl() throws Exception
	{
		history = Outside.service(this,"gus06.sys.clipboardwatcher1.history");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		chooser = Outside.service(this,"gus06.list.string.chooser.dialog");
	}
	
	
	public Object g() throws Exception
	{
		List list = (List) history.g();
		if(list==null || list.isEmpty()) return null;
		
		String v = (String) chooser.t(list);
		if(v==null) return null;
		
		clipboard.p(v);
		return v;
	}
}
