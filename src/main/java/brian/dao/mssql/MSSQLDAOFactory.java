package brian.dao.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

public class MSSQLDAOFactory extends DAOFactory{
	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DB_StudentManagementSystem_22120120;user=sa;password=password;encrypt=true;trustServerCertificate=true";
	
	static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public static Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			System.out.println("Connection failed. Error: " + e.getMessage());
		}
		
		return connection;
	}
	
	// Add a method to close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection. Error: " + e.getMessage());
            }
        }
    }
	
	@Override
	public DKHPDAO getDKHPDAO() {
		// TODO Auto-generated method stub
		return new MSSQLDKHPDAO();
	}

	@Override
	public GiaoVienDAO getGiaoVienDAO() {
		// TODO Auto-generated method stub
		return new MSSQLGiaoVienDAO();
	}

	@Override
	public GiaoVuDAO getGiaoVuDAO() {
		// TODO Auto-generated method stub
		return new MSSQLGiaoVuDAO();
	}

	@Override
	public HKHienTaiDAO geHKHienTaiDAO() {
		// TODO Auto-generated method stub
		return new MSSQLHKHienTaiDAO();
	}

	@Override
	public HocKiDAO getHocKiDAO() {
		// TODO Auto-generated method stub
		return new MSSQLHocKiDAO();
	}

	@Override
	public HocPhanDAO getHocPhanDAO() {
		// TODO Auto-generated method stub
		return new MSSQLHocPhanDAO();
	}

	@Override
	public LopDAO getLopDAO() {
		// TODO Auto-generated method stub
		return new MSSQLLopDAO();
	}

	@Override
	public MoDKHPDAO getMoDKHPDAO() {
		// TODO Auto-generated method stub
		return new MSSQLMoDKHPDAO();
	}

	@Override
	public MonHocDAO getMonHocDAO() {
		// TODO Auto-generated method stub
		return new MSSQLMonHocDAO();
	}

	@Override
	public SinhVienDAO getSinhVienDAO() {
		// TODO Auto-generated method stub
		return new MSSQLSinhVienDAO();
	}

	@Override
	public TaiKhoanDAO getTaiKhoanDAO() {
		// TODO Auto-generated method stub
		return new MSSQLTaiKhoanDAO();
	}

}
