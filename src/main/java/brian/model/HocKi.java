package brian.model;

import java.io.Serializable;
import java.util.Date;

public class HocKi implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String maHK;
	private String tenHK;
	private int namHoc;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	
	public HocKi() {
		super();
	}

	public HocKi(String maHK, String tenHK, int namHoc, Date ngayBatDau, Date ngayKetThuc) {
		super();
		this.maHK = maHK;
		this.tenHK = tenHK;
		this.namHoc = namHoc;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMaHK() {
		return maHK;
	}

	public void setMaHK(String maHK) {
		this.maHK = maHK;
	}

	public String getTenHK() {
		return tenHK;
	}

	public void setTenHK(String tenHK) {
		this.tenHK = tenHK;
	}

	public int getNamHoc() {
		return namHoc;
	}

	public void setNamHoc(int namHoc) {
		this.namHoc = namHoc;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date date) {
		this.ngayBatDau = date;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
}
