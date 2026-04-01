package a.entity.gus06.entitydev.pseudo.find;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, G, ActionListener {

	public String creationDate() {return "20140805";}

	public static final String KEY_PSEUDO = "dev.pseudo";

	private Map prop;
	private String pseudo;
	

	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		((S) prop).addActionListener(this);
		pseudo = get(KEY_PSEUDO);
	}
	
	
	public Object g() throws Exception
	{return pseudo;}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{update();}
	
	
	private void update()
	{
		String newPseudo = get(KEY_PSEUDO);
		if(equals(newPseudo,pseudo)) return;
		pseudo = newPseudo;
		updated();
	}
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
	
	private String get(String key)
	{
		if(!prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
	
	private void updated()
	{send(this,"updated()");}
}
