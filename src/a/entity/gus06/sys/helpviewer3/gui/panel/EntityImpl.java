package a.entity.gus06.sys.helpviewer3.gui.panel;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.util.List;
import java.awt.Font;
import javax.swing.JComponent;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250728";}
	
	public static final String KEY_NAME = "name";
	public static final String KEY_DATA = "data";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_ICONPROVIDER = "iconprovider";
	public static final String KEY_EXTERNAL = "external";
	
	public static final Font FONT = new Font("Calibri", Font.PLAIN, 18);
	public static final int MARGIN = 5;
	
	

	private Service executeScript;
	private Service exceptionViewer;
	private Service shiftPanel;
	private Service repaintLabel;
	private Service buildLabel;
	private Service formatData;

	private JPanel panel;
	private JLabel label;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		executeScript = Outside.service(this,"gus06.sys.script1.build2.g");
		exceptionViewer = Outside.service(this,"*gus06.data.viewer.exception");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display2");
		buildLabel = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		formatData = Outside.service(this,"gus06.string.transform.line.rm.c0.tab");
		
		label = (JLabel) buildLabel.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		String name = (String) get(KEY_NAME);
		String data = (String) get(KEY_DATA);
		List children = (List) get(KEY_CHILDREN);
		R ip = (R) get(KEY_ICONPROVIDER);
		Object external = get(KEY_EXTERNAL);
		
		if(name==null) name = "";
		if(!name.contains("#")) name = "SECTION_help#"+name;
		repaintLabel.v(name,new Object[]{label,ip});
		
		if(data==null || data.trim().equals(""))
		{
			shiftPanel.p(null);
			return;
		}
		
		String script = (String) formatData.t(data);
		
		Map scriptData = new HashMap();
		scriptData.put(KEY_ICONPROVIDER, ip);
		scriptData.put(KEY_EXTERNAL, external);
		scriptData.put(KEY_CHILDREN, children);
		scriptData.put(KEY_NAME, name);
		
		G g = (G) executeScript.t(new Object[]{script, scriptData});
		
		try
		{
			Object result = g.g();
			shiftPanel.p(result);
		}
		catch(Exception e)
		{
			exceptionViewer.p(e);
			shiftPanel.p(exceptionViewer);
		}
	}
	
	
	
	private Object get(String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}