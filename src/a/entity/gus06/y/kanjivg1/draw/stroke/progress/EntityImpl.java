package a.entity.gus06.y.kanjivg1.draw.stroke.progress;

import a.framework.*;
import java.awt.Graphics2D;
import java.awt.Shape;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250802";}

	private Service buildPartial;

	public EntityImpl() throws Exception
	{
		buildPartial = Outside.service(this, "gus.y.kanjivg1.build.partial2");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Graphics2D g2 = (Graphics2D) o[0];
		Shape stroke = (Shape) o[1];
		float progress = ((Float) o[2]).floatValue();
		
		if(progress <= 0f) return;
		if(progress >= 1f) {g2.draw(stroke);return;}

		Shape partial = (Shape) buildPartial.t(new Object[]{stroke, progress});
		g2.draw(partial);
	}
}