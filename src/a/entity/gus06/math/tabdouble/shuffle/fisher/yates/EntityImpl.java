package a.entity.gus06.math.tabdouble.shuffle.fisher.yates;

import a.framework.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170106";}

	
	
	public void p(Object obj) throws Exception
	{
		double[] t = (double[]) obj;
		int len = t.length;
		
		Random rnd = ThreadLocalRandom.current();
		for(int i=len-1;i>0;i--)
		{
			int index = rnd.nextInt(i+1);
			double a = t[index];
			t[index] = t[i];
			t[i] = a;
		}
	}
}
