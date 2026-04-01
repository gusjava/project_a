package a.entity.gus06.sys.autocomplete1.editor1.icon;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201206";}


	private Service dialog;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.data.editor.string.iconkey.dialog");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getSelectedText();
		if(text==null) text = "";
		
		String iconKey = chooseIcon(text);
		if(iconKey!=null) replace(comp,iconKey);
	}
	
	
	
	private void replace(JTextComponent comp, String iconKey) throws Exception
	{
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		String text = comp.getText();
		
		if(text.length()==end)
			comp.replaceSelection(iconKey+"#\n");
		else if(text.charAt(end)!='#')
			comp.replaceSelection(iconKey+"#");
		else comp.replaceSelection(iconKey);
			
		comp.select(start,start+iconKey.length());
	}
	
	private String chooseIcon(String key) throws Exception
	{return (String) dialog.t(key);}
}