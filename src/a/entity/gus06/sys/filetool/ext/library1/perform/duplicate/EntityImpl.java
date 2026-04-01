package a.entity.gus06.sys.filetool.ext.library1.perform.duplicate;

import a.framework.*;
import java.util.Map;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230117";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service form;
	private Service displayEditor;
	private Service okCancel;
	private Service onEnter;
	private Service onEscap;
	private Service fieldHolderKey;
	private Service fieldHolderPath;
	private Service genAlphanum;

	private JTextField fieldKey;
	private JTextField fieldPath;
	private JTextField fieldDisplay;
	private JTextField fieldDisplay1;
	private JTextField fieldDisplay2;
	private JComponent compDisplay;
	
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		displayEditor = Outside.service(this,"*gus06.data.editor.string.display");
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		onEnter = Outside.service(this,"gus06.swing.textfield.cust3.execute.onenter");
		onEscap = Outside.service(this,"gus06.swing.textfield.cust3.execute.onescap");
		fieldHolderKey = Outside.service(this,"*gus06.data.editor.string.textfield.editor1-1");
		fieldHolderPath = Outside.service(this,"*gus06.data.editor.string.textfield.editor1-2");
		genAlphanum = Outside.service(this,"gus06.data.generate.string.random.alphanum8");
		
		fieldKey = (JTextField) fieldHolderKey.i();
		fieldPath = (JTextField) fieldHolderPath.i();
		compDisplay = (JComponent) displayEditor.i();
		
		Font font = new JLabel().getFont().deriveFont(Font.BOLD);
		Border b1 = BorderFactory.createEmptyBorder(10,10,10,10);
		Border b2 = BorderFactory.createTitledBorder(b1,"Display", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, font);
		compDisplay.setBorder(b2);
		
		form.v("Key",fieldKey);
		form.v("Path",fieldPath);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) form.i(), BorderLayout.NORTH);
		panel.add(compDisplay, BorderLayout.CENTER);
		
		
		// customize fieldKey, fieldPath
		
		fieldHolderKey.v("onCleared",(E) this::cancel);
		fieldHolderPath.v("onCleared",(E) this::cancel);
		
		onEnter.p(new Object[]{fieldKey,(E) this::ok});
		onEnter.p(new Object[]{fieldPath,(E) this::ok});
		
		
		// customize fieldDisplay, display1, display2
		
		fieldDisplay = (JTextField) displayEditor.r("field");
		fieldDisplay1 = (JTextField) displayEditor.r("field1");
		fieldDisplay2 = (JTextField) displayEditor.r("field2");
		
		onEnter.p(new Object[]{fieldDisplay,(E) this::ok});
		onEnter.p(new Object[]{fieldDisplay1,(E) this::ok});
		onEnter.p(new Object[]{fieldDisplay2,(E) this::ok});
		
		onEscap.p(new Object[]{fieldDisplay,(E) this::cancel});
		onEscap.p(new Object[]{fieldDisplay1,(E) this::cancelFromDisplay1});
		onEscap.p(new Object[]{fieldDisplay2,(E) this::cancelFromDisplay2});
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		String key0 = (String) o[1];
		
		if(key0==null) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) return false;
		
		String path0 = get1(map,CONTENT+"."+key0);
		String display0 = get1(map,DISPLAY+"."+key0);
		
		fieldKey.setText((String) genAlphanum.g());
		fieldPath.setText(path0);
		displayEditor.p(display0);
		
		fieldDisplay1.setText("");
		fieldDisplay2.setText("");
		
		boolean ok = okCancel.f(panel);
		if(!ok) return false;
		
		String key1 = fieldKey.getText();
		String path1 = fieldPath.getText();
		String display1 = (String) displayEditor.g();
		
		if(empty(key1)) return false;
		if(empty(path1)) return false;
		if(empty(display1)) return false;
		if(key0.equals(key1)) return false;
		
		
		map.put(CONTENT+"."+key1,path1);
		map.put(DISPLAY+"."+key1,display1);
		
		String[] nn = struct.split(";");
		int nb = nn.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			b.append(nn[i]);
			if(nn[i].equals(key0)) b.append(";"+key1);
			if(i<nb-1) b.append(";");
		}
		map.put(STRUCT,b.toString());
		
		return true;
	}

	

	private boolean empty(String s)
	{return s==null || s.trim().equals("");}
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private String get1(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
	
	private void ok()
	{
		try{okCancel.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"ok()",e);}
	}
	
	private void cancel()
	{
		try{okCancel.v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	private void cancelFromDisplay1()
	{
		if(fieldDisplay1.getText().equals("")) cancel();
		else fieldDisplay1.setText("");
	}
	
	private void cancelFromDisplay2()
	{
		if(fieldDisplay2.getText().equals("")) cancel();
		else fieldDisplay2.setText("");
	}
}