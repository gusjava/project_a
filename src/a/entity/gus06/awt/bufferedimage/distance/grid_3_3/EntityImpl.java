package a.entity.gus06.awt.bufferedimage.distance.grid_3_3;

import a.framework.*;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141122";}


	private Service grid_3_3;
	private Service distance;


	public EntityImpl() throws Exception
	{
		grid_3_3 = Outside.service(this,"gus06.awt.bufferedimage.split.grid_3_3.cache");
		distance = Outside.service(this,"gus06.awt.bufferedimage.distance.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		BufferedImage[] img = (BufferedImage[]) obj;
		if(img.length!=2) throw new Exception("Wrong data number: "+img.length);
		
		BufferedImage[] m1 = (BufferedImage[]) grid_3_3.t(img[0]);
		BufferedImage[] m2 = (BufferedImage[]) grid_3_3.t(img[1]);
		
		long sum = 0;
    		for(int i=0;i<m1.length;i++)
    		sum += distance(m1[i],m2[i]).doubleValue();

		return Double.valueOf(sum);
	}
	
	
	
	private Double distance(BufferedImage m1, BufferedImage m2) throws Exception
	{return (Double) distance.t(new BufferedImage[]{m1,m2});}
}
