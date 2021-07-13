package practice.advance.mvc.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;

import practice.advance.mvc.model.vo.GoogleMap;

public class GoogleMapTemplate {
	
	private static GoogleMap map = null;
	
	public static GoogleMap Map() {
		if (map == null) {
			map = new GoogleMap();
			map.sample();
		}
		return map;
	}
	
	public static void downloadMap() {
		if(Map().isChanged() == false) {
			return;
		}
		
		try {
			String imageURL = map.toString();
			System.out.println(imageURL); // test
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(map.getCenter());
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
	
	public static ImageIcon getMap() {
		Map().setChanged(false);
		
		return new ImageIcon(
				(new ImageIcon(map.getCenter())).getImage().getScaledInstance(Map().getSizeX(), Map().getSizeY(), java.awt.Image.SCALE_SMOOTH)); // 이거 사이즈 좀 잘 보자
	}
	
	public static void fileDelete() {
		File f = new File(map.getCenter());
		f.delete();
	}
}
