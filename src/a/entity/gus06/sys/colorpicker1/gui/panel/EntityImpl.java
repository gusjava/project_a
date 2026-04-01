package a.entity.gus06.sys.colorpicker1.gui.panel;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl extends S1 implements Entity, I, G, P {

	public String creationDate() {return "20180226";}

	
	private Service editor;
	private Service picker;
	private Service findColor;
	
	private JPanel panel;
	private Color color;


	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.data.editor.color.editor1");
		picker = Outside.service(this,"gus06.sys.colorpicker1.holder.timed.pixel9");
		findColor = Outside.service(this,"gus06.find.color");
		
		picker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {editColor_fromPicker();}
		});
	}



	public Object i() throws Exception
	{return editor.i();}


	
	public Object g() throws Exception
	{return color;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		color = toColor(obj);
		updateGui();
	}
	
	
	private Color toColor(Object obj) throws Exception
	{return (Color) findColor.t(obj);}
	
	
	
	private void updateGui() throws Exception
	{
		editor.p(color);
		picker.p(color);
	}
	
	
	
	private void editColor_fromPicker()
	{
		try
		{
			color = (Color) picker.g();
			editor.p(color);
			colorEdited();
		}
		catch(Exception e)
		{Outside.err(this,"editColor_fromPicker()",e);}
	}
	
	
	
	
	private void colorEdited()
	{send(this,"colorEdited()");}
}