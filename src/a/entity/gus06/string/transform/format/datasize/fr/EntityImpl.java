package a.entity.gus06.string.transform.format.datasize.fr;

import a.framework.*;
import java.text.NumberFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141106";}


	public static final String U_Tb = " To";
	public static final String U_Gb = " Go";
	public static final String U_Mb = " Mo";
	public static final String U_Kb = " Ko";
	public static final String U_b = " octets";
	
	

	public static NumberFormat nf = NumberFormat.getInstance();
	
	public static final long Tb = 1000000000000L;
	public static final long Gb = 1000000000L;
	public static final long Mb = 1000000L;
	public static final long Kb = 1000L;
	
	
	public Object t(Object obj) throws Exception
	{
		long size = toLong(obj);
		if(size >= Tb) return formatSize_Tb(size);
		if(size >= Gb) return formatSize_Gb(size);
		if(size >= Mb) return formatSize_Mb(size);
		if(size >= Kb) return formatSize_Kb(size);
		return formatSize_b(size);
	}
	
	
	
	private long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof Integer) return ((Integer) obj).longValue();
		if(obj instanceof String) return Long.parseLong((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String formatSize_Tb(long size)
	{
		long sizeTb = size/Tb;
		String sizeTb_ = nf.format(sizeTb);
		if(sizeTb>=10) return sizeTb_+U_Tb;
		
		int round = (int) ((size-sizeTb*Tb)/(Gb*100));
		if(round==0) return sizeTb_+U_Tb;
		return sizeTb_+","+round+U_Tb;
	}
	
	private String formatSize_Gb(long size)
	{
		long sizeGb = size/Gb;
		String sizeGb_ = nf.format(sizeGb);
		if(sizeGb>=10) return sizeGb_+U_Gb;
		
		int round = (int) ((size-sizeGb*Gb)/(Mb*100));
		if(round==0) return sizeGb_+U_Gb;
		return sizeGb_+","+round+U_Gb;
	}
	
	private String formatSize_Mb(long size)
	{
		long sizeMb = size/Mb;
		String sizeMb_ = nf.format(sizeMb);
		if(sizeMb>=10) return sizeMb_+U_Mb;
		
		int round = (int) ((size-sizeMb*Mb)/(Kb*100));
		if(round==0) return sizeMb_+U_Mb;
		return sizeMb_+","+round+U_Mb;
	}
	
	private String formatSize_Kb(long size)
	{
		long sizeKb = size/Kb;
		String sizeKb_ = nf.format(sizeKb);
		if(sizeKb>=10) return sizeKb_+U_Kb;
		
		int round = (int) ((size-sizeKb*Kb)/100);
		if(round==0) return sizeKb_+U_Kb;
		return sizeKb_+","+round+U_Kb;
	}
	
	private String formatSize_b(long size)
	{
		return nf.format(size)+U_b;
	}
}
