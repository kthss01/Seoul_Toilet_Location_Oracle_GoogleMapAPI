package practice.location_distance;

public class Main {

	public static void main(String[] args) {
		// 마일(Mile) 단위
		double distanceMile = distance(37.504198, 127.047967, 37.501025, 127.037701, "");

		// 미터(Meter) 단위
		double distanceMeter = distance(37.504198, 127.047967, 37.501025, 127.037701, "meter");

		// 킬로미터(Kilo Meter) 단위
		double distanceKiloMeter = distance(37.504198, 127.047967, 37.501025, 127.037701, "kilometer");

		System.out.println(distanceMile);
		System.out.println(distanceMeter);
		System.out.println(distanceKiloMeter);
		
		// test
		double d = distance(37.620154692, 127.028265783, 37.637437605, 127.027308394, "meter");
		System.out.println(d);
		
		double d2 = distance(33.504274, 126.529182, 33.524383, 126.544333, "meter");
		System.out.println(d2);
		
	}

	/**
	 * 두 지점간의 거리 계산
	 *
	 * @param lat1 지점 1 위도
	 * @param lon1 지점 1 경도
	 * @param lat2 지점 2 위도
	 * @param lon2 지점 2 경도
	 * @param unit 거리 표출단위
	 * @return
	 */
	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		if (unit.equals("kilometer")) {
			dist = dist * 1.609344;
		} else if (unit.equals("meter")) {
			dist = dist * 1609.344;
		}

		return (dist);
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
//		return Math.toRadians(deg);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
