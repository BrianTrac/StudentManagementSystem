package brian.dao;

import java.util.List;

import brian.model.SinhVien;
import brian.model.HocPhan;

public interface HocPhanDAO {
	public List<HocPhan> getAllHocPhan();
	public HocPhan getHocPhan(String maHP);
	public boolean addHocPhan(HocPhan hp);
	public boolean updateHocPhan(HocPhan hp);
	public boolean deleteHocPhan(String maHP);
	public List<SinhVien> getSinhVienListInHocPhan(String maHP);
	public List<SinhVien> getSinhVienListNotInHocPhan(String maHP);
	public Object[][] getObjectMatrix();
	public Object[][] getObjectMatrix(List<HocPhan> hocPhanList);
}
