package brian.model;

import java.io.Serializable;

public class HKHienTai implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
