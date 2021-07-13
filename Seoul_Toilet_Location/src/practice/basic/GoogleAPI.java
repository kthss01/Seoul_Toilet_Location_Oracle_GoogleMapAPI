package practice.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class GoogleAPI {

	private String key = "";

	public GoogleAPI() {
		File f = new File("resources/key.secret");
		if (f.exists()) {
			readKey(f.getPath());
		} else {
			key = JOptionPane.showInputDialog("key : ");
		}
	}

	public void downloadMap(String location) {
		try {
			String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center="
					+ URLEncoder.encode(location, "UTF-8") + "&zoom=12&size=400x400&key=" + key;
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ImageIcon getMap(String location) {
		return new ImageIcon(
				(new ImageIcon(location)).getImage().getScaledInstance(612, 612, java.awt.Image.SCALE_SMOOTH));
	}

	public void fileDelete(String fileName) {
		File f = new File(fileName);
		f.delete();
	}

	private void readKey(String path) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			key = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
