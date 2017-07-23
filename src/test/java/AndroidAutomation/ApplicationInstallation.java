package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;

public class ApplicationInstallation {
	
	@Test
	public static void installApplication() throws IOException {
		if (!Android.deviceIpList.isEmpty()) {
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					Android.selectAPK(Android.deviceIpList.get(x).getVender());
					Android.locateUpgradeFile(Android.selectedAPK);

					String tmp = "adb -s " + Android.deviceIpList.get(x).getIP()
							+ ":5555 install -r " + Android.selectedAPK + "/"
							+ Android.upgradeFileName; // Insert command into temp var
					System.out.println("Trying installing apk on device: "
							+ Android.deviceIpList.get(x).getName());
					System.out.println(tmp);
					if (Android.runADBCommandAndVerify(tmp)) {
						System.out.println("Installing apk on device: "
								+ Android.deviceIpList.get(x).getName() + " success");
					} else {
						System.out.println("Installing apk on device: "
								+ Android.deviceIpList.get(x).getName() + " failed");
					}
				}
			}
		} else {

		}
	}
}
