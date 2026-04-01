package a.entity.gus06.y.openrouter.gui1ask;

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


public class EntityImpl implements Entity, I, V, R, Runnable {

	public String creationDate() {return "20251126";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	
	public static final String KEY_LANGUAGE1 = "language1";
	public static final String KEY_LANGUAGE2 = "language2";
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);


	private Service compHolder;
	private Service fieldFactory;
	private Service apiCall;
	private Service translator;

	private JPanel panel;
	private JTextPane console;
	private JTextField field;
	
	private PrintStream p_in;
	private PrintStream p_out;
	
	private R engine;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		fieldFactory = Outside.service(this,"gus06.swing.textfield.factory.recallfield");
		apiCall = Outside.service(this,"gus06.web.openrouter.api.chat.query");
		translator = Outside.service(this,"gus06.web.openrouter.api.chat.query2.translator");
		
		field = (JTextField) fieldFactory.i();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{launch();}
		});
		
		console = (JTextPane) compHolder.i();
		console.setMargin(new Insets(3,3,3,3));
		console.setEditable(false);
		console.setFont(FONT);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(console),BorderLayout.CENTER);
		
		p_out = (PrintStream) compHolder.r("blue");
		p_in = (PrintStream) compHolder.r("black");
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
		if(key.equals("field")) return field;
		if(key.equals("console")) return console;
		if(key.equals("keys")) return new String[]{"field","console"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	private String input()
	{
		String query = field.getText();
		field.setText("");
		p_in.println(query);
		return query;
	}
	
	private void launch()
	{
		try
		{
			if(t!=null && t.isAlive()) return;
			field.setEnabled(false);
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"launch()",e);}
	}

	
	
	public void run()
	{
		try
		{
			String query = input();
			if(query.matches("[a-z][a-z]->[a-z][a-z]:.+")) translate(query);
			else callAPI(query);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		field.setEnabled(true);
	}
	
	private void translate(String query) throws Exception
	{
		String prompt = query.substring(7);
		String language1=query.substring(0,1);
		String language2=query.substring(4,5);
				
		Map map = new HashMap();
		map.put(KEY_PROMPT, prompt);
		map.put(KEY_LANGUAGE1, language1);
		map.put(KEY_LANGUAGE2, language2);
		map.put(KEY_MODEL, model());
		map.put(KEY_APIKEY, apikey());
		
		String response = (String) translator.t(map);
		
		p_out.println(response);
		p_out.println();
	}
	
	private void callAPI(String query) throws Exception
	{
		Map map = new HashMap();
		map.put(KEY_PROMPT, query);
		map.put(KEY_MODEL, model());
		map.put(KEY_APIKEY, apikey());
		
		Map r = (Map) apiCall.t(map);
		String response = (String) r.get("content");
		
		p_out.println(response);
		p_out.println();
	}
	
	private String model() throws Exception
	{return engine!=null ? (String) engine.r("model") : null;}
	
	private String apikey() throws Exception
	{return engine!=null ? (String) engine.r("apikey") : null;}
}