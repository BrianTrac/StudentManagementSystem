package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.GiaoVienDAO;
import brian.model.GiaoVien;

public class MSSQLGiaoVienDAO implements GiaoVienDAO {

	@Override
	public List<GiaoVien> getAllGiaoVien() {
		// TODO Auto-generated method stub
		List<GiaoVien> giaoVienList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from GIAOVIEN";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				GiaoVien giaoVien = new GiaoVien();
				giaoVien.setMaGV(resultSet.getString("MaGV"));
				giaoVien.setTenGV(resultSet.getString("TenGV"));
				giaoVien.setGioiTinh(resultSet.getString("GioiTinh"));
				giaoVien.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				giaoVien.setDiaChi(resultSet.getString("DiaChi"));
				giaoVien.setKhoa(resultSet.getString("Khoa"));
				giaoVien.setGhiChu(resultSet.getString("GhiChu"));
				
				giaoVienList.add(giaoVien);
			}
		} catch (Exception e) {
			System.out.println("Error fetching GiaoVienDAO: " + e.getMessage());
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
		
		return giaoVienList;
	}

	@Override
	public GiaoVien getGiaoVien(String maGV) {
		// TODO Auto-generated method stub
		GiaoVien giaoVien = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from GIAOVIEN where MaGV = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maGV);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				giaoVien = new GiaoVien();
				giaoVien.setMaGV(resultSet.getString("MaGV"));
				giaoVien.setTenGV(resultSet.getString("TenGV"));
				giaoVien.setGioiTinh(resultSet.getString("GioiTinh"));
				giaoVien.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				giaoVien.setDiaChi(resultSet.getString("DiaChi"));
				giaoVien.setKhoa(resultSet.getString("Khoa"));
				giaoVien.setGhiChu(resultSet.getString("GhiChu"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching GiaoVienDAO: " + e.getMessage());
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
		
		return giaoVien;
	}

	@Override
	public boolean addGiaoVien(GiaoVien gv) {
		// TODO Auto-generated method stub
		if (getGiaoVien(gv.getMaGV()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into GIAOVIEN(MaGV, TenGV, GioiTinh, NgaySinh, Khoa, DiaChi, GhiChu) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, gv.getMaGV());
			preparedStatement.setString(2, gv.getTenGV());
			preparedStatement.setString(3, gv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(gv.getNgaySinh().getTime()); 

			preparedStatement.setDate(4, sqlNgaySinh);
			preparedStatement.setString(5, gv.getKhoa());
			preparedStatement.setString(6, gv.getDiaChi());
			preparedStatement.setString(7, gv.getGhiChu());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding GiaoVien: " + e.getMessage());
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
	public boolean updateGiaoVien(GiaoVien gv) {
		// TODO Auto-generated method stub
		if (getGiaoVien(gv.getMaGV()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update GIAOVIEN "
					+ "set TenGV = ?, GioiTinh = ?, NgaySinh = ?, Khoa = ?, DiaChi = ?, GhiChu = ?"
					+ " where MaGV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, gv.getTenGV());
			preparedStatement.setString(2, gv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(gv.getNgaySinh().getTime()); 
			
			preparedStatement.setDate(3, sqlNgaySinh);	
			preparedStatement.setString(4, gv.getKhoa());
			preparedStatement.setString(5, gv.getDiaChi());
			preparedStatement.setString(6, gv.getGhiChu());
			preparedStatement.setString(7, gv.getMaGV());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating GiaoVien: " + e.getMessage());
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
	public boolean deleteGiaoVien(String maGV) {
		// TODO Auto-generated method stub
		if (getGiaoVien(maGV) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from GIAOVIEN"
					+ " where MaGV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maGV);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting GiaoVien: " + e.getMessage());
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
		List<GiaoVien> giaoVienList = getAllGiaoVien();
		Object[][] data = new Object[giaoVienList.size()][7];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = giaoVienList.get(i).getMaGV();
			data[i][1] = giaoVienList.get(i).getTenGV();
			data[i][2] = giaoVienList.get(i).getGioiTinh();
			data[i][3] = Main.simpleDateFormat.format(giaoVienList.get(i).getNgaySinh());
			data[i][4] = giaoVienList.get(i).getKhoa();
			data[i][5] = giaoVienList.get(i).getDiaChi();
			data[i][6] = giaoVienList.get(i).getGhiChu();
		}
		
		return data;
	}

}
