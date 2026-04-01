package a.entity.gus06.y.images1.bufferedimage.create;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	private Service toDim;

	public EntityImpl() throws Exception
	{
		toDim = Outside.service(this, "gus.y.find1.dimension");
	}

	public Object t(Object obj) throws Exception
	{
		Dimension dim = (Dimension) toDim.t(obj);
		return graphicsConf().createCompatibleImage(dim.width, dim.height, Transparency.BITMASK);
	}

	private GraphicsEnvironment graphicsEnv()
	{return GraphicsEnvironment.getLocalGraphicsEnvironment();}

	private GraphicsDevice graphicsDevice()
	{return graphicsEnv().getDefaultScreenDevice();}

	private GraphicsConfiguration graphicsConf()
	{return graphicsDevice().getDefaultConfiguration();}
}