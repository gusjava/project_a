package a.entity.gus06.data.perform.cust;

import a.framework.*;
import java.util.Map;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JComponent;
import javax.swing.JCheckBox;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180221";}

	private Service custTextComp;
	private Service custCheckBox;
	private Service custComboBox;
	private Service custLabel;
	private Service custDialog;
	private Service custComp;

	public EntityImpl() throws Exception
	{
		custTextComp = Outside.service(this,"gus06.swing.textcomp.cust3.map1");
		custCheckBox = Outside.service(this,"gus06.swing.checkbox.cust3.map1");
		custComboBox = Outside.service(this,"gus06.swing.combobox.cust3.map1");
		custLabel = Outside.service(this,"gus06.swing.label.cust3.map1");
		custDialog = Outside.service(this,"gus06.swing.dialog.cust3.map1");
		custComp = Outside.service(this,"gus06.swing.comp.cust3.map1");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map map = (Map) o[1];
		
		if(data instanceof JTextComponent) {custTextComp.p(obj);return;}
		if(data instanceof JCheckBox) {custCheckBox.p(obj);return;}
		if(data instanceof JComboBox) {custComboBox.p(obj);return;}
		if(data instanceof JLabel) {custLabel.p(obj);return;}
		if(data instanceof JDialog) {custDialog.p(obj);return;}
		if(data instanceof JComponent) {custComp.p(obj);return;}
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
