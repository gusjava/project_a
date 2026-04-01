package a.entity.gus06.time.clock.gui.analogclock1.needleangles;

import java.text.SimpleDateFormat;
import java.util.Date;
import a.framework.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20160605";}


	private SimpleDateFormat KK = new SimpleDateFormat("KK");
	private SimpleDateFormat mm = new SimpleDateFormat("mm");
	private SimpleDateFormat ss = new SimpleDateFormat("ss");



	public Object g() throws Exception
	{return computeAngles(new Date());}



	public Object t(Object obj) throws Exception
	{return computeAngles((Date)obj);}


	
	private double[] computeAngles(Date date) throws Exception
	{
		double hours = find(KK,date);
		double minutes = find(mm,date);
		double secondes = find(ss,date);
		
		double secondes_inside_min = secondes;
		double secondes_inside_hour = 60*minutes+secondes;
		double secondes_inside_halfday = 3600*hours+secondes_inside_hour;
		
		double[] d = new double[3];
		d[0] = secondes_inside_halfday/21600.0*Math.PI;
		d[1] = secondes_inside_hour/1800.0*Math.PI;
		d[2] = secondes_inside_min/30.0*Math.PI;
		return d;
	}
	
	
	private double find(SimpleDateFormat sdf, Date date) throws Exception
	{
		String v = sdf.format(date);
		return Double.parseDouble(v);
	}
}
