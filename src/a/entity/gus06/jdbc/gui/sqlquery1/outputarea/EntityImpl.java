package a.entity.gus06.jdbc.gui.sqlquery1.outputarea;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P, R {

	public String creationDate() {return "20150622";}
	
	public static final String TITLE = "SQL response";



	private JPanel panel;
	private JTextArea area;
	
	
	

	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		JLabel titleLabel = new JLabel(TITLE);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		String res = obj==null?"":(String) obj;
		area.setText(res);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("area")) return area;
		if(key.equals("keys")) return new String[]{"area"};
		
		throw new Exception("Unknown key: "+key);
	}
}
