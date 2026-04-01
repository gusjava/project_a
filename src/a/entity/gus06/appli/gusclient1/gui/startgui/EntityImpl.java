package a.entity.gus06.appli.gusclient1.gui.startgui;

import a.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;


public class EntityImpl extends S1 implements Entity, I, F, ActionListener, DocumentListener {

	public String creationDate() {return "20140826";}

	public static final Font FONT = new Font("Calibri",Font.PLAIN,30);
	public static final String KEY_PSEUDO = "dev.pseudo";

	
	private Service findPseudo;
	private Service localizer;
	private Service isValid;
	private Service setProp;
	
	private JPanel panel;
	private JLabel label;
	private JTextField field;
	private JButton button;
	private JTextPane textPane;
	private JScrollPane scroll;
	
	
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		localizer = Outside.service(this,"gus06.ling.localize.manager");
		isValid = Outside.service(this,"gus06.entitydev.pseudo.isvalid");
		setProp = Outside.service(this,"gus06.command.prop2.set");
		
		label = new JLabel("First start");
		label.setFont(FONT.deriveFont(Font.BOLD));
		label.setHorizontalAlignment(JLabel.CENTER);
		
		textPane = new JTextPane();
		textPane.setFont(FONT);
		textPane.setMargin(new Insets(5,5,5,5));
		textPane.setEditable(false);
		textPane.setOpaque(false);
		
		scroll = new JScrollPane(textPane);
		scroll.setBorder(BorderFactory.createEmptyBorder(20,40,0,40));
		
		field = new JTextField(20);
		field.setFont(FONT);
		field.setMargin(new Insets(5,5,5,5));
		field.setHorizontalAlignment(JLabel.CENTER);
		
		button = new JButton("Validate");
		button.setFont(FONT);
		button.setEnabled(false);
		
		
		JPanel p = new JPanel(new GridLayout(2,1,20,20));
		p.setBorder(BorderFactory.createEmptyBorder(20,80,20,80));
		
		p.add(field);
		p.add(button);
		
		
		localizer.v("gusclient1_init_title",label);
		localizer.v("gusclient1_init_text",textPane);
		localizer.v("gusclient1_init_button",button);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(p,BorderLayout.SOUTH);
		
		field.getDocument().addDocumentListener(this);
		button.addActionListener(this);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) return true;	
		return false;
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void actionPerformed(ActionEvent e)
	{validate();}
	
	
	private void validate()
	{
		try
		{
			String pseudo = field.getText();
			if(!isValid(pseudo)) return;
			
			setProp.v(KEY_PSEUDO,pseudo);
			validated();
		}
		catch(Exception e)
		{Outside.err(this,"validate()",e);}
	}
	
	
	private void validated()
	{send(this,"validated()");}
	
	
	
	
	public void changedUpdate(DocumentEvent e) {}
	public void insertUpdate(DocumentEvent e) {textChanged_();}
	public void removeUpdate(DocumentEvent e) {textChanged_();}
	
	private void textChanged_()
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {textChanged();}
		});
	}
	
	
	private void textChanged()
	{
		try
		{
			String pseudo = field.getText();
			
			if(isValid(pseudo))
			{
				field.setForeground(Color.BLUE);
				button.setEnabled(true);
			}
			else
			{
				field.setForeground(Color.RED);
				button.setEnabled(false);
			}
		}
		catch(Exception e)
		{Outside.err(this,"textChanged()",e);}
	}
	
	
	
	
	private boolean isValid(String pseudo) throws Exception
	{return isValid.f(pseudo) && !pseudo.equals("gus");}
}
