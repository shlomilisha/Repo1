package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;


public class PushLogcatKaduri {
	
	@Test
	public void pushLogcatKaduri() throws IOException {
		if (Android.deviceIpList.size() != 0) { //
			for (int x = 0; x < Android.deviceIpList.size(); x++) {
				// System.out.println("Pushing Logcat Kaduri file to device: "+device.getName());
				Android.runADBCommand("adb -s " + Android.deviceIpList.get(x).getIP()
						+ ":5555 push C:/Testes/logcat_kaduri /data/local/tmp");
				System.out.println("Pushed logcat_Kaduri file to device "
						+ Android.deviceIpList.get(x).getName());
			}
		}

	}
}
