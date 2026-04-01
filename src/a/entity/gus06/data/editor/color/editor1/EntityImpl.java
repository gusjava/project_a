package a.entity.gus06.data.editor.color.editor1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EntityImpl extends S1 implements Entity, I, G, P, ChangeListener {

	public String creationDate() {return "20160427";}


	private Pattern p_int = Pattern.compile("[0-9]+");
	private Pattern p_int2 = Pattern.compile("-?[0-9]+");
	private Pattern p_html = Pattern.compile("#[1-9ABCDEF]{6}");
	
	
	private Service form;
	private Service findColor;

	private JColorChooser chooser;
	private JPanel panel;
	private JTextField field_newColor;
	private JTextField field_htmlCode;
	private JTextField field_rgbValue;
	
	private boolean colorChooserEdited = false;
	private boolean field_newColorEdited = false;
	private boolean field_htmlCodeEdited = false;
	private boolean field_rgbValueEdited = false;

	
	
	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"gus06.swing.panel.formpanel");
		findColor = Outside.service(this,"gus06.find.color");
		
		chooser = new JColorChooser();
		chooser.getSelectionModel().addChangeListener(this);
		
		field_newColor = new JTextField();
		field_newColor.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {if(!field_newColorEdited) field_newColorChanged();}
			public void insertUpdate(DocumentEvent e) {if(!field_newColorEdited) field_newColorChanged();}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		field_htmlCode = new JTextField();
		field_htmlCode.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {if(!field_htmlCodeEdited) field_htmlCodeChanged();}
			public void insertUpdate(DocumentEvent e) {if(!field_htmlCodeEdited) field_htmlCodeChanged();}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		field_rgbValue = new JTextField();
		field_rgbValue.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {if(!field_rgbValueEdited) field_rgbValueChanged();}
			public void insertUpdate(DocumentEvent e) {if(!field_rgbValueEdited) field_rgbValueChanged();}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		form.v("new Color(...)", field_newColor);
		form.v("HTML Color code", field_htmlCode);
		form.v("RGB Value", field_rgbValue);
		
		panel = new JPanel(new BorderLayout());
		panel.add(chooser,BorderLayout.CENTER);
		panel.add((JComponent) form.i(),BorderLayout.SOUTH);
	}


	

	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return chooser.getColor();}	
	
	
	
	
	private void colorChanged()
	{send(this,"colorChanged()");}
	
	
	
	
	
	
	public void stateChanged(ChangeEvent e)
	{
		if(colorChooserEdited) return;
		colorChooserEdited = true;
		Color c = chooser.getColor();
		
		if(!field_newColorEdited)
		{
			String text_newColor = "new Color("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")";
			field_newColor.setText(text_newColor);
		}
		if(!field_htmlCodeEdited)
		{
			String text_htmlCode = colorToHtmlCode(c);
			field_htmlCode.setText(text_htmlCode);
		}
		if(!field_rgbValueEdited)
		{
			String text_rgb = ""+c.getRGB();
			field_rgbValue.setText(text_rgb);
		}
		colorChooserEdited = false;
		colorChanged();
	}
	
	
	
	
	
	
	private void field_newColorChanged()
	{
		try
		{
			field_newColorEdited = true;
			String text = field_newColor.getText();
			Color c = (Color) findColor.t(text);
			setColor(c);
			field_newColorEdited = false;
		}
		catch(Exception e)
		{Outside.err(this,"field_newColorChanged()",e);}
	}
	
	
	private void field_htmlCodeChanged()
	{
		field_htmlCodeEdited = true;
		String text = field_htmlCode.getText().toUpperCase();
		if(p_html.matcher(text).matches())
		{
			Color c = colorFromHtmlCode(text);
			setColor(c);
		}
		field_htmlCodeEdited = false;
	}
	
	
	private void field_rgbValueChanged()
	{
		field_rgbValueEdited = true;
		String text = field_rgbValue.getText();
		if(p_int2.matcher(text).matches())
		{
			Color c = new Color(Integer.parseInt(text));
			setColor(c);
		}
		field_rgbValueEdited = false;
	}
	
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Color c = (Color) findColor.t(obj);
		setColor(c);
	}
	
	
	
	private void setColor(Color c)
	{
		if(c==null) return;
		chooser.setColor(c);
		colorChanged();
	}
	
	
	private Color colorFromHtmlCode(String code)
	{
		int r = Integer.parseInt(code.substring(1,3),16);
		int g = Integer.parseInt(code.substring(3,5),16);
		int b = Integer.parseInt(code.substring(5,7),16);
		return new Color(r,g,b);
	}
	
	private String colorToHtmlCode(Color c)
	{
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return "#"+toHex(r)+toHex(g)+toHex(b);
	}
	
	private String toHex(int value)
	{
		String s = Integer.toHexString(value).toUpperCase();
		if(s.length()==1) return "0"+s;
		return s;
	}
}
