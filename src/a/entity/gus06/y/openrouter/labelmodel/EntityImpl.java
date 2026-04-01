package a.entity.gus06.y.openrouter.labelmodel;

import a.framework.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, V, ActionListener {

	public String creationDate() {return "20251201";}
	
	public static final String KEY_MODEL = "model";

	private Service repaintLabel;

	private JLabel label;
	private R engine;
	
	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		label = new JLabel(" ");
	}
	
	public Object i() throws Exception
	{return label;}
	
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
	
	private String model() throws Exception
	{return engine!=null ? (String) engine.r("model") : null;}
	
	private String displayFor(String model)
	{
		if(model==null || model.equals("")) return "";
		return "AI_llm#"+model;
	}
	
	private void refresh()
	{
		try
		{
			repaintLabel.v(displayFor(model()), label);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}
