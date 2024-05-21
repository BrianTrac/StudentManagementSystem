package brian.model;

import java.io.Serializable;
import java.util.Date;

public class MoDKHP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private HocKi maHK;
	private int soLanMo;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	
	public MoDKHP() {
		super();
	}

	public MoDKHP(HocKi maHK, int soLanMo, Date ngayBatDau, Date ngayKetThuc) {
		super();
		this.maHK = maHK;
		this.soLanMo = soLanMo;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}

	public HocKi getMaHK() {
		return maHK;
	}

	public void setMaHK(HocKi maHK) {
		this.maHK = maHK;
	}

	public int getSoLanMo() {
		return soLanMo;
	}

	public void setSoLanMo(int soLanMo) {
		this.soLanMo = soLanMo;
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
