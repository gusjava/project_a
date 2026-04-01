package a.entity.gus06.awt.bufferedimage.create;

import a.framework.*;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.Dimension;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140803";}


	private Service toDim;
	
	public EntityImpl() throws Exception
	{toDim = Outside.service(this,"gus06.find.dimension");}

	
	
	public Object t(Object obj) throws Exception
	{
		Dimension dim = (Dimension) toDim.t(obj);
		return graphicsConf().createCompatibleImage(dim.width,dim.height,Transparency.BITMASK);
	}
	
	
	private GraphicsEnvironment graphicsEnv()
	{return GraphicsEnvironment.getLocalGraphicsEnvironment();}
	
	private GraphicsDevice graphicsDevice()
	{return graphicsEnv().getDefaultScreenDevice();}
	
	private GraphicsConfiguration graphicsConf()
	{return graphicsDevice().getDefaultConfiguration();}
}
