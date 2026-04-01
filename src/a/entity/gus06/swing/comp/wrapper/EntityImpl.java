package a.entity.gus06.swing.comp.wrapper;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}


	private Service wrapper_pwdField;
	private Service wrapper_textComp;
	private Service wrapper_combobox;
	private Service wrapper_button;


	public EntityImpl() throws Exception
	{
		wrapper_pwdField = Outside.service(this,"gus06.swing.pwdfield.wrapper");
		wrapper_textComp = Outside.service(this,"gus06.swing.textcomp.wrapper");
		wrapper_combobox = Outside.service(this,"gus06.swing.combobox.wrapper");
		wrapper_button = Outside.service(this,"gus06.swing.button.wrapper");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof JPasswordField)
			return wrapper_pwdField.t(obj);
		if(obj instanceof JTextComponent)
			return wrapper_textComp.t(obj);
		if(obj instanceof JComboBox)
			return wrapper_combobox.t(obj);
		if(obj instanceof AbstractButton)
			return wrapper_button.t(obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
