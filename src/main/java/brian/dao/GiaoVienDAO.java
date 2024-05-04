package brian.dao;

import java.util.List;

import brian.model.GiaoVien;

public interface GiaoVienDAO {
	public List<GiaoVien> getAllGiaoVien();
	public GiaoVien getGiaoVien(String maGV);
	public boolean addGiaoVien(GiaoVien gv);
	public boolean updateGiaoVien(GiaoVien gv);
	public boolean deleteGiaoVien(String maGV);
	public Object[][] getObjectMatrix();
}
