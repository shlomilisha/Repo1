package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;

public class SetupInitialization {
	
	@Test
	public void initializationSetup() throws IOException {
		System.out
		.println("Starting initialization setup for each platform type");
		// String vander = null;

		if (!Android.deviceIpList.isEmpty()) {
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					Android.launchApplication(Android.deviceIpList.get(x));
				}
			}
		}

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < Android.deviceIpList.size(); x++) {
			// if (x==0){
			// vander = deviceIpList.get(0).getVender();//

			if (Android.deviceIpList.get(0).getConnected()) {
				System.out.println("Initialization setup for "
						+ Android.deviceIpList.get(x).getName());

				Android.runADBCommand("adb -s " + Android.deviceIpList.get(x).getIP()
						+ ":5555 shell screencap /sdcard/"
						+ Android.deviceIpList.get(x).getName() + "PortalReference"
						+ ".png");// Take reference screenshout
				Android.runADBCommand("adb -s " + Android.deviceIpList.get(x).getIP()
						+ ":5555 pull /sdcard/" + Android.deviceIpList.get(x).getName()
						+ "PortalReference" + ".png" + " C:/Testes/"
						+ Android.deviceIpList.get(x).getName() + "PortalReference"
						+ ".png"); // Pull screenshout
				Android.runADBCommand("adb -s " + Android.deviceIpList.get(x).getIP()
						+ ":5555 shell rm /sdcard/"
						+ Android.deviceIpList.get(x).getName() + "PortalReference"
						+ ".png");// Delete screen shout
				System.out.println("Done initialization setup for "
						+ Android.deviceIpList.get(x).getName());
			}

		}

	}
}
