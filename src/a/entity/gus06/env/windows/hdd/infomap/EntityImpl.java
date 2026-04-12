package a.entity.gus06.env.windows.hdd.infomap;

import a.framework.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.PrintWriter;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251126";}

	public Object t(Object obj) throws Exception
	{
		File root = (File) obj;
		Integer index = getDiskIndexFromFile(root);
		if (index == null) return null;

		// --- Récupération PhySerialNumber et infos disque ---
		String ps =
			"$disk = Get-WmiObject Win32_DiskDrive | Where-Object { $_.Index -eq " + index + " }; " +
			"if ($disk -ne $null) { " +
				"'{0}|{1}|{2}|{3}|{4}|{5}|{6}' -f " +
				"$disk.SerialNumber, $disk.Model, $disk.Manufacturer, $disk.FirmwareRevision, $disk.Size, $disk.MediaType, $disk.Partitions " +
			"}";

		ProcessBuilder pb = new ProcessBuilder(
			"powershell.exe",
			"-NoProfile",
			"-Command",
			ps
		);

		pb.redirectErrorStream(true);
		Process process = pb.start();

		BufferedReader reader = new BufferedReader(
			new InputStreamReader(process.getInputStream(), "UTF-8")
		);

		String line = reader.readLine();
		reader.close();
		process.destroy();

		if (line == null || line.trim().isEmpty()) return null;

		String[] parts = line.split("\\|");
		Map info = new HashMap();
		info.put("PhySerialNumber", parts[0].trim());
		info.put("Model", parts[1].trim());
		info.put("Manufacturer", parts[2].trim());
		info.put("Firmware", parts[3].trim());
		info.put("Size", parts[4].trim());
		info.put("MediaType", parts[5].trim());
		info.put("Partitions", parts[6].trim());

		// --- Récupération VolSerialNumber ---
		String volSerial = getVolumeSerialNumber(root);
		info.put("VolSerialNumber", volSerial);

		return info;
	}

	private Integer getDiskIndexFromFile(File root) throws Exception
	{
		String path = root.getAbsolutePath();
		String letter = path.substring(0, 2);
		
		File tmp = File.createTempFile("diskindex", ".ps1");
		try (PrintWriter out = new PrintWriter(tmp)) {
		    out.println("$ld = Get-WmiObject Win32_LogicalDisk -Filter \"DeviceID='C:'\"");
		    out.println("$part = @(Get-WmiObject -Query \"ASSOCIATORS OF {Win32_LogicalDisk.DeviceID='C:'} WHERE AssocClass=Win32_LogicalDiskToPartition\")[0]");
		    out.println("$drive = @(Get-WmiObject -Query \"ASSOCIATORS OF {Win32_DiskPartition.DeviceID='$($part.DeviceID)'} WHERE AssocClass=Win32_DiskDriveToDiskPartition\")[0]");
		    out.println("$drive.Index");
		}
		
		ProcessBuilder pb = new ProcessBuilder("powershell.exe", "-NoProfile", "-File", tmp.getAbsolutePath());

		pb.redirectErrorStream(true);
		Process process = pb.start();

		BufferedReader br = new BufferedReader(
			new InputStreamReader(process.getInputStream(), "UTF-8")
		);

		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println("line:"+line);
			line = line.trim();
			if (!line.isEmpty() && line.matches("\\d+")) {
				br.close();
				process.destroy();
				return Integer.parseInt(line);
			}
		}

		br.close();
		process.destroy();
		return null;
	}

	private String getVolumeSerialNumber(File root)
	{
		Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
		byte[] volName = new byte[256];
		byte[] fsName = new byte[256];
		int[] volSerNbr = new int[1];
		int[] maxCompLen = new int[1];
		int[] fileSysFlags = new int[1];

		boolean ok = kernel32.GetVolumeInformationA(root.getAbsolutePath(), volName, 256, volSerNbr, maxCompLen, fileSysFlags, fsName, 256);
		if (!ok) return null;

		return Integer.toHexString(volSerNbr[0]).toUpperCase();
	}

	public interface Kernel32 extends Library
	{
		boolean GetVolumeInformationA(String path, byte[] volName, int volumeNameSize,
			int[] volSerNbr, int[] maxCompLen, int[] fileSysFlags, byte[] fsName, int fileSystemNameSize);
	}
}
