package AndroidAutomation;
import java.io.IOException;


public class Device {
	String name;
	String vender;
	String IP;
	String gamepad;
	boolean connected;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getGamepad() {
		return gamepad;
	}
	public void setGamepad(String gamepad) {
		this.gamepad = gamepad;
	}
	public boolean getConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public void MoveRight(String IP, Integer times)
			throws IOException {	
	}
	public void MoveLeft(String IP, Integer times)
			throws IOException {	
	}
	public void MoveUp(String IP, Integer times)
			throws IOException {
	}
	public void MoveDown(String IP, Integer times)
			throws IOException {
	}
	public void XButton(String IP, Integer times)
			throws IOException {
	}
	public void AButton(String IP, Integer times)
			throws IOException {
	}
	public void BButton(String IP, Integer times)
			throws IOException {
	}
	public void YButton(String IP, Integer times)
			throws IOException {
	}
	public void LBButton(String IP, Integer times)
			throws IOException {
		
	}
	public void LTButton(String IP, Integer times)
			throws IOException {
	}
	public void RBButton(String IP, Integer times)
			throws IOException {
	}
	public void RTButton(String IP, Integer times)
			throws IOException {
	}
	public void Overlay(String IP, Integer times)
			throws IOException {
	}
	public void pullClientLogcat(Device device,int loopNumer,String currentTime ) {
		
	}
	
}
