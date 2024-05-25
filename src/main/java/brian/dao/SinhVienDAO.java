package brian.dao;

import java.util.List;

import brian.model.HocPhan;
import brian.model.SinhVien;

public interface SinhVienDAO {
	public List<SinhVien> getAllSinhVien();
	public SinhVien getSinhVien(String maSV);
	public List<SinhVien> getSinhVienByYear(String nam);
	public boolean addSinhVien(SinhVien sv);
	public boolean updateSinhVien(SinhVien sv);
	public boolean deleteSinhVien(String maSV);
	public Object[][] getObjectMatrix();
	public Object[][] getObjectMatrix(List<SinhVien> sinhVienList);
}
