package practice.proj;

import java.awt.geom.Point2D;

import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;

public class Main {
	
	public static void main(String[] args) {
		// 투형 정보 입력(중부원점 직각좌표 투형정보)
		String[] proj4 = new String[] {
		        "+proj=tmerc",
		        "+lat_0=38N",
		        "+lon_0=127.00289027777777777776E",
		        "+ellps=bessel",
		        "+units=m",
		        "+x_0=200000",
		        "+y_0=500000",
		        "+k=1.0"
		};

		Projection proj = ProjectionFactory.fromPROJ4Specification(proj4);
		
	    Projection projection = ProjectionFactory.fromPROJ4Specification(
	            new String[] {
	                "+proj=tmerc", 
	                "+lat_0=38", 
	                "+lon_0=127.5", 
	                "+k=0.9996", 
	                "+x_0=1000000", 
	                "+y_0=2000000",
	                "+ellps=GRS80", 
	                "+units=m +no_defs"
	            });

	    // 미아지구대 기준으로 테스트 해봄
	    // 약간 오차가있지만 화장실 위치랑 미아지구대 출입구랑은 다를 수 있으니 감안하면 된다고 보면 될 거 같음
	    
		Point2D.Double srcProjec = new Point2D.Double(
				127.028265783, 37.620154692);
		Point2D.Double dstProject = projection.transform(srcProjec, new Point2D.Double());

		System.out.println("GRS80 : " + dstProject);

		srcProjec = new Point2D.Double(958373.896842, 1957963.322347);
//		srcProjec = new Point2D.Double(1957963.322347, 958373.896842);
		dstProject = projection.inverseTransform(srcProjec, new Point2D.Double());

		System.out.println("WGS84 : " + dstProject);
	}
}
