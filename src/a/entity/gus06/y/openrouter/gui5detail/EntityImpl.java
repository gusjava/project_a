package a.entity.gus06.y.openrouter.gui5detail;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, I, V, ActionListener {

	public String creationDate() {return "20251201";}
	
	private Service dataViewer;
	private Service urlClickable;
	private Service formPanel;
	private Service getDeep;

	private JPanel panel;
	private JTextArea area;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private R engine;
	private Map data;
	private Map labels;
	

	public EntityImpl() throws Exception
	{
		dataViewer = Outside.service(this,"*gus06.data.viewer.map");
		urlClickable = Outside.service(this,"gus06.swing.textcomp.cust.urlclickable1");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		getDeep = Outside.service(this,"gus06.map.deep.get");
		
		labels = new HashMap();
		
		initLabel("id", "id");
		initLabel("name", "name");
		initLabel("hugging_face_id", "hugging_face_id");
		initLabel("canonical_slug", "canonical_slug");
		initLabel("context_length", "context_length");
		
		initLabel("input_modalities", "architecture.input_modalities.0");
		initLabel("instruct_type", "architecture.instruct_type");
		initLabel("modality", "architecture.modality");
		initLabel("tokenizer", "architecture.tokenizer");
		
		initLabel("pricing_completion", "pricing.completion");
		initLabel("pricing_image", "pricing.image");
		initLabel("pricing_internal_reasoning", "pricing.internal_reasoning");
		initLabel("pricing_prompt", "pricing.prompt");
		initLabel("pricing_request", "pricing.request");
		initLabel("pricing_web_search", "pricing.web_search");
		
		initLabel("top_provider.context_length", "top_provider.context_length");
		initLabel("top_provider.max_completion_tokens", "top_provider.max_completion_tokens");
		initLabel("top_provider.is_moderated", "top_provider.is_moderated");

		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setFont(area.getFont().deriveFont((float) 14));
		area.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		area.setEditable(false);
		urlClickable.p(area);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formPanel.i(), BorderLayout.WEST);
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
//		panel.add((JComponent) dataViewer.i(), BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
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

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("updated()")) refresh();
	}
	
	private void refresh()
	{
		try
		{
			data = engine!=null ? (Map) engine.r("data") : null;
			dataViewer.p(data);
			
			String desc = get("description");
			
			area.setText(desc);
			Iterator it = labels.keySet().iterator();
			while(it.hasNext())
			{
				String path = (String) it.next();
				JLabel label = (JLabel) labels.get(path);
				display(label, path);
			}
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	private void initLabel(String display, String path) throws Exception
	{
		JLabel label = new JLabel(" ");
		labels.put(path, label);
		formPanel.v(display, label);
	}
	
	private String get(String key)
	{
		if(data==null || !data.containsKey(key)) return "";
		return (String) data.get(key);
	}
	
	private void display(JLabel label, String key) throws Exception
	{
		label.setText(getDeep(key));
	}
	
	private String getDeep(String key) throws Exception
	{
		return ""+getDeep.t(new Object[]{data, key});
	}
}
