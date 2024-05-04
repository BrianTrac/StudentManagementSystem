package brian.model;

import java.util.Date;

public class GiaoVien {
	private String maGV;
	private String tenGV;
	private String gioiTinh;
	private Date ngaySinh;
	private String khoa;
	private String diaChi;
	private String ghiChu;
	
	public GiaoVien() {
		super();
	}

	public GiaoVien(String maGV, String tenGV, String gioiTinh, Date ngaySinh, String khoa, String diaChi,
			String ghiChu) {
		super();
		this.maGV = maGV;
		this.tenGV = tenGV;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.khoa = khoa;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
	}

	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}

	public String getTenGV() {
		return tenGV;
	}

	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
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
