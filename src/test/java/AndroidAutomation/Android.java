package AndroidAutomation;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.frontendtest.components.ImageComparison;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import org.frontendtest.components.ImageComparison;
//import command:
//import net.coobird.*;

public class Android {

	// static List<String> deviceIpList = new ArrayList<String>();
	static List<Device> deviceIpList = new ArrayList<Device>();
	static ArrayList<Integer> actionSelected = new ArrayList<Integer>(
			Arrays.asList(0, 0, 0, 0, 0));
	static boolean installApplication = false;
	static boolean uninstallApplication = false;
	static boolean initialization = false;

	// static boolean launchApplication = false;
	static boolean launchGame = false;

	static String imgToCompareWithOriginal = "C:/Testes/new_screenshot.jpg";
	static String imgOriginal = "C:/Testes/original.jpg";
	static String imgOutputDifferences = "C:/Testes/new_screenshot_with_changes.jpg";

	public static String getImgToCompareWithOriginal() {
		return imgToCompareWithOriginal;
	}

	public static void setImgToCompareWithOriginal(
			String imgToCompareWithOriginal) {
		Android.imgToCompareWithOriginal = imgToCompareWithOriginal;
	}

	public static String getImgOriginal() {
		return imgOriginal;
	}

	public static void setImgOriginal(String imgOriginal) {
		Android.imgOriginal = imgOriginal;
	}

	public static String getImgOutputDifferences() {
		return imgOutputDifferences;
	}

	public static void setImgOutputDifferences(String imgOutputDifferences) {
		Android.imgOutputDifferences = imgOutputDifferences;
	}

	static boolean moreDevicesConnected = false;

	public static boolean isMoreDevicesConnected() {
		return moreDevicesConnected;
	}

	public static void setMoreDevicesConnected(boolean moreDeviceConnected) {
		Android.moreDevicesConnected = moreDeviceConnected;
	}

	static Integer actionScript;
	static String scriptName;
	static Process process;
	final static File AmzonAPKLocation = new File("C:/Testes/AmazonUpdate/");
	final static File SwisscomAPKLocation = new File(
			"C:/Testes/SwisscomUpdate/");
	final static File PhilipsAPKLocation = new File("C:/Testes/PhilipsUpdate/");
	final static File EmtecAPKLocation = new File("C:/Testes/EmtecUpdate/");
	static File selectedAPK;
	// final static File ScriptLocation = new File("C:/Python27/events.sh");
	final static File ScriptLocation = new File("C:/Python27");
	static String upgradeFileName;
	static Scanner in = new Scanner(System.in);
	static String selectedDevice;
	static String packageName;
	static boolean loop = false;
	static Integer loopCount = 1;
	static String currentTime = new SimpleDateFormat("ddMMyyy_HHmmss")
	.format(Calendar.getInstance().getTime());

	// private static final DateFormat sdf = new
	// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	// private static final DateTimeFormatter dtf =
	// DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	// public static void main(String[] args) throws IOException {

