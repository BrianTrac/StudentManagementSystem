package brian.model;

public class TaiKhoan {
	private String tenTK;
	private String matKhau;
	private String loai;
	
	public TaiKhoan() {
		
	}
	
	public TaiKhoan(String tenTK, String matKhau, String loai) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.loai = loai;
	}
	
	public String getTenTK() {
		return tenTK;
	}
	
	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}
	
}
