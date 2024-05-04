package brian;

import java.text.SimpleDateFormat;

import brian.dao.DAOFactory;
import brian.dao.DKHPDAO;
import brian.dao.GiaoVienDAO;
import brian.dao.GiaoVuDAO;
import brian.dao.HKHienTaiDAO;
import brian.dao.HocKiDAO;
import brian.dao.HocPhanDAO;
import brian.dao.LopDAO;
import brian.dao.MoDKHPDAO;
import brian.dao.MonHocDAO;
import brian.dao.SinhVienDAO;
import brian.dao.TaiKhoanDAO;




public class Main {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static DAOFactory mssqlDaoFactory = DAOFactory.getDaoFactory(DAOFactory.MSSQL);
	public static TaiKhoanDAO taiKhoanDAO = mssqlDaoFactory.getTaiKhoanDAO();
	public static SinhVienDAO sinhVienDAO = mssqlDaoFactory.getSinhVienDAO();
	public static GiaoVienDAO giaoVienDAO = mssqlDaoFactory.getGiaoVienDAO();
	public static GiaoVuDAO giaoVuDAO = mssqlDaoFactory.getGiaoVuDAO();
	public static LopDAO lopDAO = mssqlDaoFactory.getLopDAO();
	public static MonHocDAO monHocDAO = mssqlDaoFactory.getMonHocDAO();
	public static HocKiDAO hocKiDAO = mssqlDaoFactory.getHocKiDAO();
	public static HocPhanDAO hocPhanDAO = mssqlDaoFactory.getHocPhanDAO();
	public static DKHPDAO dkhpdao = mssqlDaoFactory.getDKHPDAO();
	public static MoDKHPDAO moDKHPDAO = mssqlDaoFactory.getMoDKHPDAO();
	public static HKHienTaiDAO hkHienTaiDAO= mssqlDaoFactory.geHKHienTaiDAO();
	
	
	
}
