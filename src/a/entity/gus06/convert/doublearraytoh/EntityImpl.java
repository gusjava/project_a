package a.entity.gus06.convert.doublearraytoh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180412";}

	
	public Object t(Object obj) throws Exception
	{
		return new H1((double[]) obj);
	}
	
	private class H1 implements H
	{
		private double[] array;
		public H1(double[] array) {this.array = array;}
		
		public double h(double value) throws Exception
		{
			int index = (int) value;
			if(index<0 || index>=array.length) throw new Exception("Invalid input value: "+value);
			return array[index];
		}
	}
}
