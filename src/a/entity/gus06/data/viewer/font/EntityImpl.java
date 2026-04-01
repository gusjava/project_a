package a.entity.gus06.data.viewer.font;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20190509";}


	private Service formPanel;


	private Font data;
	private Font defaultFont;
	
	private JLabel labelSample;
	private JLabel labelName;
	private JLabel labelStyle;
	private JLabel labelSize;

	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"gus06.swing.panel.formpanel");
		
		labelSample = label();
		labelName = label();
		labelStyle = label();
		labelSize = label();
		
		formPanel.v("Sample",labelSample);
		formPanel.v("Font name",labelName);
		formPanel.v("Font style",labelStyle);
		formPanel.v("Font size",labelSize);

		defaultFont = labelSample.getFont();
	}
	
	private JLabel label()
	{
		JLabel label = new JLabel(" ");
		label.setOpaque(true);
		return label;
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Font) obj;
		if(data!=null)
		{
			labelSample.setFont(data.deriveFont((float)12));
			labelSample.setText("bonjour - \u3053\u3093\u306b\u3061\u306f");
			
			labelName.setText(data.getName());
			labelStyle.setText(""+data.getStyle());
			labelSize.setText(""+data.getSize());
		}
		else
		{
			labelSample.setFont(defaultFont);
			labelSample.setText("null");
			
			labelName.setText("null");
			labelStyle.setText("null");
			labelSize.setText("null");
		}
	}
}
