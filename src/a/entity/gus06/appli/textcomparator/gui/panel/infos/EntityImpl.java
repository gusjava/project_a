package a.entity.gus06.appli.textcomparator.gui.panel.infos;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150305";}

	
	public static final String KEY_T1_LENGTH = "text1.length";
	public static final String KEY_T2_LENGTH = "text2.length";
	public static final String KEY_C_LENGTH = "common.length";
	public static final String KEY_T1_MD5 = "text1.md5";
	public static final String KEY_T2_MD5 = "text2.md5";
	public static final String KEY_EQUALS = "equals";
	public static final String KEY_DIFFCHARS = "diffchars";


	private JPanel panel;
	
	private JLabel label_t1;
	private JLabel label_t2;
	private JLabel label_c;
	

	public EntityImpl() throws Exception
	{
		label_t1 = label();
		label_t2 = label();
		label_c = label();
		
		JPanel p1 = new JPanel(new GridLayout(1,2,3,3));
		p1.add(label_t1);
		p1.add(label_t2);
		
		panel = new JPanel(new GridLayout(2,1,3,3));
		panel.add(p1);
		panel.add(label_c);
	}
	
	
	
	private JLabel label()
	{
		JLabel l = new JLabel(" ");
		l.setBorder(BorderFactory.createRaisedBevelBorder());
		l.setFont(l.getFont().deriveFont((float) 14));
		return l;
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		label_t1.setText(label_t1_text(map));
		label_t2.setText(label_t2_text(map));
		label_c.setText(label_c_text(map));
	}
	
	
	
	
	private String label_t1_text(Map map) throws Exception
	{
		String l = get(map,KEY_T1_LENGTH);
		String m = get(map,KEY_T1_MD5);
		return "md5:"+m+" length:"+l;
	}
	
	private String label_t2_text(Map map) throws Exception
	{
		String l = get(map,KEY_T2_LENGTH);
		String m = get(map,KEY_T2_MD5);
		return "md5:"+m+" length:"+l;
	}
	
	private String label_c_text(Map map) throws Exception
	{
		String lc = get(map,KEY_C_LENGTH);
		String equals = get(map,KEY_EQUALS);
		
		StringBuffer b = new StringBuffer();
		b.append("equals:"+equals);
		b.append(" common length:"+lc);
		
		if(has(map,KEY_DIFFCHARS))
		{
			String diff = get(map,KEY_DIFFCHARS);
			if(diff.length()!=2) throw new Exception("Invalid diff value: "+diff);
			
			int code1 = diff.codePointAt(0);
			int code2 = diff.codePointAt(1);
			
			char char1 = diff.charAt(0);
			char char2 = diff.charAt(1);
			
			String display1 = "["+toString(char1)+"] ("+code1+")";
			String display2 = "["+toString(char2)+"] ("+code2+")";
			
			b.append(" difference:"+display1+" <> "+display2);
		}
		return b.toString();
	}
	
	
	private String toString(char c)
	{
		if(c=='\n')return "\\n";
		if(c=='\r')return "\\r";
		if(c=='\t')return "\\t";
		return ""+c;
	}
	
	
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}
