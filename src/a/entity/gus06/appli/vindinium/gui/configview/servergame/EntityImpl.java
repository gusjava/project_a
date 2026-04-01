package a.entity.gus06.appli.vindinium.gui.configview.servergame;

import a.framework.*;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EntityImpl implements Entity, I, DocumentListener, ChangeListener, ItemListener {

	public String creationDate() {return "20170923";}

	
	public static final String TITLE = "Server";
	
	public static final String[] MAPS = new String[]{"random","m1","m2","m3","m4","m5","m6"};
	public static final String[] TURNS = new String[]{
		"300","290","280","270","260",
		"250","240","230","220","210",
		"200","190","180","170","160",
		"150","140","130","120","110",
		"100","90","80","70","60",
		"50","40","30","20","10"};
	
	
	
	
	private Service form;
	private Service whiteCombo;
	private Service titledBorder;
	private Service persistence1;
	private Service sessionParams;
	
	private JPanel panel;
	
	private JTextField field_key;
	private JSpinner spin_times;
	private JCheckBox check_restart;
	
	private JComboBox combo_turns;
	private JComboBox combo_maps;
	




	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		whiteCombo = Outside.service(this,"gus06.swing.combobox.cust.white");
		titledBorder = Outside.service(this,"gus06.swing.comp.cust2.border.titledborder1.m10");
		persistence1 = Outside.service(this,"gus06.app.persister1.manager.swing");
		sessionParams = Outside.service(this,"gus06.appli.vindinium.session.params");
		
		
		field_key = new JTextField(10);
		spin_times = new JSpinner(new SpinnerNumberModel(1,1,100,1));
		check_restart = new JCheckBox("Restart after 10 minutes");
		
		combo_turns = new JComboBox(TURNS);
		combo_maps = new JComboBox(MAPS);
		
		whiteCombo.p(combo_turns);
		whiteCombo.p(combo_maps);
		
		
		persist("field_key",field_key);
		persist("spin_times",spin_times);
		persist("check_restart",check_restart);
		persist("combo_turns",combo_turns);
		persist("combo_maps",combo_maps);
		
		
		
		form.v("Server Key",field_key);
		form.v("Repeat Game",spin_times);
		form.v(" ",check_restart);
		
		form.v(" ",new JLabel(" "));
		
		form.v("Training Turns",combo_turns);
		form.v("Training Map",combo_maps);
		
		
		panel = left((JComponent) form.i());
		titledBorder.v(TITLE,panel);
		
		
		field_key.getDocument().addDocumentListener(this);
		spin_times.addChangeListener(this);
		check_restart.addChangeListener(this);
		combo_turns.addItemListener(this);
		combo_maps.addItemListener(this);
		
		updateParams();
	}

	
	
	
	private void persist(String key, JComponent comp) throws Exception
	{persistence1.v(getClass().getName()+"_"+key,comp);}


	public Object i() throws Exception
	{return panel;}


	
	
	
	private JPanel left(JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.WEST);
		return p;
	}
	
	
	
	public void removeUpdate(DocumentEvent e) {updateParams();}
	public void insertUpdate(DocumentEvent e) {updateParams();}
	public void changedUpdate(DocumentEvent e) {}
	public void itemStateChanged(ItemEvent e) {updateParams();}
	public void stateChanged(ChangeEvent e) {updateParams();}
	
	
	
	private void updateParams()
	{
		try
		{
			String key = field_key.getText();
			String times = ""+spin_times.getValue();
			String restart = ""+check_restart.isSelected();
			String turns = ""+combo_turns.getSelectedItem();
			String map = ""+combo_maps.getSelectedItem();
			
			if(map.equals("random")) map = null;
			
			sessionParams.v(PARAMS.KEY,key);
			sessionParams.v(PARAMS.TIMES,times);
			sessionParams.v(PARAMS.RESTART,restart);
			sessionParams.v(PARAMS.TURNS,turns);
			sessionParams.v(PARAMS.MAP,map);
		}
		catch(Exception e)
		{Outside.err(this,"updateParams()",e);}
	}
}