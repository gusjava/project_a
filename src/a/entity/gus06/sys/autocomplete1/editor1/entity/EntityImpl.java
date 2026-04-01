package a.entity.gus06.sys.autocomplete1.editor1.entity;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}


	private Service dialog;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.data.editor.string.entityname.dialog");
	}
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getSelectedText();
		if(text==null) text = "";
		
		String entityName = chooseEntityName(text);
		if(entityName!=null) replace(comp,entityName);
	}
	
	private void replace(JTextComponent comp, String entityName) throws Exception
	{
		int start = comp.getSelectionStart();
		String text = comp.getText();
		
		comp.replaceSelection(entityName);
		comp.select(start,start+entityName.length());
	}
	
	private String chooseEntityName(String key) throws Exception
	{return (String) dialog.t(key);}
}