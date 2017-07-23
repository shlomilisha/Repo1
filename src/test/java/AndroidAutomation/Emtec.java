package AndroidAutomation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Emtec extends Device{
//	static Process process;
	
	

	public Emtec(String name, String vender, String IP , String gamepad) {
		super();
		this.name = name;
		this.vender = vender;
		this.IP = IP;
		this.gamepad = gamepad;
	}

	public static void runADBCommand(String command) throws IOException {
		Android.process = Runtime.getRuntime().exec(command);
		try {
			Android.process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader buf = new BufferedReader(new InputStreamReader(
				Android.process.getInputStream()));
		String line; // add file lines into this ver
		while ((line = buf.readLine()) != null) {
			System.out.println(line);
		}

	}
	
	@Override
	public void MoveRight(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing Right axis on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 16 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 16 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void MoveLeft(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing Left axis on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 16 4294967295");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 16 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void MoveUp(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing Up axis on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 17 4294967295");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 17 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void MoveDown(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing Dwon axis on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 17 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 17 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void XButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing X button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 307 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 307 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void AButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing A button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 304 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 304 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void BButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing B button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 305 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 305 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void YButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing Y button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 308 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 308 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void LBButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing LB button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 310 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 310 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void LTButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing TB button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 2 255");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 2 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void RBButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing RB button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 311 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 311 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void RTButton(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing RT button on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 5 255");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 5 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void Overlay(String IP, Integer times)
			throws IOException {
		for (int s = 0; s < times; s++) {
			System.out.println("Pressing 5,6,7 and 8 buttons on device: " + IP);

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 310 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 2 255");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 311 1");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 5 255");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");

			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 310 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 2 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 1 311 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 3 5 0");
			runADBCommand("adb -s " + IP
					+ ":5555 shell sendevent /dev/input/event3 0 0 0");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public static void pullClientLogcat(Emtec Emtec,int loopNumer,String currentTime ) throws IOException{
		runADBCommand("adb -s "+Emtec.getIP() +":5555 logcat *:S Playcast->C:/Testes/"+currentTime+"/"+Emtec.getName()+"_"+loopNumer+"_Logcat.txt");
		System.out.println("Saved logcat for device "+Emtec.getName());
			
	}
}
