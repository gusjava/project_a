package a.entity.gus06.data.editor.string.display;

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
import javax.swing.JTable;

public class EntityImpl implements Entity, I, P, G, R, E, DocumentListener, ActionListener {

	public String creationDate() {return "20201218";}


	private Service iconBuilder;
	private Service iconsViewer1;
	private Service iconsViewer2;
	private Service findParts;
	private Service tableFocusNext;

	private JPanel panel;
	private JLabel label;
	private JTextField field;
	
	private JTextField field1;
	private JTextField field2;
	
	private JTable table1;
	private JTable table2;
	
	
	private String title = "";
	private String iconKey = "";
	private String[] parts = new String[]{null,null};
	
	
	
	public EntityImpl() throws Exception
	{
		iconBuilder = Outside.service(this,"gus06.icon.builder");
		iconsViewer1 = Outside.service(this,"*gus06.app.icon.gui.viewer");
		iconsViewer2 = Outside.service(this,"*gus06.app.icon.part.gui.viewer");
		findParts = Outside.service(this,"gus06.icon.builder.findparts");
		tableFocusNext = Outside.service(this,"gus06.swing.table.cust.tab.focusnext");
		
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
		
		
		field1 = (JTextField) iconsViewer1.r("field");
		field2 = (JTextField) iconsViewer2.r("field");
		
		table1 = (JTable) iconsViewer1.r("table");
		table2 = (JTable) iconsViewer2.r("table");
		
		tableFocusNext.p(table1);
		tableFocusNext.p(table2);
		
		field1.setNextFocusableComponent(field2);
		table1.setNextFocusableComponent(field2);
		
		field2.setNextFocusableComponent(field1);
		table2.setNextFocusableComponent(field1);
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
	{return buildDisplay();}
	
	
	public void e() throws Exception
	{
		iconsViewer1.e();
		iconsViewer2.e();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		initTitleAndIconKey((String) obj);
		updateFieldText();
		updateTableSelection();
		refreshLabel();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		
		if(key.equals("field1")) return field1;
		if(key.equals("field2")) return field2;
		
		if(key.equals("table1")) return table1;
		if(key.equals("table2")) return table2;
		
		if(key.equals("keys")) return new String[]{"field","field1","field2","table1","table2"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void reset()
	{
		try
		{
			title = "";
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
			initTitleAndIconKey(field.getText());
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
		Icon icon = iconKey==null || iconKey.equals("") ? null : (Icon) iconBuilder.t(iconKey);
		
		label.setIcon(icon);
		label.setText(title);
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
		field.setText(buildDisplay());
		field.getDocument().addDocumentListener(this);
	}
	
	
	
	public void removeUpdate(DocumentEvent e) {textChanged();}
	public void insertUpdate(DocumentEvent e) {textChanged();}
	public void changedUpdate(DocumentEvent e) {}
	
	
	
	
	private void initTitleAndIconKey(String display) throws Exception
	{
		String[] n = display.split("#",2);
		title = n.length==1 ? display : n[1];
		iconKey = n.length==1 ? "" : n[0];
		parts = (String[]) findParts.t(iconKey);
	}
	
	private String buildDisplay()
	{
		if(iconKey==null || iconKey.equals("")) return title;
		return iconKey+"#"+title;
	}
}