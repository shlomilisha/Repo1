package AndroidAutomation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;


public class PrepareForTest {
	
	@Before
	public void prepareForTest() throws IOException {
		Path path = Paths.get("C:/Testes/" + Android.currentTime);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				System.out.println("Directory " + Android.currentTime + " is created!");
			} catch (IOException e) {
				// fail to create directory
				System.out.println("fail to create directory " + Android.currentTime);
				e.printStackTrace();
			}
		}
	}
}
