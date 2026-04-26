package a.entity.gus06.sys.clipboardwatcher1.chooser;

import a.framework.*;
import java.util.List;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200326";}
	
	public static final String TITLE = "Clipboard history";
	public static final String MESSAGE = "Please choose an input:";


	private Service history;
	private Service clipboard;


	public EntityImpl() throws Exception
	{
		history = Outside.service(this,"gus06.sys.clipboardwatcher1.history");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public Object g() throws Exception
	{
		List list = (List) history.g();
		if(list==null || list.isEmpty()) return null;
		
		int number = list.size();
		
		String[] values = new String[number];
		for(int i=0;i<number;i++) values[i] = (String) list.get(number-1-i);
		String selected = values[0];
		
		String v = (String) JOptionPane.showInputDialog(null,MESSAGE,TITLE,JOptionPane.PLAIN_MESSAGE,null,values,selected);
		if(v==null) return null;
		
		clipboard.p(v);
		return v;
	}
}
