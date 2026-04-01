package a.entity.gus06.y.maven1.gui1.detail;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.util.Map;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, P, I, V {
	public String creationDate() {return "20251220";}


	private Service repaintLabel;
	private Service mapViewer;
	private Service swingWorker;

	private JPanel panel;
	private JLabel label;
	
	private Object engine;
	private String path;

	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		mapViewer = Outside.service(this,"*gus06.data.viewer.object");
		swingWorker = Outside.service(this,"gus06.swing.swingworker");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		panel.add((JComponent) mapViewer.i(), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		path = (String) obj;
		repaintLabel.v(buildDisplay(), label);
		if(engine!=null) load();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine")){engine = obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void load()
	{
		try
		{
			G g = (G) this::retrieve;
			swingWorker.p(new Object[]{mapViewer, g});
		}
		catch(Exception e)
		{Outside.err(this,"load()",e);}
	}
	
	private Map retrieve()
	{
		try
		{
			if(engine==null) return null;
			return (Map) ((R)engine).r("retrieve:"+path);
		}
		catch(Exception e)
		{Outside.err(this,"retrieve(String)",e);}
		return null;
	}
	
	private String buildDisplay()
	{return path!=null && !path.isEmpty() ? "dir2#"+path : " ";}
}
