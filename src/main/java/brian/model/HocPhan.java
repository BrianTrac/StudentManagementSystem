package brian.model;

import java.io.Serializable;

public class HocPhan implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String maHP;
	private MonHoc maMH;
	private Lop maLop;
	private HocKi maHK;
	private GiaoVien maGV;
	private int siSo;
	private int daDK;
	private String khoa;
	private String thu;
	private String khungGio;
	private String phong;
	private String diaDiem;
	
	public HocPhan() {
		super();
	}

	public HocPhan(String maHP, MonHoc maMH, Lop maLop, HocKi maHK, GiaoVien maGV, int siSo, int daDK, String khoa,
			String thu, String khungGio, String phong, String diaDiem) {
		super();
		this.maHP = maHP;
		this.maMH = maMH;
		this.maLop = maLop;
		this.maHK = maHK;
		this.maGV = maGV;
		this.siSo = siSo;
		this.daDK = daDK;
		this.khoa = khoa;
		this.thu = thu;
		this.khungGio = khungGio;
		this.phong = phong;
		this.diaDiem = diaDiem;
	}

	public String getMaHP() {
		return maHP;
	}

	public void setMaHP(String maHP) {
		this.maHP = maHP;
	}

	public MonHoc getMaMH() {
		return maMH;
	}

	public void setMaMH(MonHoc maMH) {
		this.maMH = maMH;
	}

	public Lop getMaLop() {
		return maLop;
	}

	public void setMaLop(Lop maLop) {
		this.maLop = maLop;
	}

	public HocKi getMaHK() {
		return maHK;
	}

	public void setMaHK(HocKi maHK) {
		this.maHK = maHK;
	}

	public GiaoVien getMaGV() {
		return maGV;
	}

	public void setMaGV(GiaoVien maGV) {
		this.maGV = maGV;
	}

	public int getSiSo() {
		return siSo;
	}

	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}

	public int getDaDK() {
		return daDK;
	}

	public void setDaDK(int daDK) {
		this.daDK = daDK;
	}

	public String getKhoa() {
		return khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getKhungGio() {
		return khungGio;
	}

	public void setKhungGio(String khungGio) {
		this.khungGio = khungGio;
	}

	public String getPhong() {
		return phong;
	}

	public void setPhong(String phong) {
		this.phong = phong;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	
}
