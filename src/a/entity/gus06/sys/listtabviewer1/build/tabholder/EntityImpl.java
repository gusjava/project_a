package a.entity.gus06.sys.listtabviewer1.build.tabholder;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}
	
	public static final String KEY_KEY = "key";
	public static final String KEY_MAPPER = "mapper";
	
	public static final String KEY_TYPED_F1 = "typed_F1";
	public static final String KEY_TYPED_F2 = "typed_F2";
	public static final String KEY_TYPED_F3 = "typed_F3";
	public static final String KEY_TYPED_F4 = "typed_F4";
	public static final String KEY_TYPED_F5 = "typed_F5";
	public static final String KEY_TYPED_F6 = "typed_F6";
	public static final String KEY_TYPED_F7 = "typed_F7";
	public static final String KEY_TYPED_F8 = "typed_F8";
	public static final String KEY_TYPED_F9 = "typed_F9";
	public static final String KEY_TYPED_F10 = "typed_F10";
	public static final String KEY_TYPED_F11 = "typed_F11";
	public static final String KEY_TYPED_F12 = "typed_F12";
	public static final String KEY_TYPED_SPACE = "typed_space";
	public static final String KEY_TYPED_DELETE = "typed_delete";
	public static final String KEY_TYPED_ENTER = "typed_enter";
	public static final String KEY_TYPED_ESCAPE = "typed_escape";




	private Service buildMapFromKey;
	private Service buildMapFromMapper;
	private Service factoryComp;
	private Service buildAnnexeList;
	private Service buildAnnexeItem;
	private Service tryAndFlatten;



	public EntityImpl() throws Exception
	{
		buildMapFromKey = Outside.service(this,"gus06.sys.listtabviewer1.data.buildmap.fromkey");
		buildMapFromMapper = Outside.service(this,"gus06.sys.listtabviewer1.data.buildmap.frommapper");
		factoryComp = Outside.service(this,"factory#gus06.sys.listchooser1.gui.main");
		buildAnnexeList = Outside.service(this,"gus06.sys.listtabviewer1.build.annexe.list");
		buildAnnexeItem = Outside.service(this,"gus06.sys.listtabviewer1.build.annexe.item");
		tryAndFlatten = Outside.service(this,"gus06.map.perform.tryandflattenvalues");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((Map) obj);
	}
	
	
	
	private class Holder implements P, I, ActionListener
	{
		private Map conf;
		
		private List data;
		private Object holder;
		
		private P typedF1;
		private P typedF2;
		private P typedF3;
		private P typedF4;
		private P typedF5;
		private P typedF6;
		private P typedF7;
		private P typedF8;
		private P typedF9;
		private P typedF10;
		private P typedF11;
		private P typedF12;
		private P typedSpace;
		private P typedDelete;
		private P typedEnter;
		private P typedEscape;
		
		
		public Holder(Map conf) throws Exception
		{
			this.conf = conf;
			holder = factoryComp.g();
			
			typedF1 = (P) getConf(KEY_TYPED_F1);
			typedF2 = (P) getConf(KEY_TYPED_F2);
			typedF3 = (P) getConf(KEY_TYPED_F3);
			typedF4 = (P) getConf(KEY_TYPED_F4);
			typedF5 = (P) getConf(KEY_TYPED_F5);
			typedF6 = (P) getConf(KEY_TYPED_F6);
			typedF7 = (P) getConf(KEY_TYPED_F7);
			typedF8 = (P) getConf(KEY_TYPED_F8);
			typedF9 = (P) getConf(KEY_TYPED_F9);
			typedF10 = (P) getConf(KEY_TYPED_F10);
			typedF11 = (P) getConf(KEY_TYPED_F11);
			typedF12 = (P) getConf(KEY_TYPED_F12);
			typedSpace = (P) getConf(KEY_TYPED_SPACE);
			typedDelete = (P) getConf(KEY_TYPED_DELETE);
			typedEnter = (P) getConf(KEY_TYPED_ENTER);
			typedEscape = (P) getConf(KEY_TYPED_ESCAPE);
			
			((S)holder).addActionListener(this);
		}
		
		public Object i() throws Exception
		{
			return ((I)holder).i();
		}
		
		public void p(Object obj) throws Exception
		{
			data = (List) obj;
			
			Map map = buildMap();
			Map map1 = (Map) tryAndFlatten.t(map);
			
			if(map1!=null) 
			{
				Object annexeItem = buildAnnexeItem.t(conf);
				((V)holder).v("annexe",annexeItem);
				((P)holder).p(map1);
			}
			else
			{
				Object annexeList = buildAnnexeList.t(conf);
				((V)holder).v("annexe",annexeList);
				((P)holder).p(map);
			}
		}
		
		
		private Map buildMap() throws Exception
		{
			if(conf.containsKey(KEY_MAPPER))
			{
				T mapper = (T) conf.get(KEY_MAPPER);
				return (Map) buildMapFromMapper.t(new Object[]{data,mapper});
			}
			if(conf.containsKey(KEY_KEY))
			{
				String key = (String) conf.get(KEY_KEY);
				return (Map) buildMapFromKey.t(new Object[]{data,key});
			}
			throw new Exception("Tab key not found inside conf");
		}
		
		
		
		public void actionPerformed(ActionEvent e)
		{
			String s = e.getActionCommand();
			
			if(s.equals("typed_F1()")) {handleTyped(typedF1);return;}
			if(s.equals("typed_F2()")) {handleTyped(typedF2);return;}
			if(s.equals("typed_F3()")) {handleTyped(typedF3);return;}
			if(s.equals("typed_F4()")) {handleTyped(typedF4);return;}
			if(s.equals("typed_F5()")) {handleTyped(typedF5);return;}
			if(s.equals("typed_F6()")) {handleTyped(typedF6);return;}
			if(s.equals("typed_F7()")) {handleTyped(typedF7);return;}
			if(s.equals("typed_F8()")) {handleTyped(typedF8);return;}
			if(s.equals("typed_F9()")) {handleTyped(typedF9);return;}
			if(s.equals("typed_F10()")) {handleTyped(typedF10);return;}
			if(s.equals("typed_F11()")) {handleTyped(typedF11);return;}
			if(s.equals("typed_F12()")) {handleTyped(typedF12);return;}
			if(s.equals("typed_space()")) {handleTyped(typedSpace);return;}
			if(s.equals("typed_delete()")) {handleTyped(typedDelete);return;}
			if(s.equals("typed_enter()"))  {handleTyped(typedEnter);return;}
			if(s.equals("typed_escape()")) {handleTyped(typedEscape);return;}
		}
		
		
		private Object getConf(String key)
		{
			if(!conf.containsKey(key)) return null;
			return conf.get(key);
		}
		
		
		private void handleTyped(P handler)
		{
			try
			{
				if(handler==null) return;
				Object selected = ((G)holder).g();
				handler.p(selected);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleTyped(P)",e);}
		}

	}
}
