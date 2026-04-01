package a.entity.gus06.file.video.duration.rebuild;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200107";}
	
	public static final int DEFAULT_POS = 10;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer duration = (Integer) o[0];
		Object posObj = o[1];
		
		return computePos(duration,posObj);
	}
	
	
	
	private int computePos(int duration, Object posObj) throws Exception
	{
		if(posObj==null) return DEFAULT_POS;
		if(posObj instanceof Integer) return computePosAsInt((int) posObj,duration);
		if(posObj instanceof Double) return computePosAsDouble((double) posObj,duration);
		
		throw new Exception("Invalid posObj type: "+posObj.getClass().getName());
	}
	
	private int computePosAsInt(int pos, int duration) throws Exception
	{
		if(pos<0 || pos>duration) throw new Exception("Invalid position: "+pos);
		return pos;
	}
	
	private int computePosAsDouble(double pos, int duration) throws Exception
	{
		if(pos<0 || pos>1) throw new Exception("Invalid position: "+pos);
		return (int) (pos*duration);
	}
}
