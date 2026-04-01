package a.entity.gus06.env.windows.dll.findtype;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150615";}
	
	public static final int MZ_MAGIC = 0x5A4D;
	public static final int PE_SIGNATURE = 0x00004550;
	
	public static final int IMAGE_FILE_MACHINE_UNKNOWN		= 0x0000; //The contents of this field are assumed to be applicable to any machine type
	public static final int IMAGE_FILE_MACHINE_AM33			= 0x01D3; //Matsushita AM33
	public static final int IMAGE_FILE_MACHINE_AMD64		= 0x8664; //x64
	public static final int IMAGE_FILE_MACHINE_ARM			= 0x01C0; //ARM little endian
	public static final int IMAGE_FILE_MACHINE_ARMV7		= 0x01C4; //ARMv7 (or higher) Thumb mode only
	public static final int IMAGE_FILE_MACHINE_EBC			= 0x0EBC; //EFI byte code
	public static final int IMAGE_FILE_MACHINE_I386			= 0x014C; //Intel 386 or later processors and compatible processors
	public static final int IMAGE_FILE_MACHINE_IA64			= 0x0200; //Intel Itanium processor family
	public static final int IMAGE_FILE_MACHINE_M32R			= 0x9041; //Mitsubishi M32R little endian
	public static final int IMAGE_FILE_MACHINE_MIPS16		= 0x0266; //MIPS16
	public static final int IMAGE_FILE_MACHINE_MIPSFPU		= 0x0366; //MIPS with FPU
	public static final int IMAGE_FILE_MACHINE_MIPSFPU16	= 0x0466; //MIPS16 with FPU
	public static final int IMAGE_FILE_MACHINE_POWERPC		= 0x01F0; //Power PC little endian
	public static final int IMAGE_FILE_MACHINE_POWERPCFP	= 0x01F1; //Power PC with floating point support
	public static final int IMAGE_FILE_MACHINE_R4000		= 0x0166; //MIPS little endian
	public static final int IMAGE_FILE_MACHINE_SH3			= 0x01A2; //Hitachi SH3
	public static final int IMAGE_FILE_MACHINE_SH3DSP		= 0x01A3; //Hitachi SH3 DSP
	public static final int IMAGE_FILE_MACHINE_SH4			= 0x01A6; //Hitachi SH4
	public static final int IMAGE_FILE_MACHINE_SH5			= 0x01A8; //Hitachi SH5
	public static final int IMAGE_FILE_MACHINE_THUMB		= 0x01C2; //ARM or Thumb ("interworking")
	public static final int IMAGE_FILE_MACHINE_WCEMIPSV2	= 0x0169; //MIPS little-endian WCE v2
	
	

	public Object t(Object obj) throws Exception
	{
		InputStream is = toInputStream(obj);
		DataInputStream in = new DataInputStream(is);
		
		String type = extractType(in);
		in.close();
		is.close();
		
		return type;
	}

	
	private String extractType(DataInputStream in) throws Exception
	{
		int mz_magic = _unsigned_(in);
		if(mz_magic!=MZ_MAGIC) throw new Exception("Invalid MZ magic number : "+mz_magic);
		// pos: 2
		
		in.skipBytes(58);
		// pos: 60
		
		int e_lfanew = _int_(in);
		if(e_lfanew<=64) throw new Exception("Invalid e_lfanew value : "+e_lfanew);
		
		in.skipBytes(e_lfanew-64);
		// pos: 'e_lfanew'
		
		int pe_signature = _int_(in);
		if(pe_signature!=PE_SIGNATURE) throw new Exception("Invalid PE signature : "+pe_signature);
		// pos: 'e_lfanew'+4
		
		int image_file_machine = _unsigned_(in);
		
		switch(image_file_machine)
		{
			case IMAGE_FILE_MACHINE_AMD64:return "64";
			case IMAGE_FILE_MACHINE_I386:return "32";
			default:return "?";
		}
	}
	
	
	
	private int _int_(DataInputStream in) throws Exception
	{
		byte[] b = new byte[4];
		in.readFully(b,0,4);
		return (b[3]) << 24 | (b[2] & 0xFF) << 16 | (b[1] & 0xFF) << 8 | (b[0] & 0xFF);
	}
	
	private int _unsigned_(DataInputStream in) throws Exception
	{
		byte[] b = new byte[2];
		in.readFully(b,0,2);
		return (b[1] & 0xFF) << 8 | (b[0] & 0xFF);
	}
	
	private InputStream toInputStream(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return (InputStream) obj;
		if(obj instanceof File) return new FileInputStream((File)obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}