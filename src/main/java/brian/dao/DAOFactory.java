package brian.dao;

import brian.dao.mssql.MSSQLDAOFactory;

public abstract class DAOFactory {
	public static final int MSSQL = 1;
	
	public abstract DKHPDAO getDKHPDAO();
	public abstract GiaoVienDAO getGiaoVienDAO();
	public abstract GiaoVuDAO getGiaoVuDAO();
	public abstract HKHienTaiDAO geHKHienTaiDAO();
	public abstract HocKiDAO getHocKiDAO();
	public abstract HocPhanDAO getHocPhanDAO();
	public abstract LopDAO getLopDAO();
	public abstract MoDKHPDAO getMoDKHPDAO();
	public abstract MonHocDAO getMonHocDAO();
	public abstract SinhVienDAO getSinhVienDAO();
	public abstract TaiKhoanDAO getTaiKhoanDAO();
	
	public static DAOFactory getDaoFactory(int whichFactory) {
		switch(whichFactory) {
		case MSSQL:
			return new MSSQLDAOFactory();
		default :
			return null;
		}
	}
}
