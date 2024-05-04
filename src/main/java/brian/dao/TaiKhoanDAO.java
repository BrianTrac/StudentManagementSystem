package brian.dao;

import java.util.List;

import brian.model.GiaoVu;
import brian.model.SinhVien;
import brian.model.TaiKhoan;

public interface TaiKhoanDAO {
	public List<TaiKhoan> getAllTaiKhoan();
	public TaiKhoan getTaiKhoan(String tenTK);
	public GiaoVu getGiaoVu(String tenTK);
	public SinhVien getSinhVien(String tenTK);
	public boolean addTaiKhoan(TaiKhoan tk);
	public boolean updateTaiKhoan(TaiKhoan tk);
	public boolean deleteTaiKhoan(String tk);
	public Object[][] getObjectMatrix();
}
