package a.entity.gus06.y.kanjivg1.build.partial1;

import a.framework.*;
import java.awt.Shape;
import java.awt.geom.PathIterator;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250802";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Shape stroke = (Shape) o[0];
		float progress = ((Float) o[1]).floatValue();
		
		PathIterator it = stroke.getPathIterator(null, 0.5);
		List<Line2D.Float> segments = new ArrayList<>();
		Point2D.Float last = null;
		float totalLength = 0f;

		float[] coords = new float[6];
		while (!it.isDone())
		{
			int type = it.currentSegment(coords);
			switch (type) {
				case PathIterator.SEG_MOVETO:
					last = new Point2D.Float(coords[0], coords[1]);
					break;
				case PathIterator.SEG_LINETO:
					Point2D.Float current = new Point2D.Float(coords[0], coords[1]);
					Line2D.Float seg = new Line2D.Float(last, current);
					segments.add(seg);
					totalLength += (float) seg.getP1().distance(seg.getP2());
					last = current;
					break;
				case PathIterator.SEG_CLOSE:
					break;
				default:
					// Ignore courbes pour le moment
			}
			it.next();
		}

		// Construction du sous-chemin jusqu'� la bonne longueur
		float targetLength = progress * totalLength;
		float accumulated = 0f;
		Path2D.Float partial = new Path2D.Float();
		boolean started = false;

		for (Line2D.Float seg : segments)
		{
			float segLength = (float) seg.getP1().distance(seg.getP2());
			if (accumulated + segLength <= targetLength)
			{
				if (!started) {
					partial.moveTo(seg.x1, seg.y1);
					started = true;
				}
				partial.lineTo(seg.x2, seg.y2);
				accumulated += segLength;
			}
			else
			{
				float remain = targetLength - accumulated;
				float ratio = remain / segLength;
				float x = seg.x1 + (seg.x2 - seg.x1) * ratio;
				float y = seg.y1 + (seg.y2 - seg.y1) * ratio;
				if (!started) partial.moveTo(seg.x1, seg.y1);
				
				partial.lineTo(x, y);
				break;
			}
		}
		return partial;
	}
}