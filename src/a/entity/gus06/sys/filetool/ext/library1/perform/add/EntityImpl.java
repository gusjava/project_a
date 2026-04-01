package a.entity.gus06.sys.filetool.ext.library1.perform.add;

import a.framework.*;
import java.util.Map;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200311";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	
	
	private Service form;
	private Service okCancel;
	private Service clipboard;
	private Service fileDispay;
	private Service onEnter;
	private Service onEscap;
	private Service toolAdd;

	private JTextField field_path;
	private JTextField field_display;


	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
		fileDispay = Outside.service(this,"gus06.file.getdisplay");
		onEnter = Outside.service(this,"gus06.swing.textfield.cust3.execute.onenter");
		onEscap = Outside.service(this,"gus06.swing.textfield.cust3.execute.onescap");
		toolAdd = Outside.service(this,"gus06.sys.filetool.ext.library1.tool.add");
		
		field_path = new JTextField();
		field_display = new JTextField();
		
		form.v("Path",field_path);
		form.v("Display",field_display);
		
		onEnter.p(new Object[]{field_path,(E) this::ok});
		onEnter.p(new Object[]{field_display,(E) this::ok});
		
		onEscap.p(new Object[]{field_path,(E) this::cancel});
		onEscap.p(new Object[]{field_display,(E) this::cancel});
	}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String struct = get0(map, STRUCT);
		if(struct==null) struct = "";
		
		File file = (File) clipboard.g();
		
		String path0 = file!=null ? file.getAbsolutePath() : "";
		String display0 = (String) fileDispay.t(file);
		
		field_path.setText(path0);
		field_display.setText(display0);
		
		boolean ok = okCancel.f(form.i());
		if(!ok) return false;
		
		String path1 = field_path.getText();
		String display1 = field_display.getText();
		
		if(empty(path1)) return false;
		if(empty(display1)) return false;
		
		toolAdd.p(new Object[]{map, new String[]{path1, display1}});
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
}