	public void prepareForTest() throws IOException {
		Path path = Paths.get("C:/Testes/" + currentTime);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Directory " + currentTime + " is created!");
			} catch (IOException e) {
				// fail to create directory
				System.out.println("fail to create directory " + currentTime);
				e.printStackTrace();
			}
		}
		// Create Logfile with current time as a file name.
		// String fileName = "C:/Testes/" + currentTime + "/LogFile_"
		// + currentTime + ".txt";
		// File logFile = new File(fileName);

		// BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		// writer.write("Start log file\r\n");

		addDevice();// Add Devices From Devices file To Array List;
		connectToDevices();// Opened ADB connect To Devices on Array List();
		pushLogcatKaduri(); // Push logcat kaduri file to allow device
		// application logcat logs.

	}

	public void applicationInstallation() throws IOException {
		installApplication();
	}

	public void applicationRomove() throws IOException {
		uninstallApplication();
	}

	public void setupInitialization() throws IOException {
		initializationSetup();
	}

	public void gameLaunch() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					launchApplication(deviceIpList.get(x));
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
			// if (isApplicationLaunchSuccess(
			// deviceIpList.get(x), "_Portal_", z)) {
			//
			// } else {// Recover application (launched application again )
			//
			// // Pull logcat from client
			// pullClientLogcat(deviceIpList.get(x), z);
			//
			// if (isClientRecovered(
			// deviceIpList.get(x), z)) {
			//
			// } else {
			// System.out.println("failed recover "+ deviceIpList.get(x).getName()+ " ,will disconnect device");
			// deviceIpList.get(x).setConnected(false);
			// System.out.println("Set "+ deviceIpList.get(x).getName()+ " as disconnected");
			// }
			//
			// }
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					detectGamepad(deviceIpList.get(x));
					androidLaunchedGame(deviceIpList.get(x));
				}
			}

		}
	}

	public void launchGameAndExitApplication() throws IOException {
		for (int x = 0; x < deviceIpList.size(); x++) {
			if (deviceIpList.get(x).getConnected()) {
				launchApplication(deviceIpList.get(x));
			}
		}
		// Wait 20 sec for the application to launched
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < deviceIpList.size(); x++) {
			int z = 1;
			if (deviceIpList.get(x).getConnected()) {
				// *******Verify portal launched
				// successfully*******
				if (isApplicationLaunchSuccess(deviceIpList.get(x), "_Portal_",
						z)) {

				} else {// Recover application (launched
					// application again )

					// Pull logcat from client
					pullClientLogcat(deviceIpList.get(x), z);

					if (isClientRecovered(deviceIpList.get(x), z)) {
					} else {
						System.out.println("failed recover "
								+ deviceIpList.get(x).getName()
								+ " ,will disconnect device");

						deviceIpList.get(x).setConnected(false);
						System.out.println("Set "
								+ deviceIpList.get(x).getName()
								+ " as disconnected");

						// In case there are not connected
						// devices loop will be over
						if (!checkIfMoreDevicesConnected()) {
							z = loopCount;
						}
					}

				}
			}
		}

		// Launch a game
		for (int x = 0; x < deviceIpList.size(); x++) {
			if (deviceIpList.get(x).getConnected()) {
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
				androidLaunchedGame(deviceIpList.get(x));
			}
		}

		// androidLaunchedGame();

		// Wait 20 second before exit application
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < deviceIpList.size(); x++) {
			int z = 1;
			if (deviceIpList.get(x).getConnected()) {
				if (isGameLaunchSuccess(deviceIpList.get(x), "_Game_", z)) {

				} else {// Recover application (launched
					// application again )

					// Pull logcat from client
					pullClientLogcat(deviceIpList.get(x), z);

					if (isClientRecovered(deviceIpList.get(x), z)) {

					} else {
						System.out.println("failed recover "
								+ deviceIpList.get(x).getName()
								+ " ,will disconnect device");

						deviceIpList.get(x).setConnected(false);
						System.out.println("Set "
								+ deviceIpList.get(x).getName()
								+ " as disconnected");

					}

				}
			}
		}

		androidExitApplication(); // Exit the application and
		// closed adb server
	}

	public void tearDown() throws IOException {
		disconnectFromDevice(); // Disconnect
		try {// Close writer
			// writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// selectAction();

	public static void selectAPK(String selectedDevice) {
		if (selectedDevice.equals("Amazon")) {
			selectedAPK = AmzonAPKLocation;
		} else if (selectedDevice.equals("Swisscom")) {
			selectedAPK = SwisscomAPKLocation;
		} else if (selectedDevice.equals("Philips")) {
			selectedAPK = PhilipsAPKLocation;
		} else {
			selectedAPK = EmtecAPKLocation;
		}

	}

	public static void runADBCommand(String command) throws IOException {
		process = Runtime.getRuntime().exec(command);
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BufferedReader buf = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line; // add file lines into this ver
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
		}
		buf.close();

	}

	public static boolean runADBCommandAndVerify(String command)
			throws IOException {
		process = Runtime.getRuntime().exec(command);
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BufferedReader buf = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line; // add file lines into this ver
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
			if (line.contains("connected")) {
				buf.close();
				return true;
			} else if (line.contains("Success")) {
				buf.close();
				return true;
			}
		}
		buf.close();
		return false;

	}

	public static void addDevice() throws IOException {
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
					deviceIpList.add(fireTv2014);
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
					deviceIpList.add(fireTv2015);
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
					deviceIpList.add(swisscom);
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
					deviceIpList.add(philips);
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
					deviceIpList.add(emtec);
					// runADBCommand("adb" + " connect " + line);
					emtecDeviceCounter++;
				}

			}
			// FireTv2014 fireTV1 = new FireTv2014
			// ("devicename","Amazon","1.1.1.1","Logitech");
			// fireTV1.setName("AmazonFire1");
			// fireTV1.setIP(line);
			// deviceIpList.add(fireTV1);
			// runADBCommand("adb" + " connect " + line);
		}
		if (deviceIpList.size() != 0) { // printed devices on device arry list
			System.out.println("Number of devices added to list: "
					+ deviceIpList.size());
			System.out.println("########################################");
			System.out.println("## Device name ## Device IP           ##");
			System.out.println("########################################");
			for (int x = 0; x < deviceIpList.size(); x++) {
				System.out.println("   " + deviceIpList.get(x).getName() + "  "
						+ deviceIpList.get(x).getIP());
				// if (runADBCommand("adb" + " connect " +
				// deviceIpList.get(x).getIP())){
				// deviceIpList.get(x).setConnected(true);
				// }
			}
			System.out.println("########################################");
			System.out.println("");

		}

		lines.close();

	}

	public static void connectToDevices() throws IOException {

		// Try connected all device Array List devices
		if (deviceIpList.size() != 0) { //
			for (int x = 0; x < deviceIpList.size(); x++) {
				System.out.println(deviceIpList.get(x).getName());
				if (runADBCommandAndVerify("adb" + " connect "
						+ deviceIpList.get(x).getIP())) {
					deviceIpList.get(x).setConnected(true);
					setMoreDevicesConnected(true);
					if (deviceIpList.get(x).getVender()
							.equalsIgnoreCase("Philips")) {
						System.out.println("Device "
								+ deviceIpList.get(x).getName()
								+ " vendor is :"
								+ deviceIpList.get(x).getVender());
						deviceIpList.get(x).setGamepad(
								getGamepadEvent(deviceIpList.get(x)));// Test
						// line
					}
				}
			}

		}
		// Print connected devices
		runADBCommand("adb devices");
	}

	public static String getGamepadEvent(Device device) throws IOException {

		System.out.println("Initiate command : adb -s " + device.getIP()
				+ ":5555 shell getevent -S on device " + device.getName());
		runADBCommand("cmd /c adb -s " + device.getIP()
				+ ":5555 shell getevent -S"); // This line is extera , but it's
		// works only with it *** don't
		// remove**
		process = Runtime.getRuntime().exec(
				"adb -s " + device.getIP() + ":5555 shell getevent -S");
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BufferedReader buf = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		String line = buf.readLine(); // add stream line into this var
		// System.out.println("Check point 0 "+line);
		buf.close();
		if (line != null) {
			System.out.println("Device " + device.getName()
					+ " gamepad event is :" + line);
			// System.out.println("Check point 1 "+line);
			if (line.contains("/dev/input/")) {
				// System.out.println("Check point 2 "+line);
				String gamepadEvent[] = line.split(" ");
				System.out.println("Seystem pull " + gamepadEvent[3]
						+ " for device " + device.getName());
				return gamepadEvent[3];
			}
		}
		// System.out.println("Check point 3 "+line);
		return null;

	}

	public static void disconnectFromDevice() throws IOException {
		System.out.println("Disconnecting form devices ");
		runADBCommand("adb disconnect");
		runADBCommand("adb kill-server");
	}

	public static void initializationSetup() throws IOException {
		System.out
		.println("Starting initialization setup for each platform type");
		// String vander = null;

		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					launchApplication(deviceIpList.get(x));
				}
			}
		}

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < deviceIpList.size(); x++) {
			// if (x==0){
			// vander = deviceIpList.get(0).getVender();//

			if (deviceIpList.get(0).getConnected()) {
				System.out.println("Initialization setup for "
						+ deviceIpList.get(x).getName());

				runADBCommand("adb -s " + deviceIpList.get(x).getIP()
						+ ":5555 shell screencap /sdcard/"
						+ deviceIpList.get(x).getName() + "PortalReference"
						+ ".png");// Take reference screenshout
				runADBCommand("adb -s " + deviceIpList.get(x).getIP()
						+ ":5555 pull /sdcard/" + deviceIpList.get(x).getName()
						+ "PortalReference" + ".png" + " C:/Testes/"
						+ deviceIpList.get(x).getName() + "PortalReference"
						+ ".png"); // Pull screenshout
				runADBCommand("adb -s " + deviceIpList.get(x).getIP()
						+ ":5555 shell rm /sdcard/"
						+ deviceIpList.get(x).getName() + "PortalReference"
						+ ".png");// Delete screen shout
				System.out.println("Done initialization setup for "
						+ deviceIpList.get(x).getName());
			}

			// }else{
			// if ((vander!= deviceIpList.get(x).getVender())&&
			// deviceIpList.get(x).getConnected() ){ // if Vander changed
			// initialization it
			// vander=deviceIpList.get(x).getVender();
			//
			// System.out.println("Initialization setup for "+deviceIpList
			// .get(x).getVender());
			//
			// runADBCommand("adb -s " + deviceIpList.get(x).getIP()
			// +
			// ":5555 shell screencap /sdcard/"+vander+"Portal"+"Reference"+".png");//
			// Take reference screenshout
			// runADBCommand("adb -s " + deviceIpList.get(x).getIP()
			// + ":5555 pull /sdcard/"+vander+"Portal"+"Reference"+".png"
			// +" C:/Testes/"+vander+"Portal"+"Reference"+".png"); //Pull
			// screenshout
			// runADBCommand("adb -s " + deviceIpList.get(x).getIP()
			// +
			// ":5555 shell rm /sdcard/"+vander+"Portal"+"Reference"+".png");//
			// Delete screen shout
			// System.out.println("Done initialization setup for "+deviceIpList
			// .get(x).getVender());
			//
			// }
			// }

		}

	}

	public static void launchApplication(Device device) throws IOException {
		if (device.getVender().equals("Amazon")) {
			packageName = "com.media.playcast.FIRETV.AMAZON";
		} else if (device.getVender().equals("Swisscom")) {
			packageName = "com.media.playcast.SWISSCOM.MARVELL";
		} else if (device.getVender().equals("Philips")) {
			packageName = "com.media.playcast.PHILIPS.MTK";
		} else {
			packageName = "com.media.playcast.EMTEC.GB";
		}

		String tmp = "adb -s " + device.getIP() + ":5555 shell am start -n "
				+ packageName + "/com.media.playcast.AndroidClient"; // Insert
		// command
		// into
		// temp
		// var
		System.out.println("Launching apk on device: " + device.getName());
		runADBCommand(tmp);

	}

	public static void deviceLaunchApplication(Device device)
			throws IOException {
		if (device.getConnected()) {
			// Set package name according to selected device
			if (device.getVender().equals("Amazon")) {
				packageName = "com.media.playcast.FIRETV.AMAZON";
			} else if (device.getVender().equals("Swisscom")) {
				packageName = "com.media.playcast.SWISSCOM.MARVELL";
			} else if (device.getVender().equals("Philips")) {
				packageName = "com.media.playcast.PHILIPS.MTK";
			} else {
				packageName = "com.media.playcast.EMTEC.GB";
			}

			String tmp = "adb -s " + device.getIP()
					+ ":5555 shell am start -n " + packageName
					+ "/com.media.playcast.AndroidClient"; // Insert command
			// into temp var
			System.out.println("Launching apk on device: " + device.getName());
			runADBCommand(tmp);
			// runADBCommand("adb install -r C:/Users/shlomi/PlaycastAndroidClient-EMTEC.GB-2.1.43328.44.apk");
		}
	}

	public static void getDeviceIp() throws IOException {

	}

	public static void locateUpgradeFile(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				locateUpgradeFile(fileEntry);
			} else {
				upgradeFileName = fileEntry.getName();
				System.out.println("The install file name is : "
						+ upgradeFileName);
				System.out.println();

			}
		}
	}

	public static void installApplication() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					selectAPK(deviceIpList.get(x).getVender());
					locateUpgradeFile(selectedAPK);

					String tmp = "adb -s " + deviceIpList.get(x).getIP()
							+ ":5555 install -r " + selectedAPK + "/"
							+ upgradeFileName; // Insert command into temp var
					System.out.println("Trying installing apk on device: "
							+ deviceIpList.get(x).getName());
					System.out.println(tmp);
					if (runADBCommandAndVerify(tmp)) {
						System.out.println("Installing apk on device: "
								+ deviceIpList.get(x).getName() + " success");
					} else {
						System.out.println("Installing apk on device: "
								+ deviceIpList.get(x).getName() + " failed");
					}
				}
			}
		} else {

		}
	}

	public static void uninstallApplication() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					// Set package name according to selected device
					if (deviceIpList.get(x).getVender().equals("Amazon")) {
						packageName = "com.media.playcast.FIRETV.AMAZON";
					} else if (deviceIpList.get(x).getVender()
							.equals("Swisscom")) {
						packageName = "com.media.playcast.SWISSCOM.MARVELL";
					} else if (deviceIpList.get(x).getVender()
							.equals("Philips")) {
						packageName = "com.media.playcast.PHILIPS.MTK";
					} else {
						packageName = "com.media.playcast.EMTEC.GB";
					}

					String tmp = "adb -s " + deviceIpList.get(x).getIP()
							+ ":5555 uninstall " + packageName; // Insert
					// command into
					// temp var
					System.out.println("Trying uninstalling apk on device: "
							+ deviceIpList.get(x).getName());
					System.out.println(tmp);
					if (runADBCommandAndVerify(tmp)) {
						System.out.println("uninstalling apk on device: "
								+ deviceIpList.get(x).getName() + " successed");
					} else {
						System.out.println("uninstalling apk on device: "
								+ deviceIpList.get(x).getName() + " failed");
					}
					// runADBCommand(tmp);
				}

			}
		} else {

		}
	}

	public void launchGameflyApp() throws IOException {
		runADBCommand("adb -s shell am start -n com.media.playcast.EMTEC.GB-2.apk=com.media.playcast.EMTEC.GB/com.media.playcast.EMTEC.GB-2.apk=com.media.playcast.EMTEC.GB.ActivityName");

		// BufferedReader input = new BufferedReader(new
		// InputStreamReader(process.getInputStream()));
		// while ((input.readLine()) != null) {
		// System.out.println(input.readLine());
		// }
		// input.close();
	}

	public static void pushScript() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				System.out
				.println("Pushing script file to /data/local/tmp/ to device: "
						+ deviceIpList.get(x));

				if (actionScript == 1) {
					// System.out.println(ScriptLocation+"/"+selectedDevice+"_launched_game.sh");
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 push " + ScriptLocation + "/"
							+ selectedDevice
							+ "_launched_game.sh data/local/tmp/"); // Load the
					// script to
					// the
					// device
					// System.out.println("adb -s "+deviceIpList.get(x)+":5555 push "+ScriptLocation+"/"+selectedDevice+"_launched_game.sh data/local/tmp/");
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 shell chmod 755 /data/local/tmp/"
							+ selectedDevice + "_launched_game.sh"); // Set the
					// permissions
					scriptName = selectedDevice + "_launched_game.sh";

				}
				if (actionScript == 2) {
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 push " + ScriptLocation + selectedDevice
							+ "_exit_game.sh data/local/tmp/"); // Load the
					// script to the
					// device
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 shell chmod 755 /data/local/tmp/"
							+ selectedDevice + "_exit_game.sh"); // Set the
					// permissions
					scriptName = selectedDevice + "_exit_game.sh";

				}
				if (actionScript == 3) {
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 push " + ScriptLocation + selectedDevice
							+ "_exit_game.sh data/local/tmp/"); // Load the
					// script to the
					// device
					runADBCommand("adb -s " + deviceIpList.get(x)
							+ ":5555 shell chmod 755 /data/local/tmp/"
							+ selectedDevice + "_goover_overlay.sh"); // Set the
					// permissions
					scriptName = selectedDevice + "_goover_overlay.sh";

				}

				// runADBCommand("adb -s "+deviceIpList.get(x)+":5555 push "+ScriptLocation+" data/local/tmp/");
				// //Load the script to the device
				// runADBCommand("adb -s "+deviceIpList.get(x)+":5555 shell chmod 755 /data/local/tmp/events.sh");
				// //Set the permissions
			}
		} else {

		}
	}

	public static void runScript() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				System.out.println("Run script file on device: "
						+ deviceIpList.get(x));
				runADBCommand("adb -s " + deviceIpList.get(x)
						+ ":5555 shell sh /data/local/tmp/" + scriptName); // Run
				// script
			}
		} else {

		}
	}

	public static void captureScreenshot(Device device, String status,int loopNumber) throws IOException {

		System.out.println("Take screen shot on device: " + device.getName());

		runADBCommand("adb -s " + device.getIP()
				+ ":5555 shell screencap /sdcard/" + device.getName() + status
				+ loopNumber + ".png");// Take screenshout
		runADBCommand("adb -s " + device.getIP() + ":5555 pull /sdcard/"
				+ device.getName() + status + loopNumber + ".png C:/Testes/"
				+ currentTime + "/" + device.getName() + status + loopNumber
				+ ".png"); // Pull screenshout
		runADBCommand("adb -s " + device.getIP() + ":5555 shell rm /sdcard/"
				+ device.getName() + status + loopNumber + ".png");// Delete
		// screen
		// shout

		setImgToCompareWithOriginal("C:/Testes/" + currentTime + "/"+ device.getName() + status + loopNumber + ".png");

	}

	public static boolean IsEqualScreenshout(Device device, String status,double threshold, Integer loopNumber,String imgToCompareWithOriginal, String imgOriginal,String imgOutputDifferences) throws IOException {

		// setImgToCompareWithOriginal("C:/Testes/"+device.getName()+status+loopNumber+".png");
		// setImgOriginal("C:/Testes/"+device.getVender()+status+"Reference"+".png");
		// setImgOutputDifferences("C:/Testes/"+device.getName()+status+loopNumber+"_Difference.jpg");

		System.out.println("Compering screenshots on device" + device.getName()
				+ ".........");
		
		System.out.println("Check point 1");

		ImageComparison imageComparison = new ImageComparison(10, 10, 0.07);
		System.out.println("Check point 2");

		if (imageComparison.fuzzyEqual(imgOriginal, imgToCompareWithOriginal,
				imgOutputDifferences)) {
			System.out.println("Images are fuzzy-equal.");
			return true;
		}

		else {
			System.out.println("Images are not fuzzy-equal.");
			return false;
		}
	}
	
	public static void detectGamepad(Device device) throws IOException {
		System.out.println("Activate gamepad as index 1 on device: " + device.getName());
		device.RTButton(device.getIP(), 1);
	}

	public static void androidLaunchedGame(Device device) throws IOException {

		System.out.println("Launching a game on device: " + device.getName());
		device.AButton(device.getIP(), 2);
	}

	public static void androidPlayGameTrail(Device device, int gameNumber)
			throws IOException {

		System.out.println("Play game taril number " + gameNumber
				+ " on device: " + device.getName());
		device.AButton(device.getIP(), 1);
		device.MoveDown(device.getIP(), 1);
		device.AButton(device.getIP(), 1);

	}

	public static void androidLaunchAllMyGames() throws IOException {
		List<String> isGameLaunch = new ArrayList<String>();
		Integer counter = 0;
		boolean equal = false;
		while (!equal) {
			if (counter == 0) {
				// Take screen capture for reference
				if (!deviceIpList.isEmpty()) {
					for (int x = 0; x < deviceIpList.size(); x++) {
						if (deviceIpList.get(x).getConnected()) {
							System.out
							.println("check point 1 The counter value is :"
									+ counter);
							captureScreenshot(deviceIpList.get(x),
									"_Portal_Launched_All_Games_", counter);
						}
					}
					for (int x = 0; x < deviceIpList.size(); x++) {
						if (deviceIpList.get(x).getConnected()) {
							androidLaunchedGame(deviceIpList.get(x));
						}
					}

					for (int x = 0; x < deviceIpList.size(); x++) {
						if (deviceIpList.get(x).getConnected()) {
							if (isGameLaunchSuccess(deviceIpList.get(x),
									"_Game_", counter)) {
								isGameLaunch.add("no");
								// writer.write
								// ("Game number "+z+" failed to launched\r\n");
							} else {
								isGameLaunch.add("yes");
								// writer.write
								// ("Game number "+z+" success to launched\r\n");
							}
						}
					}

					try {
						Thread.sleep(15000);// Wait game 20 second before
						// returns to
						// portal
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					androidReturnToPortal();
					counter++;

				}
				// captureScreenshot(device, "_Portal_Launched_All_Games_",
				// counter);

				// androidLaunchAllMyGames(counter);

			} else {
				// Move right once
				for (int x = 0; x < deviceIpList.size(); x++) {
					if (deviceIpList.get(x).getConnected()) {
						deviceIpList.get(x).MoveRight(
								deviceIpList.get(x).getIP(), counter);
						System.out
						.println("Check point 2 The counter value is :"
								+ counter);
						captureScreenshot(deviceIpList.get(x),
								"_Portal_Launched_All_Games_", counter);

						setImgToCompareWithOriginal("C:/Testes/" + currentTime
								+ "/" + deviceIpList.get(x).getName()
								+ "_Portal_Launched_All_Games_" + counter
								+ ".png");
						setImgOriginal("C:/Testes/" + currentTime + "/"
								+ deviceIpList.get(x).getName()
								+ "_Portal_Launched_All_Games_" + (counter - 1)
								+ ".png");
						setImgOutputDifferences("C:/Testes/" + currentTime
								+ "/" + deviceIpList.get(x).getName()
								+ "_Portal_Launched_All_Games_" + counter
								+ "_Difference.jpg");

						// Verify if we are on the last game
						if (IsEqualScreenshout(deviceIpList.get(x),
								"_Portal_Launched_All_Games_", 0.05, counter,
								getImgToCompareWithOriginal(),
								getImgOriginal(), getImgOutputDifferences())) {
							System.out
							.println("Done,launched all games on device: "
									+ deviceIpList.get(x).getName());
							// deviceIpList.get(x).AButton(deviceIpList.get(x).getIP(),
							// 2);
							equal = true;

						} else {
							// If Image are not equals delete Difference image.
							try {
								File file = new File(getImgOutputDifferences());
								if (file.delete()) {
									System.out.println(file.getName()
											+ " is deleted!");
								} else {
									System.out
									.println("Delete operation is failed.");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							androidLaunchedGame(deviceIpList.get(x));
						}
					}
				}

				for (int x = 0; x < deviceIpList.size(); x++) {
					if (deviceIpList.get(x).getConnected()) {
						if (isGameLaunchSuccess(deviceIpList.get(x), "_Game_",
								counter)) {
							isGameLaunch.add("yes");
							// writer.write
							// ("Game number "+z+" failed to launched\r\n");
						} else {
							isGameLaunch.add("no");
							// writer.write
							// ("Game number "+z+" success to launched\r\n");
						}
						try {
							Thread.sleep(10000);// Play game 10 second
							// before returns to portal
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}

				androidReturnToPortal();

				// androidReturnToPortal();
				// counter++;
				// androidLaunchAllMyGames(counter);

				// }
				// }
				// }

				// }
				// }
				counter++;

			}

		}

	}

	public static boolean isGameLaunchSuccess(Device device, String status,
			int z) throws IOException {// Take game screen shot and compare it
		// to Connection Interrupted picture
		captureScreenshot(device, status, z);
		// cropImage(getImgToCompareWithOriginal(), 520, 770, 880, 80);
		cropImage(getImgToCompareWithOriginal(), 850, 1000, 200, 50);

		setImgToCompareWithOriginal("C:/Testes/www.gamefly.com.png");
		setImgOriginal("C:/Testes/" + currentTime + "/" + device.getName()
				+ status + z + "_Cropped.png");
		setImgOutputDifferences("C:/Testes/" + currentTime + "/"
				+ device.getName() + status + z + "_Difference.png");

		if (IsEqualScreenshout(device, status, 0.05, z,
				getImgToCompareWithOriginal(), getImgOriginal(),
				getImgOutputDifferences())) {// Compare Game image with error
			// image if equal print error
			System.out.println(device.getName()
					+ " Displayed error message Game number_" + z
					+ "_Game not available");
			return false;
		} else {
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+" Displayed error message Game number_"+z+"_Game not available");
			// return false;
			// }else{
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressA_PressB1.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_A_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+" Displayed error message Game number_"+z+"_Game not available");
			// return false;
			// }else{
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB1.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+"Displayed error message Game number_"+z+"_Game not available");
			// return false;
			// }else{
			System.out.println(device.getName() + "_Game number_" + z
					+ "_Game launched successfully");
			return true;
			// }
			// }
			// }
		}
	}

	public static boolean isApplicationLaunchSuccess(Device device,String status, int z) throws IOException {// Take game screen shot
		// and compare it to
		// Connection
		// Interrupted picture
		captureScreenshot(device, status, z);
		// cropImage(getImgToCompareWithOriginal(), 520, 770, 880, 80);
		cropImage(getImgToCompareWithOriginal(), 850, 1000, 200, 50);

		setImgOriginal("C:/Testes/www.gamefly.com.png");
		System.out.println("The file location is C:/Testes/" + currentTime + "/" + device.getName() + status + z + "_Cropped.png");
		setImgToCompareWithOriginal("C:/Testes/" + currentTime + "/" + device.getName() + status + z + "_Cropped.png");
		setImgOutputDifferences("C:/Testes/" + currentTime + "/" + device.getName() + status + z + "_Difference.png");

		if (IsEqualScreenshout(device, status, 0.05, z,getImgToCompareWithOriginal(), getImgOriginal(),getImgOutputDifferences())) {// Compare Game image with error
			// image if equal print error
			System.out
			.println(device.getName()
					+ " Displayed error message, failed to launched application loop number_"
					+ z);
			return false;
		} else {
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+" Displayed error message, failed to launched application loop number_"+z);
			// return false;
			// }else{
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressA_PressB1.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_A_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+" Displayed error message, failed to launched application loop number_"+z);
			// return false;
			// }else{
			// setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB1.png");
			// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference_B.png");
			// if(IsEqualScreenshout(device, status, 0.05,
			// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
			// Game image with error image if equal print error
			// System.out.println(device.getName()+" Displayed error message, failed to launched application loop number_"+z);
			// return false;
			// }else{
			System.out.println(device.getName() + status + "number_" + z
					+ status + " launched successfully");
			return true;
			// }
			// }
			// }
		}
	}

	// public static boolean isGameLaunchSuccess (Device device, String status,
	// int z) throws IOException{//Take game screen shot and compare it to
	// Connection Interrupted picture
	// captureScreenshot(device,status, z);
	// // wait 2 second before capture additional screenshot
	// try {
	// Thread.sleep(2000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// captureScreenshot(device,status+z+"_", z);
	//
	// setImgToCompareWithOriginal("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_"+z+".png");
	// setImgOriginal("C:/Testes/"+currentTime+"/"+device.getName()+status+z+".png");
	// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+status+z+"_Difference.png");
	//
	// if(IsEqualScreenshout(device, status, 0.05,
	// z,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){//Compare
	// Game image with error image if equal print error
	// System.out.println(device.getName()+"_Game number_"+z+"_Game not available");
	// return false;
	// }else{
	// System.out.println(device.getName()+"_Game number_"+z+"_Game launched successfully");
	// return true;
	// }
	// }

	public static void androidReturnToPortal() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					System.out.println("Luanched overlay on device: "
							+ deviceIpList.get(x).getName());

					deviceIpList.get(x).Overlay(deviceIpList.get(x).getIP(), 1);
					deviceIpList.get(x).MoveRight(deviceIpList.get(x).getIP(),
							2);
					deviceIpList.get(x).AButton(deviceIpList.get(x).getIP(), 1);
				}
			}
		} else {
			// do nothing
		}
	}

	public static void androidExitApplication() throws IOException {
		if (!deviceIpList.isEmpty()) {
			for (int x = 0; x < deviceIpList.size(); x++) {
				if (deviceIpList.get(x).getConnected()) {
					System.out.println("Exit Gamefly application on device: "
							+ deviceIpList.get(x).getName());

					deviceIpList.get(x).Overlay(deviceIpList.get(x).getIP(), 1);
					deviceIpList.get(x).MoveRight(deviceIpList.get(x).getIP(),
							2);
					deviceIpList.get(x)
					.MoveDown(deviceIpList.get(x).getIP(), 1);
					deviceIpList.get(x).AButton(deviceIpList.get(x).getIP(), 1);
				}
			}
		} else {
			// do nothing
		}
	}

	public static void androidExitTrailer(Device device) throws IOException {
		device.BButton(device.getIP(), 2);
	}

	public static void androidExitApplicationFromTrailer(Device device)
			throws IOException {
		device.BButton(device.getIP(), 3);
		device.MoveRight(device.getIP(), 3);
		device.AButton(device.getIP(), 1);
	}

	public static boolean isClientRecovered(Device device, int loopNumber)
			throws IOException {
		// Recover application (launched application again
		System.out.println("Trying to recover " + device.getName());
		device.BButton(device.getIP(), 1);
		deviceLaunchApplication(device);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		device.XButton(device.getIP(), 1); // Press X button in case marketing
		// message appears.
		captureScreenshot(device, "_Portal_", loopNumber);
		cropImage(getImgToCompareWithOriginal(), 520, 770, 880, 80);

		setImgToCompareWithOriginal("C:/Testes/Error_Message_PressA_PressB.png");
		setImgOriginal("C:/Testes/" + currentTime + "/" + device.getName()
				+ "_Portal_" + loopNumber + "_Cropped.png");
		setImgOutputDifferences("C:/Testes/" + currentTime + "/"
				+ device.getName() + "_Portal_" + loopNumber
				+ "_Difference_A_B.png");

		if (IsEqualScreenshout(device, "_Portal_", 0.05, loopNumber,
				getImgToCompareWithOriginal(), getImgOriginal(),
				getImgOutputDifferences())) {// Compare Portal image with error
			// image if equal print error
			System.out
			.println(device.getName()
					+ " Displayed error message while launching portal loop number_"
					+ loopNumber);
			return false;
		} else {
			setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB.png");
			setImgOutputDifferences("C:/Testes/" + currentTime + "/"
					+ device.getName() + "_Portal_" + loopNumber
					+ "_Difference_B.png");
			if (IsEqualScreenshout(device, "_Portal_", 0.05, loopNumber,
					getImgToCompareWithOriginal(), getImgOriginal(),
					getImgOutputDifferences())) {// Compare Portal image with
				// error image if equal
				// print error
				System.out
				.println(device.getName()
						+ " Displayed error message while launching portal loop number_"
						+ loopNumber);
				return false;
			} else {
				setImgToCompareWithOriginal("C:/Testes/Error_Message_PressA_PressB1.png");
				setImgOutputDifferences("C:/Testes/" + currentTime + "/"
						+ device.getName() + "_Portal_" + loopNumber
						+ "_Difference_A_B1.png");
				if (IsEqualScreenshout(device, "_Portal_", 0.05, loopNumber,
						getImgToCompareWithOriginal(), getImgOriginal(),
						getImgOutputDifferences())) {// Compare Portal image
					// with error image if
					// equal print error
					System.out
					.println(device.getName()
							+ " Displayed error message while launching portal loop number_"
							+ loopNumber);
					return false;
				} else {
					setImgToCompareWithOriginal("C:/Testes/Error_Message_PressB1.png");
					setImgOutputDifferences("C:/Testes/" + currentTime + "/"
							+ device.getName() + "_Portal_" + loopNumber
							+ "_Difference_B1.png");
					if (IsEqualScreenshout(device, "_Portal_", 0.05,
							loopNumber, getImgToCompareWithOriginal(),
							getImgOriginal(), getImgOutputDifferences())) {// Compare
						// Portal
						// image
						// with
						// error
						// image
						// if
						// equal
						// print
						// error
						System.out
						.println(device.getName()
								+ "Displayed error message while launching portal loop number_"
								+ loopNumber);
						return false;
					} else {
						System.out.println(device.getName()
								+ "_Portal loop number_" + loopNumber
								+ "_launched successfully");
						System.out.println("Device " + device.getName()
								+ " successfully recoverd to portal ");
						return true;
					}
				}
			}
		}

		// ****Old code compare current screen shot with device portal.****
		// captureScreenshot(device,"_Portal_", loopNumber);
		// setImgOriginal("C:/Testes/"+device.getName()+"PortalReference.png");
		// setImgOutputDifferences("C:/Testes/"+currentTime+"/"+device.getName()+"_Portal_"+loopNumber+"_Difference.png");
		// if(IsEqualScreenshout(device,"_Portal_", 0.05 ,
		// loopNumber,getImgToCompareWithOriginal(),getImgOriginal(),getImgOutputDifferences())){
		// System.out.println("Recover "+device.getName()+" to portal sucessfully");
		// androidLaunchedGame(device);
		// return true;
		// }else{
		// return false;
		// }

	}

	public static void cropImage(String orgImage, int x, int y, int w, int h)
			throws IOException {
		Image src = ImageIO.read(new File(orgImage));

		BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		dst.getGraphics().drawImage(src, 0, 0, w, h, x, y, x + w, y + h, null);
		System.out.println("Cropping image " + orgImage);
		orgImage = orgImage.replaceAll(".png", "_Cropped.png");

		ImageIO.write(dst, "png", new File(orgImage));
		System.out.println("Saved croped image " + orgImage);

	}

	public static void pushLogcatKaduri() throws IOException {
		if (deviceIpList.size() != 0) { //
			for (int x = 0; x < deviceIpList.size(); x++) {
				// System.out.println("Pushing Logcat Kaduri file to device: "+device.getName());
				runADBCommand("adb -s " + deviceIpList.get(x).getIP()
						+ ":5555 push C:/Testes/logcat_kaduri /data/local/tmp");
				System.out.println("Pushed logcat_Kaduri file to device "
						+ deviceIpList.get(x).getName());
			}
		}

	}

	public static boolean checkIfMoreDevicesConnected() throws IOException {
		int connectedDevicesCounter = 0;
		for (int x = 0; x < deviceIpList.size(); x++) {
			if (deviceIpList.get(x).getConnected()) {
				connectedDevicesCounter++;
			}

		}
		if (connectedDevicesCounter > 0) {
			setMoreDevicesConnected(true);
			return true;
		} else {
			setMoreDevicesConnected(false);
			System.out.println("No more devices connected , ending loop");
			// return false;
		}
		return false;
	}

	public static void pullClientLogcat(Device device, int loopNumer)
			throws IOException {
		System.out.println("Inizilazed command: adb -s " + device.getIP()
				+ ":5555 logcat -d >C:/Testes/" + currentTime + "/"
				+ device.getName() + "_" + loopNumer
				+ "_Logcat.txt *:S Playcast");
		runADBCommand("cmd /c adb -s " + device.getIP()
				+ ":5555 logcat -d > C:/Testes/" + currentTime + "/"
				+ device.getName() + "_" + loopNumer
				+ "_Logcat.txt *:S Playcast");
		System.out.println("Saved logcat for device " + device.getName());

	}

}
