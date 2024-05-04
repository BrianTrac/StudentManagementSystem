package brian.model;

public class MonHoc {
	private String maMH;
	private String tenMH;
	private int soTC;
	
	public MonHoc() {
		super();
	}

	public MonHoc(String maMH, String tenMH, int soTC) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.soTC = soTC;
	}

	public String getMaMH() {
		return maMH;
	}

	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getTenMH() {
		return tenMH;
	}

	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}

	public int getSoTC() {
		return soTC;
	}

	public void setSoTC(int soTC) {
		this.soTC = soTC;
	}
	
}
