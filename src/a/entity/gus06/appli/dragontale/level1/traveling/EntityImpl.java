package a.entity.gus06.appli.dragontale.level1.traveling;

import a.framework.*;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, E, P, R {

	public String creationDate() {return "20200518";}
	
	private Service resourceLoader;
	private Service mvtManager;

	private BufferedImage bg;
	private double offset = 0;
	
	public EntityImpl() throws Exception
	{
		resourceLoader = Outside.service(this,"gus06.appli.dragontale.resource.loader");
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
		bg = (BufferedImage) resourceLoader.r("img-background1");
	}
	
	public void e() throws Exception
	{
		double[] pos = (double[]) mvtManager.r("position.player");
		
		offset += 0.2;
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("reset")) {reset();return;}
		throw new Exception("Unknown command: "+s);
	}
	
	
	private void reset() throws Exception
	{
		offset = 0;
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("offset")) return offset;
		if(key.equals("keys")) return new String[]{"offset"};
		throw new Exception("Unknown key: "+key);
	}
}
