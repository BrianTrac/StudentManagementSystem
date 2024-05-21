package brian.model;

import java.io.Serializable;
import java.util.Date;

public class SinhVien implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private TaiKhoan maSV;
	private String tenSV; 
	private String gioiTinh;
	private Date ngaySinh;
	private String khoa;
	private String diaChi;
	private String ghiChu;
	
	public SinhVien() {
		super();
	}

	public SinhVien(TaiKhoan maSV, String tenSV, String gioiTinh, Date ngaySinh, String khoa, String diaChi,
			String ghiChu) {
		super();
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.khoa = khoa;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
	}

	public TaiKhoan getMaSV() {
		return maSV;
	}

	public void setMaSV(TaiKhoan maSV) {
		this.maSV = maSV;
	}

	public String getTenSV() {
		return tenSV;
	}

	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
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

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
