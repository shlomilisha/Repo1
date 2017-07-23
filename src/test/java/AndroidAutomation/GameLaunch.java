package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;

public class GameLaunch {

	@Test
	public void gameLaunch() throws IOException {
		if (!Android.deviceIpList.isEmpty()) {
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					Android.launchApplication(Android.deviceIpList.get(x));
				}
			}
			// Wait 20 sec for the application to launched
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int z = 1;
			// *******Verify portal launched
			// successfully*******

			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					if (Android.isApplicationLaunchSuccess(
							Android.deviceIpList.get(x), "_Portal_", z)) {
						System.out.println("Portal launched successfull");
					} else {// Recover application (launched application again )

						// Pull logcat from client
						Android.pullClientLogcat(Android.deviceIpList.get(x), z);
						if (Android.isClientRecovered(
								Android.deviceIpList.get(x), z)) {

						} else {
							System.out.println("failed recover "
									+ Android.deviceIpList.get(x).getName()
									+ " ,will disconnect device");
							Android.deviceIpList.get(x).setConnected(false);
							System.out.println("Set "
									+ Android.deviceIpList.get(x).getName()
									+ " as disconnected");
						}
					}
				}
			}
		}

		for (int x = 0; x < Android.deviceIpList.size(); x++) {
			if (Android.deviceIpList.get(x).getConnected()) {
				Android.detectGamepad(Android.deviceIpList.get(x));
				Android.androidLaunchedGame(Android.deviceIpList.get(x));
			}
		}

	}
}
