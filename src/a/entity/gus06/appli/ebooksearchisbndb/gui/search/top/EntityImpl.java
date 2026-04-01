package a.entity.gus06.appli.ebooksearchisbndb.gui.search.top;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, ActionListener, I, G {

	public String creationDate() {return "20150509";}
	
	public static final String FORMAT = "format";
	public static final String KEY = "key";
	public static final String TYPE = "type";
	public static final String INFO = "info";
	
	public static final String KEY_APIKEY = "apikey";
	


	private Service custCombo;
	private Service optionManager;
	private Service persist;

	private JPanel panel;
	
	private JComboBox combo;
	private JTextField field;
	private JButton button;
	
	

	public EntityImpl() throws Exception
	{
		custCombo = Outside.service(this,"gus06.swing.combobox.cust.white");
		optionManager = Outside.service(this,"gus06.sys.option.manager");
		persist = Outside.service(this,"gus06.app.persister1.manager.swing");
		
		combo = new JComboBox(new String[]{"book","subject","category","author","publisher"});
		custCombo.p(combo);
		
		button = new JButton("Search");
		button.addActionListener(this);
		
		field = new JTextField();
		field.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(combo,BorderLayout.WEST);
		panel.add(field,BorderLayout.CENTER);
		panel.add(button,BorderLayout.EAST);
		
		persist.v(getClass().getName()+"_field",field);
		persist.v(getClass().getName()+"_combo",combo);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{search();}
	
	
	
	private void search()
	{send(this,"search()");}
	
	
	public Object g() throws Exception
	{
		String info = field.getText();
		String type = (String) combo.getSelectedItem();
		String key = option(KEY_APIKEY);
		
		Map map = new HashMap();
		map.put(FORMAT,"json");
		map.put(KEY,key);
		map.put(TYPE,type);
		map.put(INFO,info);
		
		return map;
	}
	
	
	private String option(String key) throws Exception
	{return (String) optionManager.r(key);}
}
