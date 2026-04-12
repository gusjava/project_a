package a.entity.gus06.appli.mosaique.engine;

import a.framework.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class EntityImpl extends S1 implements Entity, R, V, E, F, Runnable {

	public String creationDate() {return "20141115";}


	private Service buildWhite;
	private Service buildDarken;


	private BufferedImage input;
	private BufferedImage output;
	
	private BufferedImage image1;
	private BufferedImage image2;
	
	private T trans;
	private int number;
	
	private Thread t;
	private int[] progress;


	

	public EntityImpl() throws Exception
	{
		buildWhite = Outside.service(this,"gus06.awt.bufferedimage.create.white");
		buildDarken = Outside.service(this,"gus06.awt.bufferedimage.transform.color.darken");
	}
	
	
	public void e() throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		if(trans==null) throw new Exception("Transform not initialized yet");
		if(input==null) throw new Exception("Input not initialized yet");
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public boolean f(Object obj) throws Exception
	{return t!=null && t.isAlive();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("input")) return input;
		if(key.equals("output")) return output;
		if(key.equals("number")) return ""+number;
		
		if(key.equals("image1")) return image1;
		if(key.equals("image2")) return image2;
		
		if(key.equals("progress")) return progress;
		
		if(key.equals("keys")) return new String[]{
			"input","output","number","image1","image2","progress"
		};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("input")) {input = (BufferedImage) obj;return;}
		if(key.equals("trans")) {trans = (T) obj;return;}
		if(key.equals("number")) {number = Integer.parseInt((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void run()
	{
		try{perform();}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	private void perform() throws Exception
	{
		image1 = (BufferedImage) buildDarken.t(input);
		image2 = (BufferedImage) buildWhite.t(input);
		
		updated();
		
		Graphics graphics1 = image1.getGraphics();
		Graphics graphics2 = image2.getGraphics();
		
		int count = number*number;
		
		int image_w = image1.getWidth();
		int image_h = image1.getHeight();
		
		int x = 0;
		int y = 0;
		
		int w1 = image_w/number;
		int h1 = image_h/number;

		for(int i=0;i<count;i++)
		{
			int w = Math.min(w1,image_w-x);
			int h = Math.min(h1,image_h-y);
			
			BufferedImage img1 = input.getSubimage(x,y,w,h);
			BufferedImage img2 = (BufferedImage) trans.t(img1);
			
			graphics1.drawImage(img1,x,y,null);
			graphics2.drawImage(img2,x,y,w,h,null);
			
			progress = new int[]{i+1,count};
			updated();
			
			x += w;
			if((i+1)%number == 0) {x = 0;y += h;}
		}
	}
	
	
	
	private void updated()
	{send(this,"updated()");}
}
