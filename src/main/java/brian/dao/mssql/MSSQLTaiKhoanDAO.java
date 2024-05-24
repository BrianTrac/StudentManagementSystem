package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.TaiKhoanDAO;
import brian.model.GiaoVu;
import brian.model.SinhVien;
import brian.model.TaiKhoan;

public class MSSQLTaiKhoanDAO implements TaiKhoanDAO{

	@Override
	public List<TaiKhoan> getAllTaiKhoan() {
		List<TaiKhoan> taiKhoanList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from TAIKHOAN";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenTK(resultSet.getString("TenTK"));
				taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
				taiKhoan.setLoai(resultSet.getString("Loai"));
				
				taiKhoanList.add(taiKhoan);
			}
		} catch (Exception e) {
			System.out.println("Error fetching TaiKhoanDAO: " + e.getMessage());
		}
		finally {
			try {
				if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return taiKhoanList;
	}

	@Override
	public TaiKhoan getTaiKhoan(String tenTK) {
		// TODO Auto-generated method stub
		TaiKhoan taiKhoan = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from TAIKHOAN where TenTK = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tenTK);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				taiKhoan = new TaiKhoan();
				taiKhoan.setTenTK(resultSet.getString("TenTK"));
				taiKhoan.setMatKhau(resultSet.getString("MatKhau"));
				taiKhoan.setLoai(resultSet.getString("Loai"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching TaiKhoanDAO: " + e.getMessage());
		}
		finally {
			try {
				if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return taiKhoan;
	}

	@Override
	public GiaoVu getGiaoVu(String tenTK) {
		// TODO Auto-generated method stub
		GiaoVu giaoVu = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select gv.* "
				+ "from GIAOVU gv "
				+ "join TAIKHOAN tk on tk.TenTK = gv.MaGVu "
				+ "where tk.TenTK = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tenTK);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				giaoVu = new GiaoVu();
				giaoVu.setMaGVu(Main.taiKhoanDAO.getTaiKhoan(resultSet.getString("MaGVu")));
				giaoVu.setTenGVu(resultSet.getString("TenGVu"));
				giaoVu.setGioiTinh(resultSet.getString("GioiTinh"));
				giaoVu.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				giaoVu.setDiaChi(resultSet.getString("DiaChi"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching TaiKhoanDAO: " + e.getMessage());
		}
		finally {
			try {
				if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return giaoVu;
	}

	@Override
	public SinhVien getSinhVien(String tenTK) {
		// TODO Auto-generated method stub
		SinhVien sinhVien = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select sv.* "
				+ "from SINHVIEN sv "
				+ "join TAIKHOAN tk on tk.TenTK = sv.MaSV "
				+ "where tk.TenTK = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tenTK);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				sinhVien = new SinhVien();
				sinhVien.setMaSV(Main.taiKhoanDAO.getTaiKhoan(resultSet.getString("MaSV")));
				sinhVien.setTenSV(resultSet.getString("TenSV"));
				sinhVien.setGioiTinh(resultSet.getString("GioiTinh"));
				sinhVien.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				sinhVien.setDiaChi(resultSet.getString("DiaChi"));
				sinhVien.setKhoa(resultSet.getString("Khoa"));
				sinhVien.setGhiChu(resultSet.getString("GhiChu"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching TaiKhoanDAO: " + e.getMessage());
		}
		finally {
			try {
				if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return sinhVien;
	}

	@Override
	public boolean addTaiKhoan(TaiKhoan tk) {
		// TODO Auto-generated method stub
		if (getTaiKhoan(tk.getTenTK()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into TAIKHOAN(TenTK, Loai) values(?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, tk.getTenTK());
		//	preparedStatement.setString(2, tk.getMatKhau());
			preparedStatement.setString(2, tk.getLoai());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding TaiKhoan: " + e.getMessage());
	        return false;
		}
		finally {
			try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return true;
	}

	@Override
	public boolean updateTaiKhoan(TaiKhoan tk) {
		// TODO Auto-generated method 
		if (getTaiKhoan(tk.getTenTK()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update TAIKHOAN "
					+ "set TenTK = ?, MatKhau = ?, Loai = ?"
					+ "where TenTK = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, tk.getTenTK());
			preparedStatement.setString(2, tk.getMatKhau());
			preparedStatement.setString(3, tk.getLoai());
			preparedStatement.setString(4, tk.getTenTK());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating TaiKhoan: " + e.getMessage());
	        return false;
		}
		finally {
			try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return true;
	}

	@Override
	public boolean deleteTaiKhoan(String tk) {
		// TODO Auto-generated method stub
		if (getTaiKhoan(tk) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from TAIKHOAN"
					+ "where TenTK = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, tk);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting TaiKhoan: " + e.getMessage());
	        return false;
		}
		finally {
			try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
			} catch (Exception e) {
				System.out.println("Error closing resources: " + e.getMessage());
			}
		}
		
		return true;
	}

	@Override
	public Object[][] getObjectMatrix() {
		// TODO Auto-generated method stub
		List<TaiKhoan> taiKhoanList = getAllTaiKhoan();
		Object[][] data = new Object[taiKhoanList.size()][3];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = taiKhoanList.get(i).getTenTK();
			data[i][1] = taiKhoanList.get(i).getMatKhau();
			data[i][2] = taiKhoanList.get(i).getLoai();
		}
		
		return data;
	}
}
