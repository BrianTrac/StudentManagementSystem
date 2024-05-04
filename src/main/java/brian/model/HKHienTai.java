package brian.model;

public class HKHienTai {
	private String maHKHT;
	private HocKi maHK;
	
	public HKHienTai() {
		super();
	}

	public HKHienTai(String maHKHT, HocKi maHK) {
		super();
		this.maHKHT = maHKHT;
		this.maHK = maHK;
	}

	public String getMaHKHT() {
		return maHKHT;
	}

	public void setMaHKHT(String maHKHT) {
		this.maHKHT = maHKHT;
	}

	public HocKi getMaHK() {
		return maHK;
	}

	public void setMaHK(HocKi maHK) {
		this.maHK = maHK;
	}
	
}
