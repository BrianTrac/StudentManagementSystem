package brian.dao.mssql;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.SinhVienDAO;
import brian.model.HocPhan;
import brian.model.SinhVien;

public class MSSQLSinhVienDAO implements SinhVienDAO{

	@Override
	public List<SinhVien> getAllSinhVien() {
		// TODO Auto-generated method stub
		List<SinhVien> sinhVienList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from SINHVIEN";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SinhVien sinhVien = new SinhVien();
				sinhVien.setMaSV(Main.taiKhoanDAO.getTaiKhoan(resultSet.getString("MaSV")));
				sinhVien.setTenSV(resultSet.getString("TenSV"));
				sinhVien.setGioiTinh(resultSet.getString("GioiTinh"));
				sinhVien.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				sinhVien.setDiaChi(resultSet.getString("DiaChi"));
				sinhVien.setKhoa(resultSet.getString("Khoa"));
				sinhVien.setGhiChu(resultSet.getString("GhiChu"));
				
				sinhVienList.add(sinhVien);
			}
		} catch (Exception e) {
			System.out.println("Error fetching SinhVienDAO: " + e.getMessage());
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
		
		return sinhVienList;
	}
	
	@Override
	public SinhVien getSinhVien(String maSV) {
		// TODO Auto-generated method stub
		SinhVien sinhVien = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from SINHVIEN where MaSV = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maSV);
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
			System.out.println("Error fetching SinhVienDAO: " + e.getMessage());
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
	public List<SinhVien> getSinhVienByYear(String nam) {
		// TODO Auto-generated method stub
		List<SinhVien> sinhVienList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select sv.* "
				+    "from SINHVIEN sv "
				+ 	 "join DKHP dkhp on sv.MaSV = dkhp.MaSV "
				+ 	 "join HOCPHAN hp on dkhp.MaHP = hp.MaHP "
				+ 	 "where hp.Khoa = ? ";
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nam);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				SinhVien sinhVien = new SinhVien();
				sinhVien.setMaSV(Main.taiKhoanDAO.getTaiKhoan(resultSet.getString("MaSV")));
				sinhVien.setTenSV(resultSet.getString("TenSV"));
				sinhVien.setGioiTinh(resultSet.getString("GioiTinh"));
				sinhVien.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				sinhVien.setDiaChi(resultSet.getString("DiaChi"));
				sinhVien.setKhoa(resultSet.getString("Khoa"));
				sinhVien.setGhiChu(resultSet.getString("GhiChu"));
				
				sinhVienList.add(sinhVien);
			}
		} catch (Exception e) {
			System.out.println("Error fetching SinhVienDAO: " + e.getMessage());
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
		
		return sinhVienList;
	}

	@Override
	public boolean addSinhVien(SinhVien sv) {
		// TODO Auto-generated method stub
		if (getSinhVien(sv.getMaSV().getTenTK()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into SINHVIEN(MaSV, TenSV, GioiTinh, NgaySinh, Khoa, DiaChi, GhiChu) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, sv.getMaSV().getTenTK());
			preparedStatement.setString(2, sv.getTenSV());
			preparedStatement.setString(3, sv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(sv.getNgaySinh().getTime()); 

			preparedStatement.setDate(4, sqlNgaySinh);
			preparedStatement.setString(5, sv.getKhoa());
			preparedStatement.setString(6, sv.getDiaChi());
			preparedStatement.setString(7, sv.getGhiChu());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding SinhVien: " + e.getMessage());
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
	public boolean updateSinhVien(SinhVien sv) {
		// TODO Auto-generated method stub
		if (getSinhVien(sv.getMaSV().getTenTK()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update SINHVIEN "
					+ "set TenSV = ?, GioiTinh = ?, NgaySinh = ?, Khoa = ?, DiaChi = ?, GhiChu = ?"
					+ " where MaSV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, sv.getTenSV());
			preparedStatement.setString(2, sv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(sv.getNgaySinh().getTime()); 

			preparedStatement.setDate(3, sqlNgaySinh);
			preparedStatement.setString(4, sv.getKhoa());
			preparedStatement.setString(5, sv.getDiaChi());
			preparedStatement.setString(6, sv.getGhiChu());
			preparedStatement.setString(7, sv.getMaSV().getTenTK());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating SinhVien: " + e.getMessage());
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
	public boolean deleteSinhVien(String maSV) {
		// TODO Auto-generated method stub
		if (getSinhVien(maSV) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from SINHVIEN"
					+ " where MaSV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maSV);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting SinhVien: " + e.getMessage());
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
		List<SinhVien> sinhVienList = getAllSinhVien();
		Object[][] data = new Object[sinhVienList.size()][7];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = sinhVienList.get(i).getMaSV().getTenTK();
			data[i][1] = sinhVienList.get(i).getTenSV();
			data[i][2] = sinhVienList.get(i).getGioiTinh();
			data[i][3] = Main.simpleDateFormat.format(sinhVienList.get(i).getNgaySinh());
			data[i][4] = sinhVienList.get(i).getKhoa();
			data[i][5] = sinhVienList.get(i).getDiaChi();
			data[i][6] = sinhVienList.get(i).getGhiChu();
		}
		
		return data;
	}
	
	@Override
	public Object[][] getObjectMatrix(List<SinhVien> sinhVienList) {
		// TODO Auto-generated method stub
		Object[][] data = new Object[sinhVienList.size()][7];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = sinhVienList.get(i).getMaSV().getTenTK();
			data[i][1] = sinhVienList.get(i).getTenSV();
			data[i][2] = sinhVienList.get(i).getGioiTinh();
			data[i][3] = Main.simpleDateFormat.format(sinhVienList.get(i).getNgaySinh());
			data[i][4] = sinhVienList.get(i).getKhoa();
			data[i][5] = sinhVienList.get(i).getDiaChi();
			data[i][6] = sinhVienList.get(i).getGhiChu();
		}
		
		return data;
	}

}
