package a.core.gus.gyem.m054.e.started;

import java.awt.event.ActionListener;
import java.util.List;

import a.core.gus.gyem.GyemSystem;
import a.framework.E;
import a.framework.S;
import a.framework.S1;

public class Module extends GyemSystem implements E, S {
	
	public static final String EVENT = "started";
	
	private S1 s1 = new S1();
	
	public void e() throws Exception
	{s1.send(this,EVENT);}

	public void addActionListener(ActionListener listener) throws Exception
	{s1.addActionListener(listener);}

	public void removeActionListener(ActionListener listener) throws Exception
	{s1.removeActionListener(listener);}

	public List listeners() throws Exception
	{return s1.listeners();}
}
