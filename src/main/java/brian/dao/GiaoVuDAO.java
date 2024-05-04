package brian.dao;

import java.util.List;

import brian.model.GiaoVu;

public interface GiaoVuDAO {
	public List<GiaoVu> getAllGiaoVu();
	public GiaoVu getGiaoVu(String maGVu);
	public boolean addGiaoVu(GiaoVu gv);
	public boolean updateGiaoVu(GiaoVu gv);
	public boolean deleteGiaoVu(String maGVu);
	public Object[][] getObjectMatrix();
}
