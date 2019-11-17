import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class Camera {
	
	public static void capture() throws IOException {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640, 480));
		webcam.open();
		ImageIO.write(webcam.getImage(), "PNG", new File("hello-world.png"));
		webcam.close();
		
		
	}

}
