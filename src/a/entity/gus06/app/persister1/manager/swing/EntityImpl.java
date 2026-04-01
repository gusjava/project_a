package a.entity.gus06.app.persister1.manager.swing;

import a.framework.*;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20150509";}


	private Service persistComboBox;
	private Service persistSpinner;
	private Service persistCheckBox;
	private Service persistPwdField;
	private Service persistTextComponent;
	private Service persistTabbedPane;
	private Service persistFrame;


	public EntityImpl() throws Exception
	{
		persistComboBox = Outside.service(this,"gus06.swing.combobox.persister.index");
		persistSpinner = Outside.service(this,"gus06.swing.spinner.persister.index");
		persistCheckBox = Outside.service(this,"gus06.swing.checkbox.persister.selected");
		persistPwdField = Outside.service(this,"gus06.swing.pwdfield.persister.pwd");
		persistTextComponent = Outside.service(this,"gus06.swing.textcomp.persister.text");
		persistTabbedPane = Outside.service(this,"gus06.swing.tabbedpane.persister.tab");
		persistFrame = Outside.service(this,"gus06.swing.frame.persister.bounds");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		V v = findPersister(obj);
		v.v(key,obj);
	}
	
	
	
	private V findPersister(Object obj) throws Exception
	{
		if(obj instanceof JComboBox) return persistComboBox;
		if(obj instanceof JSpinner) return persistSpinner;
		if(obj instanceof JCheckBox) return persistCheckBox;
		if(obj instanceof JPasswordField) return persistPwdField;
		if(obj instanceof JTextComponent) return persistTextComponent;
		if(obj instanceof JTabbedPane) return persistTabbedPane;
		if(obj instanceof JFrame) return persistFrame;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
