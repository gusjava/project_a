package a.entity.gus06.maincust.entity.generator1.mapwatcher;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;


public class EntityImpl extends S1 implements Entity, G, ActionListener {

	public String creationDate() {return "20140810";}


	private Map classMap;
	private String lastKey;
	

	public EntityImpl() throws Exception
	{
		classMap = (Map) Outside.resource(this,"classmap");
		((S) classMap).addActionListener(this);
	}
	
	public Object g() throws Exception
	{return lastKey;}
	
	
		
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(!s.equals("put(Object,Object)")) return;
		
		lastKey = findLastKey();
		classChanged();
	}
	
	
	
	private String findLastKey()
	{
		try{return (String) ((R) classMap).r("lastKey");}
		catch(Exception e) {Outside.err(this,"findLastKey()",e);}
		return "";
	}
	
	
	private void classChanged()
	{send(this,"classChanged()");}
}
