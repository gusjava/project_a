package a.entity.gus06.y.openrouter.gui2translate;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;


public class EntityImpl implements Entity, I, V, R, ActionListener, Runnable {

	public String creationDate() {return "20251126";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	
	public static final String KEY_LANGUAGE1 = "language1";
	public static final String KEY_LANGUAGE2 = "language2";
	

	private Service translator;
	private Service comboBuilder;

	private JPanel panel;
	private JButton button;
	private JTextArea area1;
	private JTextArea area2;
	private JComboBox combo1;
	private JComboBox combo2;
	
	private R engine;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		translator = Outside.service(this,"gus06.web.openrouter.api.chat.query2.translator");
		comboBuilder = Outside.service(this,"gus06.swing.combobox.build.icon.language.en");
		
		button = new JButton("Translate");
		button.addActionListener(this);
		
		area1 = new JTextArea();
		area1.setMargin(new Insets(3,3,3,3));
		
		area2 = new JTextArea();
		area2.setMargin(new Insets(3,3,3,3));
		area2.setEditable(false);
		
		combo1 = (JComboBox) comboBuilder.i();
		combo2 = (JComboBox) comboBuilder.i();
		combo1.setSelectedItem("fr");
		combo2.setSelectedItem("en");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(combo1, BorderLayout.NORTH);
		panel1.add(new JScrollPane(area1), BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(combo2, BorderLayout.NORTH);
		panel2.add(new JScrollPane(area2), BorderLayout.CENTER);
		
		JPanel panel12 = new JPanel(new GridLayout(1,2));
		panel12.add(panel1);
		panel12.add(panel2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel12, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine")) {engine = (R) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("area1")) return area1;
		if(key.equals("area2")) return area2;
		if(key.equals("keys")) return new String[]{"area1","area2"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{launch();}
	
	
	private void launch()
	{
		if(t!=null && t.isAlive()) return;
		
		area1.setEnabled(false);
		button.setEnabled(false);
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			String prompt = area1.getText();
			String language1 = (String) combo1.getSelectedItem();
			String language2 = (String) combo2.getSelectedItem();
					
			Map map = new HashMap();
			map.put(KEY_PROMPT, prompt);
			map.put(KEY_LANGUAGE1, language1);
			map.put(KEY_LANGUAGE2, language2);
			map.put(KEY_MODEL, model());
			map.put(KEY_APIKEY, apikey());
			
			String response = (String) translator.t(map);
			area2.setText(response);
			area1.setEnabled(true);
			button.setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	private String model() throws Exception
	{return engine!=null ? (String) engine.r("model") : null;}
	
	private String apikey() throws Exception
	{return engine!=null ? (String) engine.r("apikey") : null;}
}