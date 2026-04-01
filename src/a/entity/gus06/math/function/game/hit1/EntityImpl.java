package a.entity.gus06.math.function.game.hit1;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20170917";}
	
	
	public double h(double value) throws Exception
	{
		int power = (int) value;
	    	double x = Math.random();
	
	    	if(power>0)
	        for(int i=0;i<power;i++)
	        {
	            double r = Math.random();
	            if(r>x) x = r;
	        }
	    	else if(power<0)
	    	for(int i=power;i<0;i++)
	    	{
	    		double r = Math.random();
	    		if(r<x) x = r;
	    	}
	    	return x;
	}
}
