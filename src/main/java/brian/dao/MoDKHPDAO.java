package brian.dao;

import java.util.List;

import brian.model.MoDKHP;

public interface MoDKHPDAO {
	public List<MoDKHP> getAllMoDKHP();
	public MoDKHP getMoDKHP(String maHK, int soLanMo);
	public boolean addMoDKHP(MoDKHP moDKHP);
	public boolean updateMoDKHP(MoDKHP moDKHP);
	public boolean deleteMoDKHP(String maHK, int soLanMo);
	public Object[][] getObjectMatrix();
}
