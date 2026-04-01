package a.entity.gus06.input.choose.dialog.field.fromset;

import a.framework.*;
import java.util.Set;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150924";}
	
	public static final String MESSAGE = "Please, choose target field";
	public static final String TITLE = "Field chooser";


	
	public Object t(Object obj) throws Exception
	{return chooseField((Set) obj);}
	
	
	
	
	private String chooseField(Set fields)
	{
		int number = fields.size();
		if(number==0) return null;
		
		ArrayList list = new ArrayList(fields);
		Collections.sort(list);
		
		String[] values = new String[number];
		list.toArray(values);
		
		return (String) JOptionPane.showInputDialog(null,MESSAGE,TITLE,JOptionPane.PLAIN_MESSAGE,null,values,values[0]);
	}
}
