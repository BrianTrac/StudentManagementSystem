package brian.dao;

import java.util.List;

import brian.model.Lop;

public interface LopDAO {
	public List<Lop> getAllLop();
	public Lop getLop(String maLop);
	public boolean addLop(Lop lop);
	public boolean updateLop(Lop lop);
	public boolean deleteLop(String maLop);
	public Object[][] getObjectMatrix();
}
