package a.entity.gus06.map.holder1;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;

public class EntityImpl extends S1 implements Entity, T, G, F, P {

	public String creationDate() {return "20161117";}

	private Map map;
	
	
	public synchronized void p(Object obj) throws Exception
	{
		map = (Map) obj;
		modified();
	}
	
	public synchronized Object g() throws Exception
	{return map;}
	
	
	public synchronized boolean f(Object obj) throws Exception
	{return map!=null?map.containsKey(obj):false;}
	
	
	public synchronized Object t(Object obj) throws Exception
	{return f(obj)?map.get(obj):null;}
	
	
	private void modified()
	{send(this,"modified()");}
}
