package brian.model;

public class Lop {
	private String maLop;
	private String tenLop;
	private int soSV;
	
	public Lop() {
		super();
	}

	public Lop(String maLop, String tenLop, int soSV) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.soSV = soSV;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public int getSoSV() {
		return soSV;
	}

	public void setSoSV(int soSV) {
		this.soSV = soSV;
	}
	
}
