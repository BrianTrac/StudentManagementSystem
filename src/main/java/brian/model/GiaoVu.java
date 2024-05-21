package brian.model;

import java.io.Serializable;
import java.util.Date;

public class GiaoVu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TaiKhoan maGVu;
	private String tenGVu;
	private String gioiTinh;
	private Date ngaySinh;
	private String diaChi;
	
	// write setter and getter
	public GiaoVu() {
		// TODO Auto-generated constructor stub
	}
	
	public GiaoVu(TaiKhoan maGVu, String tenGVu, String gioiTinh, Date ngaySinh, String diaChi) {
		super();
		this.maGVu = maGVu;
		this.tenGVu = tenGVu;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
	}

	public TaiKhoan getMaGVu() {
		return maGVu;
	}

	public void setMaGVu(TaiKhoan maGVu) {
		this.maGVu = maGVu;
	}

	public String getTenGVu() {
		return tenGVu;
	}

	public void setTenGVu(String tenGVu) {
		this.tenGVu = tenGVu;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date date) {
		this.ngaySinh = date;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
		
}
