package a.entity.gus06.sys.listtabviewer1.build.annexe.list;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200405";}


	public static final String KEY_KEY2 = "key2";
	public static final String KEY_MAPPER2 = "mapper2";


	private Service buildMapFromKey;
	private Service buildMapFromMapper;
	private Service factoryComp;
	private Service buildAnnexeItem;
	private Service tryAndFlatten;
	private Service factory;



	public EntityImpl() throws Exception
	{
		buildMapFromKey = Outside.service(this,"gus06.sys.listtabviewer1.data.buildmap.fromkey");
		buildMapFromMapper = Outside.service(this,"gus06.sys.listtabviewer1.data.buildmap.frommapper");
		factoryComp = Outside.service(this,"factory#gus06.sys.listchooser1.gui.main");
		buildAnnexeItem = Outside.service(this,"gus06.sys.listtabviewer1.build.annexe.item");
		tryAndFlatten = Outside.service(this,"gus06.map.perform.tryandflattenvalues");
		factory = Outside.service(this,"factory#gus06.data.viewer.object");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((Map) obj);
	}
	
	
	
	private class Holder implements P, I
	{
		private Map conf;
		
		private List data;
		private Map map;
		private Object holder;
		private Object annexe;
		
		
		public Holder(Map conf) throws Exception
		{
			this.conf = conf;
			holder = factoryComp.g();
		}
		
		
		public Object i() throws Exception
		{
			return ((I)holder).i();
		}
		
		public void p(Object obj) throws Exception
		{
			data = (List) obj;
			if(data==null) data = new ArrayList();
			
			map = buildMap();
			Map map1 = (Map) tryAndFlatten.t(map);
			if(map1!=null)
			{
				map = map1;
				annexe = buildAnnexeItem.t(conf);
			}
			else
			{
				annexe = factory.g();
			}
			
			((V)holder).v("annexe",annexe);
			((P)holder).p(map);
		}
		
		
		private Map buildMap() throws Exception
		{
			if(conf.containsKey(KEY_MAPPER2))
			{
				T mapper = (T) conf.get(KEY_MAPPER2);
				return (Map) buildMapFromMapper.t(new Object[]{data,mapper});
			}
			if(conf.containsKey(KEY_KEY2))
			{
				String key = (String) conf.get(KEY_KEY2);
				return (Map) buildMapFromKey.t(new Object[]{data,key});
			}
			throw new Exception("Tab key2 not found inside conf");
		}
	}
}
