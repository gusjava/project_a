package a.entity.gus06.sys.drawingpanel1.screen;

import a.framework.*;

public class EntityImpl implements Entity, P, I, V, R {

	public String creationDate() {return "20170425";}


	private Service screen;
	private Service drawer;
	private Service mouse;
	
	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.drawn");
		drawer = Outside.service(this,"*gus06.sys.drawingpanel1.drawer");
		mouse = Outside.service(this,"*gus06.sys.drawingpanel1.mouse");
		
		mouse.v("comp",screen.i());
	}
	
	
	public Object i() throws Exception
	{return screen.i();}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("foreground")) {initForeground(obj);return;}
		if(key.equals("background")) {initBackground(obj);return;}
		if(key.equals("dimension")) {initDimension(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("mouse")) return mouse.g();
		if(key.equals("keys")) return new String[]{"mouse"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void p(Object obj) throws Exception
	{
		drawer.v("data",obj);
		screen.p(drawer);
	}
	
	
	
	private void initForeground(Object obj) throws Exception
	{
		drawer.v("foreground",obj);
	}
	
	private void initBackground(Object obj) throws Exception
	{
		drawer.v("background",obj);
	}
	
	private void initDimension(Object obj) throws Exception
	{
		drawer.v("dimension",obj);
		mouse.v("dimension",obj);
	}
	
}
