package a.entity.gus06.file.editor.default0;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140723";}

	
	private Service shiftPanel;
	private Service editorTxt;
	private Service editorProp;
	private Service editorImage;
	
	private JPanel panel;
	private JButton buttonTxt;
	private JButton buttonProp;
	private JButton buttonImage;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		editorTxt = Outside.service(this,"*gus06.file.editor.ext.txt");
		editorProp = Outside.service(this,"*gus06.file.editor.ext.properties");
		editorImage = Outside.service(this,"*gus06.file.editor.ext.image");
		
		buttonTxt = new JButton("Txt");
		buttonTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {initTxt();}
		});
		buttonProp = new JButton("Prop");
		buttonProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {initProp();}
		});
		buttonImage = new JButton("Image");
		buttonImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {initImage();}
		});
		
		JPanel p = new JPanel(new GridLayout(1,3));
		p.add(buttonTxt);
		p.add(buttonProp);
		p.add(buttonImage);
				
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
		panel.add(p,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		editorTxt.p(null);
		editorProp.p(null);
		shiftPanel.p(null);
	}
	
	
	
	private void initTxt()
	{
		try
		{
			editorTxt.p(file);
			shiftPanel.p(editorTxt.i());
		}
		catch(Exception e)
		{Outside.err(this,"initTxt()",e);}
	}
	
	
	private void initProp()
	{
		try
		{
			editorProp.p(file);
			shiftPanel.p(editorProp.i());
		}
		catch(Exception e)
		{Outside.err(this,"initProp()",e);}
	}
	
	
	private void initImage()
	{
		try
		{
			editorImage.p(file);
			shiftPanel.p(editorImage.i());
		}
		catch(Exception e)
		{Outside.err(this,"initImage()",e);}
	}
}