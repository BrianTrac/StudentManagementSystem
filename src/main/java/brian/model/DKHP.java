package brian.model;

import java.util.Date;

public class DKHP {
	private HocPhan maHP;
	private SinhVien maSV;
	private Date thoiGianDKHP;
	private double diem;
	
	public DKHP() {
		super();
	}

	public DKHP(HocPhan maHP, SinhVien maSV, Date thoiGianDKHP, double diem) {
		super();
		this.maHP = maHP;
		this.maSV = maSV;
		this.thoiGianDKHP = thoiGianDKHP;
		this.diem = diem;
	}

	public HocPhan getMaHP() {
		return maHP;
	}

	public void setMaHP(HocPhan maHP) {
		this.maHP = maHP;
	}

	public SinhVien getMaSV() {
		return maSV;
	}

	public void setMaSV(SinhVien maSV) {
		this.maSV = maSV;
	}

	public Date getThoiGianDKHP() {
		return thoiGianDKHP;
	}

	public void setThoiGianDKHP(Date date) {
		this.thoiGianDKHP = date;
	}

	public double getDiem() {
		return diem;
	}

	public void setDiem(double diem) {
		this.diem = diem;
	}
	
}
