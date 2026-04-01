package a.entity.gus06.sys.gameengine1.producer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, R, P, E {

	public String creationDate() {return "20200515";}


	private Service initSize;
	private Service newImage;
	
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage img;
	
	private List handlers;
	

	
	
	public EntityImpl() throws Exception
	{
		initSize = Outside.service(this,"gus06.sys.gameengine1.producer.initsize");
		newImage = Outside.service(this,"gus06.awt.bufferedimage.build.empty2");
		
		Object size = initSize.g();
		image1 = newImage(size);
		image2 = newImage(size);
		img = image1;
		
	   	handlers = new ArrayList();
	}
	
	
	
	private BufferedImage newImage(Object size) throws Exception
	{return (BufferedImage) newImage.t(size);}

	


	
	
	public Object g() throws Exception
	{
		img = img==image1?image2:image1;
		
		for(int i=0;i<handlers.size();i++)
		{
			P p = (P) handlers.get(i);
			p.p(img);
		}
		
		produced();
		return img;
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{handlers.add(obj);}
	
	
	
	public void e() throws Exception
	{handlers.clear();}
	
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("image")) return img;
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void produced()
	{send(this,"produced");}
}
