package a.entity.gus06.sys.helpviewer1.gui.panel;

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

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20161022";}
	
	public static final String KEY_NAME = "name";
	public static final String KEY_DATA = "data";
	public static final String KEY_CHILDREN = "children";
	
	public static final Font FONT = new Font("Calibri", Font.PLAIN, 18);
	public static final int MARGIN = 5;
	
	


	private Service repaintLabel;
	private Service buildLabel;

	private JPanel panel;
	private JLabel label;
	private JTextArea area;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		buildLabel = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(FONT);
		area.setMargin(new Insets(MARGIN,MARGIN,MARGIN,MARGIN));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		label = (JLabel) buildLabel.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		String name = (String) get(KEY_NAME);
		String data = (String) get(KEY_DATA);
		List children = (List) get(KEY_CHILDREN);
		
		if(!name.contains("#")) name = "SECTION_help#"+name;
		
		repaintLabel.v(name,label);
		area.setText(data);
		area.setCaretPosition(0);
		
		if(children!=null)
		{
			//AJOUTER UN SOMMAIRE EN FIN DE SECTION
		}
	}
	
	
	
	private Object get(String key)
	{
		if(!map.containsKey(key)) return "";
		return map.get(key);
	}
}