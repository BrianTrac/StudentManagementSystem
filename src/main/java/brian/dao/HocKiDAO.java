package brian.dao;

import java.util.List;

import brian.model.HocKi;

public interface HocKiDAO {
	public List<HocKi> getAllHocKi();
	public HocKi getHocKi(String maHK);
	public boolean addHocKi(HocKi hk);
	public boolean updateHocKi(HocKi hk);
	public boolean deleteHocKi(String maHK);
	public Object[][] getObjectMatrix();
}
