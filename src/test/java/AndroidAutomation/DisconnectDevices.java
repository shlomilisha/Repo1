package AndroidAutomation;

import java.io.IOException;

import org.junit.Test;


public class DisconnectDevices {

	

	@Test
	public void tearDown() throws IOException {
		Android.disconnectFromDevice(); // Disconnect
		try {// Close writer
			// writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
