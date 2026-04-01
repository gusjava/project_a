package a.entity.gus06.jdbc.gui.sqlquery1.inputarea;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;

public class EntityImpl extends S1 implements Entity, R, I, G, E {

	public String creationDate() {return "20150622";}
	
	public static final String TITLE = "SQL query";


	private Service syntax;
	private Service undo;
	private Service putAction;

	private JPanel panel;
	private JTextPane area;
	
	
	

	public EntityImpl() throws Exception
	{
		syntax = Outside.service(this,"gus06.swing.textpane.cust.syntax.sql.ostermiller");
		undo = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_zy.undoredo");
		putAction = Outside.service(this,"gus06.swing.textcomp.cust.putaction.ctrl_q");

		area = new JTextPane();
		initFont();
		
		syntax.p(area);
		
		putAction.p(new Object[]{area,this});
		undo.p(area);
		
		JLabel titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return text();}


	public void e() throws Exception
	{perform();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("area")) return area;
		if(key.equals("keys")) return new String[]{"area"};
		
		throw new Exception("Unknown key: "+key);
	}

	
	private void perform()
	{send(this,"perform()");}



	
	private void initFont()
	{
		int fontSize = area.getFont().getSize();
		area.setFont(new Font("Courier New",Font.PLAIN,fontSize));
	}
	
	
	private String text()
	{
		String s = area.getSelectedText();
		if(s!=null && !s.equals("")) return s;
		return area.getText();
	}
}
