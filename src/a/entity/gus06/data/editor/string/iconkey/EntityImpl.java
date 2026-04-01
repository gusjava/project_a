package a.entity.gus06.data.editor.string.iconkey;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, G, R, E, DocumentListener, ActionListener {

	public String creationDate() {return "20201206";}


	private Service iconBuilder;
	private Service iconsViewer1;
	private Service iconsViewer2;
	private Service findParts;

	private JPanel panel;
	private JLabel label;
	private JTextField field;
	
	private String iconKey = "";
	private String[] parts = new String[]{null,null};
	
	
	public EntityImpl() throws Exception
	{
		iconBuilder = Outside.service(this,"gus06.icon.builder");
		iconsViewer1 = Outside.service(this,"*gus06.app.icon.gui.viewer");
		iconsViewer2 = Outside.service(this,"*gus06.app.icon.part.gui.viewer");
		findParts = Outside.service(this,"gus06.icon.builder.findparts");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		field = new JTextField();
		field.setMargin(new Insets(2,2,2,2));
		
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
		panelTop.add(label,BorderLayout.EAST);
		panelTop.add(field,BorderLayout.CENTER);
		
		JPanel panelCenter = new JPanel(new GridLayout(1,2,5,5));
		panelCenter.add(titled((JComponent) iconsViewer1.i(),"Main icon"));
		panelCenter.add(titled((JComponent) iconsViewer2.i(),"Suffix icon"));
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel.add(panelTop,BorderLayout.NORTH);
		panel.add(panelCenter,BorderLayout.CENTER);
		
		field.getDocument().addDocumentListener(this);
		
		iconsViewer1.addActionListener(this);
		iconsViewer2.addActionListener(this);
	}
	
	
	
	private JPanel titled(JComponent comp, String title)
	{
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createEtchedBorder());
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(comp,BorderLayout.CENTER);
		
		return panel;
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{return iconKey;}
	
	
	
	public void e() throws Exception
	{
		iconsViewer1.e();
		iconsViewer2.e();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		iconKey = (String) obj;
		parts = (String[]) findParts.t(iconKey);
		
		updateFieldText();
		updateTableSelection();
		refreshLabel();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("keys")) return new String[]{"field"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void reset()
	{
		try
		{
			iconKey = "";
			parts = new String[]{null,null};
			
			updateFieldText();
			updateTableSelection();
			refreshLabel();
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	private void textChanged()
	{
		try
		{
			iconKey = field.getText();
			parts = (String[]) findParts.t(iconKey);
			
			updateTableSelection();
			refreshLabel();
		}
		catch(Exception e)
		{Outside.err(this,"textChanged()",e);}
	}
	
	
	public void actionPerformed(ActionEvent e)
	{iconSelected();}



	private void iconSelected()
	{
		try
		{
			String mainIcon = (String) iconsViewer1.g();
			String partIcon = (String) iconsViewer2.g();
			
			if(partIcon!=null && !partIcon.startsWith("PART_"))
				throw new Exception("Invalid part icon id: "+partIcon);
			
			parts = new String[]{mainIcon,partIcon};
			iconKey = rebuildIconText();
			
			updateFieldText();
			refreshLabel();
		}
		catch(Exception e)
		{Outside.err(this,"iconSelected()",e);}
	}
	
	
	
	private String rebuildIconText()
	{
		if(parts==null) return "";
		if(parts[0]==null) return "";
		return parts[1]!=null ? parts[0]+"_"+parts[1].substring(5) : parts[0];
	}
	
	
	private void refreshLabel() throws Exception
	{
		if(iconKey==null || iconKey.equals(""))
		{label.setIcon(null);return;}
		
		Icon icon = (Icon) iconBuilder.t(iconKey);
		label.setIcon(icon);
	}
	
	
	private void updateTableSelection() throws Exception
	{
		iconsViewer1.removeActionListener(this);
		iconsViewer1.v("select",parts[0]);
		iconsViewer1.addActionListener(this);
		
		iconsViewer2.removeActionListener(this);
		iconsViewer2.v("select",parts[1]);
		iconsViewer2.addActionListener(this);
	}
	
	
	private void updateFieldText()
	{
		field.getDocument().removeDocumentListener(this);
		field.setText(iconKey);
		field.getDocument().addDocumentListener(this);
	}
	
	
	
	public void removeUpdate(DocumentEvent e) {textChanged();}
	public void insertUpdate(DocumentEvent e) {textChanged();}
	public void changedUpdate(DocumentEvent e) {}
}