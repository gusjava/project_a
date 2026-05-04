package a.entity.gus06.data.editor.string.entityname.dialog;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220909";}

	public static final String DEFAULT_TITLE = "Entity name chooser";


	private Service editor;
	private Service dialog;
	private Service onEscape;
	
	private JComponent editorComp;
	private JTextField editorField;
	private JLabel label;
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.data.editor.string.entityname");
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		onEscape = Outside.service(this,"gus.x.swing.comp.cust3.execute.escape");
		
		editorComp = (JComponent) editor.i();
		editorField = (JTextField) editor.r("field");
		
		label = new JLabel(" ");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(editorComp,BorderLayout.CENTER);
		
		onEscape.p(new Object[]{editorField,(E) this::onEscape});
		editorField.addActionListener(evt->{onEnter();});
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String[])
		{
			String[] o = (String[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			return perform(o[0],o[1]);
		}
		
		if(obj instanceof String)
		{
			return perform(DEFAULT_TITLE,(String) obj);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String perform(String title, String text) throws Exception
	{
		label.setText(title);
		editor.p(text);
		boolean edited = dialog.f(panel);
		return edited ? (String) editor.g() : text;
	}
	
	private void onEscape()
	{
		try
		{
			String text = editorField.getText();
			if(!text.equals("")) {editorField.setText("");return;}
			
			dialog.v("do","cancel");
		}
		catch(Exception e)
		{Outside.err(this,"onEscape()",e);}
	}
	
	
	private void onEnter()
	{
		try
		{
			dialog.v("do","ok");
		}
		catch(Exception e)
		{Outside.err(this,"onEnter()",e);}
	}
}