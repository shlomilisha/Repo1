package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;

public class LaunchGameAndExitApplication {

	@Test
	public void launchGameAndExitApplication() throws IOException {
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

		for (int x = 0; x < Android.deviceIpList.size(); x++) {
			int z = 1;
			if (Android.deviceIpList.get(x).getConnected()) {
				// *******Verify portal launched
				// successfully*******
				if (Android.isApplicationLaunchSuccess(Android.deviceIpList.get(x), "_Portal_",
						z)) {

				} else {// Recover application (launched
					// application again )

					// Pull logcat from client
					Android.pullClientLogcat(Android.deviceIpList.get(x), z);

					if (Android.isClientRecovered(Android.deviceIpList.get(x), z)) {
					} else {
						System.out.println("failed recover "
								+ Android.deviceIpList.get(x).getName()
								+ " ,will disconnect device");

						Android.deviceIpList.get(x).setConnected(false);
						System.out.println("Set "
								+ Android.deviceIpList.get(x).getName()
								+ " as disconnected");

						// In case there are not connected
						// devices loop will be over
						if (!Android.checkIfMoreDevicesConnected()) {
							z = Android.loopCount;
						}
					}

				}
			}
		}

		// Launch a game
		for (int x = 0; x < Android.deviceIpList.size(); x++) {
			if (Android.deviceIpList.get(x).getConnected()) {
				// If it's not the first round select game
				// number 3
				// if (z > 1) {
				//
				// deviceIpList
				// .get(x)
				// .MoveRight(
				// deviceIpList.get(x)
				// .getIP(),
				// (int) ((Math.random() * 10) + 1));
				// }
				Android.androidLaunchedGame(Android.deviceIpList.get(x));
			}
		}

		// androidLaunchedGame();

		// Wait 20 second before exit application
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < Android.deviceIpList.size(); x++) {
			int z = 1;
			if (Android.deviceIpList.get(x).getConnected()) {
				if (Android.isGameLaunchSuccess(Android.deviceIpList.get(x), "_Game_", z)) {

				} else {// Recover application (launched
					// application again )

					// Pull logcat from client
					Android.pullClientLogcat(Android.deviceIpList.get(x), z);

					if (Android.isClientRecovered(Android.deviceIpList.get(x), z)) {

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

		androidExitApplication(); // Exit the application and
		// closed adb server
	}
	
	@Test
	public void androidExitApplication() throws IOException {
		if (!Android.deviceIpList.isEmpty()) {
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				if (Android.deviceIpList.get(x).getConnected()) {
					System.out.println("Exit Gamefly application on device: "
							+ Android.deviceIpList.get(x).getName());

					Android.deviceIpList.get(x).Overlay(Android.deviceIpList.get(x).getIP(), 1);
					Android.deviceIpList.get(x).MoveRight(Android.deviceIpList.get(x).getIP(),2);
					Android.deviceIpList.get(x).MoveDown(Android.deviceIpList.get(x).getIP(), 1);
					Android.deviceIpList.get(x).AButton(Android.deviceIpList.get(x).getIP(), 1);
					Android.deviceIpList.get(x).AButton(Android.deviceIpList.get(x).getIP(), 1);
				}
			}
		} else {
			// do nothing
		}
	}
}
