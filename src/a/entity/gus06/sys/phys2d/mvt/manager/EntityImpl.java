package a.entity.gus06.sys.phys2d.mvt.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, V, R, E, P {

	public String creationDate() {return "20200516";}

	
	public static final String KEY_POSITION0 = "position0";
	public static final String KEY_POSITION = "position";
	public static final String KEY_SPEED = "speed";
	public static final String KEY_ACC = "acc";
	
	

	private Map map_pos0;
	private Map map_pos;
	private Map map_speed;
	private Map map_acc;
	
	
	public EntityImpl() throws Exception
	{
		map_pos0 = new HashMap();
		map_pos = new HashMap();
		map_speed = new HashMap();
		map_acc = new HashMap();
	}

	
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
	
	private void put(Map map, String key, Object obj)
	{if(obj==null) map.remove(key); else map.put(key,obj);}
	
	
	
	
	private Object getPosition0(String id)
	{return get(map_pos0,id);}
	
	private Object getPosition(String id)
	{return get(map_pos,id);}
	
	private Object getSpeed(String id)
	{return get(map_speed,id);}
	
	private Object getAcc(String id)
	{return get(map_acc,id);}
	
	
	

	private void putPosition0(String id, Object value)
	{put(map_pos0,id,value);}
	
	private void putPosition(String id, Object value)
	{put(map_pos,id,value);}

	private void putSpeed(String id, Object value)
	{put(map_speed,id,value);}

	private void putAcc(String id, Object value)
	{put(map_acc,id,value);}
	

	


	public void v(String key, Object obj) throws Exception
	{
		if(key.startsWith(KEY_POSITION+"."))
			putPosition(key.substring(KEY_POSITION.length()+1),obj);
		
		else if(key.startsWith(KEY_SPEED+"."))
			putSpeed(key.substring(KEY_SPEED.length()+1),obj);
		
		else if(key.startsWith(KEY_ACC+"."))
			putAcc(key.substring(KEY_SPEED.length()+1),obj);
		
		else throw new Exception("Invalid key: "+key);
	}

	


	public Object r(String key) throws Exception
	{
		if(key.startsWith(KEY_POSITION0+"."))
			return getPosition0(key.substring(KEY_POSITION0.length()+1));
		
		if(key.startsWith(KEY_POSITION+"."))
			return getPosition(key.substring(KEY_POSITION.length()+1));
		
		if(key.startsWith(KEY_SPEED+"."))
			return getSpeed(key.substring(KEY_SPEED.length()+1));
		
		if(key.startsWith(KEY_ACC+"."))
			return getAcc(key.substring(KEY_SPEED.length()+1));
		
		throw new Exception("Invalid key: "+key);
	}

	
	


	public void e() throws Exception
	{
		ArrayList ids = new ArrayList(map_pos.keySet());
		for(int i=0;i<ids.size();i++)
		{
			String id = (String) ids.get(i);
			
			updateSpeed(id);
			updatePosition(id);
		}
		updated();
	}




	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("reset")) {reset();return;}
		throw new Exception("Unknown command: "+s);
	}
	
	
	private void reset()
	{
		map_pos0.clear();
		map_pos.clear();
		map_speed.clear();
		map_acc.clear();
	}
	
	
	
	private void updateSpeed(String id) throws Exception
	{putSpeed(id,computeSpeed(id));}
	
	
	private void updatePosition(String id) throws Exception
	{putPosition(id,computePosition(id));}
	
	
	
	
	
	
	
	private Object computeSpeed(String id) throws Exception
	{
		Object speed = getSpeed(id);
		Object acc = getAcc(id);

		if(acc==null) return speed;
		acc = checkProvide(acc);
		
		if(acc instanceof T)
			return ((T) acc).t(speed);
		
		if(acc instanceof double[])
		{
			double[] speed_ = (double[]) speed;
			double[] acc_ = (double[]) acc;
			
			double x1 = speed_[0]+acc_[0];
			double y1 = speed_[1]+acc_[1];
			
			return new double[]{x1,y1};
		}
		throw new Exception("Unsupported acc object type: "+acc.getClass().getName());	
	}
	
	
	
	
	
	
	
	
	private Object computePosition(String id) throws Exception
	{
		Object pos = getPosition(id);
		putPosition0(id,pos);
		
		Object speed = getSpeed(id);
		speed = checkProvide(speed);
		
		if(speed==null) return pos;
		
		if(speed instanceof T)
			return ((T) speed).t(pos);
		
		if(speed instanceof double[])
		{
			double[] pos_ = (double[]) pos;
			double[] speed_ = (double[]) speed;
			
			double x1 = pos_[0]+speed_[0];
			double y1 = pos_[1]+speed_[1];
			
			return new double[]{x1,y1};
		}
		throw new Exception("Unsupported speed object type: "+speed.getClass().getName());	
	}
	
	
	
	private Object checkProvide(Object obj) throws Exception
	{
		if(obj instanceof G)
			return ((G) obj).g();
		return obj;
	}
	

	
	private void updated()
	{send(this,"udated()");}
}
