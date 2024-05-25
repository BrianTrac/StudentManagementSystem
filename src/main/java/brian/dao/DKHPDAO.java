package brian.dao;

import java.util.List;

import brian.model.DKHP;
import brian.model.HocPhan;
import brian.model.SinhVien;

public interface DKHPDAO {
	public List<DKHP> getAllDKHP();
	public DKHP getDKHP(String maHP, String maSV);
	public List<DKHP> getDKHPByMaSV(String maSV);
	public boolean addDKHP(DKHP dkhp);
	public boolean addDKHP(String maHP, String maSV);
	public boolean deleteDKHP(String maHP, String maSV);
	public boolean updateDKHP(DKHP dkhp);
	public Object[][] getObjectMatrix();
	public Object[][] getObjectMatrix(List<DKHP> dkhpList);
}
