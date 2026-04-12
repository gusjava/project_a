package a.entity.gus06.y.openrouter.labelcredits;

import a.framework.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.Map;

public class EntityImpl implements Entity, I, V, ActionListener {

	public String creationDate() {return "20251203";}
	
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
		if(s.equals("creditsLoaded()")) refresh();
	}
	
	private Map credits() throws Exception
	{return engine!=null ? (Map) engine.r("credits") : null;}
	
	private String displayFor(Map credits)
	{
		if(credits==null) return "";
		double total = Double.parseDouble((String) credits.get("total_credits"));
		double usage = Double.parseDouble((String) credits.get("total_usage"));
		
		return "credits: "+(total-usage)+" ";
	}
	
	private void refresh()
	{
		try
		{
			label.setText(displayFor(credits()));
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}
