package a.entity.gus06.convert.gtoh;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190624";}


	public Object t(Object obj) throws Exception
	{return new H1((G) obj);}
	
	
	
	private class H1 implements H
	{
		private G g;
		public H1(G g) {this.g = g;}
		
		public double h(double value) throws Exception
		{
			Number n = (Number) g.g();
			return n.doubleValue();
		}
	}
}
