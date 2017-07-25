package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;

public class ApplicationUninstall {
	
	@Test
	public void uninstallApplication() throws IOException {
		if (!Android.deviceIpList.isEmpty()) {
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					// Set package name according to selected device
					if (Android.deviceIpList.get(x).getVender().equals("Amazon")) {
						Android.packageName = "com.media.playcast.FIRETV.AMAZON";
					} else if (Android.deviceIpList.get(x).getVender()
							.equals("Swisscom")) {
						Android.packageName = "com.media.playcast.SWISSCOM.MARVELL";
					} else if (Android.deviceIpList.get(x).getVender()
							.equals("Philips")) {
						Android.packageName = "com.media.playcast.PHILIPS.MTK";
					} else {
						Android.packageName = "com.media.playcast.EMTEC.GB";
					}

					String tmp = "adb -s " + Android.deviceIpList.get(x).getIP()
							+ ":5555 uninstall " + Android.packageName; // Insert
					// command into
					// temp var
					System.out.println("Trying uninstalling apk on device: "
							+ Android.deviceIpList.get(x).getName());
					System.out.println(tmp);
					if (Android.runADBCommandAndVerify(tmp)) {
						System.out.println("uninstalling apk on device: "
								+ Android.deviceIpList.get(x).getName() + " successed");
					} else {
						System.out.println("uninstalling apk on device: "
								+ Android.deviceIpList.get(x).getName() + " failed");
					}
					// runADBCommand(tmp);
				}

			}
		} else {

		}
	}
}
