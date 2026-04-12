package a.entity.gus06.y.openrouter.gui4prompt;

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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.BorderFactory;


public class EntityImpl implements Entity, I, V, R, Runnable, ActionListener {

	public String creationDate() {return "20251129";}
	
	public static final String KEY_PROMPT = "prompt";
	public static final String KEY_MODEL = "model";
	public static final String KEY_APIKEY = "apikey";
	public static final String KEY_STOREDIR = "storedir";
	public static final String KEY_TIMEOUT = "timeout";

	public static final Border BORDER = BorderFactory.createCompoundBorder(
		BorderFactory.createEtchedBorder(),
		BorderFactory.createEmptyBorder(5, 5, 5, 5)
	);

	private Service apiCall;
	private Service splitCust;
	private Service buildTitledPanel;
	private Service inputGui;
	private Service outputGui;
	private Service textChanged;
	private Service format2dc;

	private JPanel panel;
	private JButton button;
	private JLabel labelInfo1;
	private JLabel labelInfo2;
	private JTextComponent areaInput;
	
	private R engine;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		apiCall = Outside.service(this,"gus06.web.openrouter.api.chat.query");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		buildTitledPanel = Outside.service(this,"gus06.swing.comp.build.titledpanel");
		inputGui = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		outputGui = Outside.service(this,"*gus06.data.viewer.string.textarea.editor1");
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		format2dc = Outside.service(this,"gus06.string.transform.format.number.decimal2");
		
		labelInfo1 = new JLabel(" ");
		labelInfo2 = new JLabel(" ");
		
		JComponent compInput = (JComponent) inputGui.i();
		JComponent compOutput = (JComponent) outputGui.i();
		
		compInput.setBorder(BORDER);
		compOutput.setBorder(BORDER);
		
		JPanel panelInput = buildTitled(labelInfo1, inputGui, "Prompt");
		JPanel panelOutput = buildTitled(labelInfo2, outputGui, "Response");
		
		areaInput = (JTextComponent) inputGui.r("comp");
		S sup = (S) textChanged.t(areaInput);
		sup.addActionListener(e->areaInputChanged());
		
		JSplitPane split = new JSplitPane();
		split.setLeftComponent(panelInput);
		split.setRightComponent(panelOutput);
		
		splitCust.p(split);
		split.setDividerLocation(600);
		
		button = new JButton("Send request");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{launch();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	private JPanel buildTitled(JLabel label, Object gui, String title) throws Exception
	{
		return (JPanel) buildTitledPanel.t(new Object[]{label, gui, title});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private void launch()
	{
		try
		{
			if(engine==null) return;
			if(t!=null && t.isAlive()) return;
			button.setEnabled(false);
			button.setText("Waiting for response...");
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
			outputGui.p("");
			labelInfo2.setText(" ");
			
			String input = (String) inputGui.g();
			String model = model();
			String apikey = apikey();
			File storeDir = (File) ((R) engine).r("storeDir");
			Integer timeout = (Integer) ((R) engine).r("timeout");
			
			Map map = new HashMap();
			map.put(KEY_PROMPT, input);
			map.put(KEY_MODEL, model);
			map.put(KEY_APIKEY, apikey);
			map.put(KEY_STOREDIR, storeDir);
			map.put(KEY_TIMEOUT, timeout);
			
			Map r = (Map) apiCall.t(map);
			
			String error = (String) get(r,"error");
			String response = (String) get(r,"response");
			String duration = (String) get(r,"duration");
			String promptTokenNb = (String) get(r,"prompt_token_nb");
			String reponseTokenNb = (String) get(r,"response_token_nb");
			
			if(error!=null)
			{
				outputGui.p("###"+error);
				labelInfo2.setText("ERROR ("+duration+" ms)");
			}
			else
			{
				outputGui.p(response);
				labelInfo2.setText(promptTokenNb+" \u2192 "+reponseTokenNb+" ("+duration+" ms)");
			}
			loadCredits();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		
		button.setEnabled(true);
		button.setText("Send request");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			if(engine!=null) ((S)engine).removeActionListener(this);
			engine = (R) obj;
			((S)engine).addActionListener(this);
			refresh();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("areaInput")) return inputGui.r("comp");
		if(key.equals("areaOutput")) return outputGui.r("comp");
		if(key.equals("keys")) return new String[]{"areaInput","areaOutput"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	private String model() throws Exception
	{return engine!=null ? (String) engine.r("model") : null;}
	
	private String apikey() throws Exception
	{return engine!=null ? (String) engine.r("apikey") : null;}
	
	private Map data() throws Exception
	{return engine!=null ? (Map) engine.r("data") : null;}
	
	private String get(Map map, String key, String defaultValue)
	{return map.containsKey(key) ? (String) map.get(key) : defaultValue;}
	
	private String get(Map map, String key)
	{return get(map, key, null);}
	
	private long contextLength() throws Exception
	{
		Map data = data();
		if(data==null) return -1;
		return Long.parseLong(get(data, "context_length", "-1"));
	}
	
	private long inputTokenNb() throws Exception
	{
		double textLength = (double) areaInput.getDocument().getLength();
		return (long) (textLength/4.68);
	}
	
	private void loadCredits() throws Exception
	{
		((V) engine).v("loadCredits",null);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("updated()")) refresh();
	}
	
	private void refresh()
	{
		try
		{
			outputGui.p("");
			labelInfo2.setText(" ");
			refreshLabelInfo1();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}

	
	private void areaInputChanged()
	{
		try
		{
			refreshLabelInfo1();
		}
		catch(Exception e)
		{Outside.err(this,"areaInputChanged()",e);}
	}

	private void refreshLabelInfo1() throws Exception
	{
		long contextLength = contextLength();
		long inputTokenNb = inputTokenNb();
		double percent = (double) inputTokenNb/(double) contextLength * 100.0;
		String percentF = (String) format2dc.t(percent);
		
		labelInfo1.setText(inputTokenNb+" / "+contextLength+" ("+percentF+"%)");
	}
}