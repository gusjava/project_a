package a.entity.gus06.file.lnk.extract.path;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LnkParser {

	
	private String target_file;
	private boolean isDirectory;
	private boolean isLocal;

	
	public boolean isDirectory()
	{return isDirectory;}
	
	public boolean isLocal()
	{return isLocal;}
	
	public String getTargetFile()
	{return target_file;}
	
	
	
	
	
	public LnkParser(File f) throws IOException
	{parse(f);}

	
	
	private void parse(File f) throws IOException
	{
		FileInputStream fis = new FileInputStream(f);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[256];
		
		int n;
		while((n = fis.read(buff)) != -1)
		{baos.write(buff,0,n);}
		
		fis.close();
		byte[] link = baos.toByteArray();
		baos.close();
		
		parse(link);
	}

	
	
	private void parse(byte[] link)
	{
		// get the flags byte
		byte flags = link[0x14];

		// get the file attributes byte
		final int file_atts_offset = 0x18;
		byte file_atts = link[file_atts_offset];
		byte is_dir_mask = (byte) 0x10;
		
		isDirectory = (file_atts & is_dir_mask) > 0;

		// if the shell settings are present, skip them
		final int shell_offset = 0x4c;
		final byte has_shell_mask = (byte)0x01;
		int shell_len = 0;
		
		if ((flags & has_shell_mask) > 0)
		shell_len = bytes2short(link, shell_offset) + 2;
		

		// get to the file settings
		int file_start = 0x4c + shell_len;

		final int file_location_info_flag_offset_offset = 0x08;
		int file_location_info_flag = link[file_start + file_location_info_flag_offset_offset];
		
		isLocal = (file_location_info_flag & 2) == 0;
		
		// get the local volume and local system values
		//final int localVolumeTable_offset_offset = 0x0C;
		final int basename_offset_offset = 0x10;
		final int networkVolumeTable_offset_offset = 0x14;
		final int finalname_offset_offset = 0x18;
		int finalname_offset = link[file_start + finalname_offset_offset] + file_start;
		String finalname = getNullDelimitedString(link, finalname_offset);
		
		if(isLocal)
		{
			int basename_offset = link[file_start + basename_offset_offset] + file_start;
			String basename = getNullDelimitedString(link, basename_offset);
			target_file = basename + finalname;
		}
		else
		{
			int networkVolumeTable_offset = link[file_start + networkVolumeTable_offset_offset] + file_start;
			int shareName_offset_offset = 0x08;
			int shareName_offset = link[networkVolumeTable_offset + shareName_offset_offset] + networkVolumeTable_offset;
			String shareName = getNullDelimitedString(link, shareName_offset);
			target_file = shareName + "\\" + finalname;
		}
	}
	
	
	

	private String getNullDelimitedString(byte[] bytes, int off)
	{
		int len = 0;
		while (bytes[off+len] != 0 && bytes[off+len] != 4) len++;
		return new String(bytes,off,len);
	}

	
	
	private int bytes2short(byte[] bytes, int off)
	{return ((bytes[off + 1] & 0xff) << 8) | (bytes[off] & 0xff);}
}