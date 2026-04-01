package a.entity.gus06.appli.gusexplorer.gui.editor.fillbar;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.JToolBar;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190331";}

	public static final String KEY_F1_HANDLER = "F1_handler";
	public static final String KEY_F2_HANDLER = "F2_handler";
	public static final String KEY_F3_HANDLER = "F3_handler";
	
	public static final String KEY_CTRL_B_HANDLER = "ctrl_b_handler";
	public static final String KEY_CTRL_C_HANDLER = "ctrl_c_handler";
	public static final String KEY_CTRL_D_HANDLER = "ctrl_d_handler";
	public static final String KEY_CTRL_E_HANDLER = "ctrl_e_handler";
	public static final String KEY_CTRL_F_HANDLER = "ctrl_f_handler";
	public static final String KEY_CTRL_L_HANDLER = "ctrl_l_handler";
	public static final String KEY_CTRL_V_HANDLER = "ctrl_v_handler";
	public static final String KEY_CTRL_X_HANDLER = "ctrl_x_handler";
	
	public static final String KEY_CTRL_SHIFT_C_HANDLER = "ctrl_shift_c_handler";
	public static final String KEY_CTRL_SHIFT_F_HANDLER = "ctrl_shift_f_handler";
	
	public static final String KEY_TOOLTIP_HANDLER = "tooltip_handler";



	private Service actionBuilder;
	private Service toggleBuilder;
	private Service replaceText;
	private Service findInfos;
	private Service buildGitAction;
	private Service buildHandler;


	public EntityImpl() throws Exception
	{
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		toggleBuilder = Outside.service(this,"gus06.swing.button.toggleaction.builder0");
		replaceText = Outside.service(this,"*gus06.appli.gusexplorer.gui.editor.fillbar.replacetext");
		findInfos = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.findinfos");
		buildGitAction = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.git");
		buildHandler = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.t2.build");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JToolBar bar = (JToolBar) o[0];
		Object editor = o[1];
		
		bar.removeAll();
		
		if(!(editor instanceof R)) return;
		R editor_r = (R) editor;
		
		Object editor1 = r_(editor_r,"editor");
		if(editor1==null) return;
		if(!(editor1 instanceof R)) return;
		
		R editor1_r = (R) editor1;
		
		File file = (File) r_(editor1_r,"file");
		if(file==null) throw new Exception("File not found for editor: "+editor1.getClass());
		
		JComponent comp = (JComponent) r_(editor1_r,"comp");
		if(comp==null) throw new Exception("Comp not found for editor: "+editor1.getClass());
		
		putToComp(comp, KEY_F1_HANDLER, null);
		putToComp(comp, KEY_F2_HANDLER, null);
		putToComp(comp, KEY_F3_HANDLER, null);
		
		putToComp(comp, KEY_CTRL_B_HANDLER, null);
		putToComp(comp, KEY_CTRL_C_HANDLER, null);
		putToComp(comp, KEY_CTRL_D_HANDLER, null);
		putToComp(comp, KEY_CTRL_E_HANDLER, null);
		putToComp(comp, KEY_CTRL_F_HANDLER, null);
		putToComp(comp, KEY_CTRL_L_HANDLER, null);
		putToComp(comp, KEY_CTRL_V_HANDLER, null);
		putToComp(comp, KEY_CTRL_X_HANDLER, null);
		
		putToComp(comp, KEY_CTRL_SHIFT_C_HANDLER, null);
		putToComp(comp, KEY_CTRL_SHIFT_F_HANDLER, null);
		
		putToComp(comp, KEY_TOOLTIP_HANDLER, null);
		
		
		List infos = (List) findInfos.t(file);
		
		Action gitAction = (Action) buildGitAction.t(new Object[]{comp, file});
		if(gitAction!=null)
		{
			bar.add(gitAction);
			bar.addSeparator();
		}
		
		for(int i=0;i<infos.size();i++)
		{
			String[] info = (String[]) infos.get(i);
			String type = info[0];
			
			if(type.equals("separator"))
			{
				bar.addSeparator();
			}
			else if(type.equals("action"))
			{
				String display = info[1];
				String key = info[2];
				
				E execute = new Execute1(comp, key);
				Action action = (Action) actionBuilder.t(new Object[]{display,execute});
				bar.add(action);
			}
			else if(type.equals("toggle"))
			{
				String display = info[1];
				String key = info[2];
				
				E executeOn = new Execute1(comp, key+"_on");
				E executeOff = new Execute1(comp, key+"_off");
				
				JComponent action = (JComponent) toggleBuilder.t(new Object[]{display,executeOn,executeOff});
				bar.add(action);
			}
			
			
			else if(type.equals("F1"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_F1_HANDLER, p);
			}
			else if(type.equals("F2"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_F2_HANDLER, p);
			}
			else if(type.equals("F3"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_F3_HANDLER, p);
			}
			else if(type.equals("ctrl_b"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_B_HANDLER, p);
			}
			else if(type.equals("ctrl_c"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_C_HANDLER, p);
			}
			else if(type.equals("ctrl_d"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_D_HANDLER, p);
			}
			else if(type.equals("ctrl_e"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_E_HANDLER, p);
			}
			else if(type.equals("ctrl_f"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_F_HANDLER, p);
			}
			else if(type.equals("ctrl_l"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_L_HANDLER, p);
			}
			else if(type.equals("ctrl_v"))
			{
				String key = info[1];
				
				G g = (G) buildHandler.t(new Object[]{comp,"g:"+key});
				if(g==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_V_HANDLER, g);
			}
			else if(type.equals("ctrl_x"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_X_HANDLER, p);
			}
			else if(type.equals("ctrl_shift_c"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_SHIFT_C_HANDLER, p);
			}
			else if(type.equals("ctrl_shift_f"))
			{
				String key = info[1];
				
				P p = (P) buildHandler.t(new Object[]{comp,"p:"+key});
				if(p==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_CTRL_SHIFT_F_HANDLER, p);
			}
			else if(type.equals("tooltip"))
			{
				String key = info[1];
				
				T t = (T) buildHandler.t(new Object[]{comp,"t:"+key});
				if(t==null) throw new Exception("Invalid script key: "+key);
				putToComp(comp, KEY_TOOLTIP_HANDLER, t);
			}
		}
	}
	
	
	
	private Object r_(R r, String key)
	{
		if(r==null) return null;
		try{return r.r(key);}
		catch(Exception e){}
		return null;
	}
	
	
	
	private class Execute1 implements E, Runnable
	{
		private JComponent comp;
		private String key;
		
		public Execute1(JComponent comp, String key)
		{
			this.comp = comp;
			this.key = key;
		}
		
		public void e() throws Exception
		{SwingUtilities.invokeLater(this);}
		
		public void run()
		{
			try{replaceText.p(new Object[]{comp,key});}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"run()",e);}
		}
	}
	
	
	private void putToComp(JComponent comp, String key, Object obj) throws Exception
	{
		if(!(comp instanceof R)) return;
		Map data = (Map) ((R) comp).r("data");
		data.put(key,obj);
	}
}