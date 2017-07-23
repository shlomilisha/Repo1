package AndroidAutomation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;


public class ConnectDevices {

	

	@Test
	public void addDevice() throws IOException {
		BufferedReader lines = new BufferedReader(new FileReader(
				"/Testes/Devices.txt"));

		String line; // add file lines into this ver
		while ((line = lines.readLine()) != null) {
			if (line.equalsIgnoreCase("#Amzon2014")) {
				Integer FireDeviceCounter = 1;
				// line = lines.readLine(); //read next line in the file
				while ((line = lines.readLine()) != null
						&& !line.equalsIgnoreCase("##########")
						&& !line.equalsIgnoreCase("")) {
					FireTv2014 fireTv2014 = new FireTv2014("FireTv2014_"
							+ FireDeviceCounter, "Amazon", line, "Logitech",
							false);
					Android.deviceIpList.add(fireTv2014);
					// runADBCommand("adb" + " connect " + line);
					FireDeviceCounter++;
				}

			}
			if (line.equalsIgnoreCase("#Amzon2015")) {
				Integer FireDeviceCounter = 1;
				// line = lines.readLine(); //read next line in the file
				while ((line = lines.readLine()) != null
						&& !line.equalsIgnoreCase("##########")
						&& !line.equalsIgnoreCase("")) {
					FireTv2015 fireTv2015 = new FireTv2015("FireTv2015_"
							+ FireDeviceCounter, "Amazon", line, "Logitech");
					Android.deviceIpList.add(fireTv2015);
					// runADBCommand("adb" + " connect " + line);
					FireDeviceCounter++;
				}

			}
			if (line.equalsIgnoreCase("#Swisscom")) {
				Integer swisscomDeviceCounter = 1;
				// line = lines.readLine(); //read next line in the file
				while ((line = lines.readLine()) != null
						&& !line.equalsIgnoreCase("##########")
						&& !line.equalsIgnoreCase("")) {
					Swisscom swisscom = new Swisscom("Swisscom_"
							+ swisscomDeviceCounter, "Swisscom", line,
							"Logitech");
					Android.deviceIpList.add(swisscom);
					// runADBCommand("adb" + " connect " + line);
					swisscomDeviceCounter++;
				}

			}
			if (line.equalsIgnoreCase("#Philips")) {
				Integer philipsDeviceCounter = 1;
				// line = lines.readLine(); //read next line in the file
				while ((line = lines.readLine()) != null
						&& !line.equalsIgnoreCase("##########")
						&& !line.equalsIgnoreCase("")) {
					Philips philips = new Philips("Philips_"
							+ philipsDeviceCounter, "Philips", line, "Logitech");
					Android.deviceIpList.add(philips);
					// runADBCommand("adb" + " connect " + line);
					philipsDeviceCounter++;
				}

			}
			if (line.equalsIgnoreCase("#Emtec")) {
				Integer emtecDeviceCounter = 1;
				// line = lines.readLine(); //read next line in the file
				while ((line = lines.readLine()) != null
						&& !line.equalsIgnoreCase("##########")
						&& !line.equalsIgnoreCase("")) {
					Emtec emtec = new Emtec("Emtec_" + emtecDeviceCounter,
							"Emtec", line, "Logitech");
					Android.deviceIpList.add(emtec);
					// runADBCommand("adb" + " connect " + line);
					emtecDeviceCounter++;
				}

			}

		}
		if (Android.deviceIpList.size() != 0) { // printed devices on device arry list
			System.out.println("Number of devices added to list: "
					+ Android.deviceIpList.size());
			System.out.println("########################################");
			System.out.println("## Device name ## Device IP           ##");
			System.out.println("########################################");
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				System.out.println("   " + Android.deviceIpList.get(x).getName() + "  "
						+ Android.deviceIpList.get(x).getIP());
				// if (runADBCommand("adb" + " connect " +
				// Android.deviceIpList.get(x).getIP())){
				// Android.deviceIpList.get(x).setConnected(true);
				// }
			}
			System.out.println("########################################");
			System.out.println("");

		}

		lines.close();

	}
	
	@Test
	public void connectToDevices() throws IOException {
		// Try connected all device Array List devices
		if (Android.deviceIpList.size() != 0) { //
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				System.out.println(Android.deviceIpList.get(x).getName());
				if (Android.runADBCommandAndVerify("adb" + " connect "
						+ Android.deviceIpList.get(x).getIP())) {
					Android.deviceIpList.get(x).setConnected(true);
					Android.setMoreDevicesConnected(true);
					if (Android.deviceIpList.get(x).getVender()
							.equalsIgnoreCase("Philips")) {
						System.out.println("Device "
								+ Android.deviceIpList.get(x).getName()
								+ " vendor is :"
								+ Android.deviceIpList.get(x).getVender());
						Android.deviceIpList.get(x).setGamepad(
								Android.getGamepadEvent(Android.deviceIpList.get(x)));// Test
																		// line
					}
				}
			}

		}
		// Print connected devices
		Android.runADBCommand("adb devices");
	}
}
