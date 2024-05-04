package brian.dao;

import java.util.List;

import brian.model.MonHoc;

public interface MonHocDAO {
	public List<MonHoc> getAllMonHoc();
	public MonHoc getMonHoc(String maMH);
	public boolean addMonHoc(MonHoc mh);
	public boolean updateMonHoc(MonHoc mh);
	public boolean deleteMonHoc(String maMH);
	public Object[][] getObjectMatrix();
}
