package a.entity.gus06.env.windows.hdd.phy.serialnumber;

import a.framework.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251126";}

	public Object t(Object obj) throws Exception
	{
		Integer index = getDiskIndexFromFile((File) obj);
		if (index == null) return null;
		
		ProcessBuilder pb = new ProcessBuilder(
			"powershell.exe",
			"-NoProfile",
			"-Command",
			"$disk = Get-WmiObject Win32_DiskDrive | Where-Object { $_.Index -eq " + index + " }; if ($disk -ne $null) { $disk.SerialNumber }"
		);
		
		pb.redirectErrorStream(true);
		Process process = pb.start();
		
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(process.getInputStream(), "UTF-8")
		);
		
		String serial = reader.readLine();
		reader.close();
		process.destroy();
		return serial;
	}
	
	private Integer getDiskIndexFromFile(File root) throws Exception
	{
		String path = root.getAbsolutePath();
		String letter = path.substring(0, 2);
	
		String ps =
			"$ld = Get-WmiObject Win32_LogicalDisk -Filter \"DeviceID='" + letter + "'\";" +
			"$part = @(Get-WmiObject -Query \"ASSOCIATORS OF {Win32_LogicalDisk.DeviceID='" + letter + "'} " +
				"WHERE AssocClass=Win32_LogicalDiskToPartition\")[0];" +
			"$drive = @(Get-WmiObject -Query \"ASSOCIATORS OF {Win32_DiskPartition.DeviceID='$($part.DeviceID)'} " +
				"WHERE AssocClass=Win32_DiskDriveToDiskPartition\")[0];" +
			"$drive.Index";
	
		ProcessBuilder pb = new ProcessBuilder(
			"powershell.exe",
			"-NoProfile",
			"-Command",
			ps
		);
	
		pb.redirectErrorStream(true);
		Process process = pb.start();
	
		BufferedReader br = new BufferedReader(
			new InputStreamReader(process.getInputStream(), "UTF-8")
		);
	
		String line = br.readLine();
		br.close();
		process.destroy();
	
		if (line == null || line.trim().isEmpty()) return null;
		return Integer.parseInt(line.trim());
	}
}