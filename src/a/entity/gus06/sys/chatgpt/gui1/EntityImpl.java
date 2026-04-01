package a.entity.gus06.sys.chatgpt.gui1;

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


public class EntityImpl implements Entity, I, V, R {

	public String creationDate() {return "20250112";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	
	public static final String KEY_LANGUAGE1 = "language1";
	public static final String KEY_LANGUAGE2 = "language2";
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);


	private Service compHolder;
	private Service fieldFactory;
	private Service chatGPT;
	private Service translator;

	private JPanel panel;
	private JTextPane console;
	private JTextField field;
	
	private PrintStream p_in;
	private PrintStream p_out;
	
	private String model;
	private String apikey;
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		fieldFactory = Outside.service(this,"gus06.swing.textfield.factory.recallfield");
		chatGPT = Outside.service(this,"gus06.web.chatgpt.api.query");
		translator = Outside.service(this,"gus06.web.chatgpt.api.query2.prompt.translator");
		
		field = (JTextField) fieldFactory.i();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{performQuery(input());}
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
	
	
	private String input()
	{
		String text = field.getText();
		field.setText("");
		return text;
	}
	
	
	private void performQuery(String query)
	{
		try
		{
			if(query.matches("[a-z][a-z]->[a-z][a-z]:.+"))
				translate(query);
			else callAPI(query);
		}
		catch(Exception e)
		{Outside.err(this,"performQuery(String)",e);}
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
		map.put(KEY_MODEL, model);
		map.put(KEY_APIKEY, apikey);
		
		String response = (String) translator.t(map);
		
		p_in.println(query);
		p_out.println(response);
		p_out.println();
	}
	
	private void callAPI(String query) throws Exception
	{
		Map map = new HashMap();
		map.put(KEY_PROMPT, query);
		map.put(KEY_MODEL, model);
		map.put(KEY_APIKEY, apikey);
		
		String response = (String) chatGPT.t(map);
		
		p_in.println(query);
		p_out.println(response);
		p_out.println();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("model")) {model = (String) obj;return;}
		if(key.equals("apikey")) {apikey = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("field")) return field;
		if(key.equals("console")) return console;
		if(key.equals("keys")) return new String[]{"field","console"};
		
		throw new Exception("Unknown key: "+key);
	}
}