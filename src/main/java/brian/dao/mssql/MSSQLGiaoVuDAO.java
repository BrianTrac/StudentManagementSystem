package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.GiaoVuDAO;
import brian.model.GiaoVu;

public class MSSQLGiaoVuDAO implements GiaoVuDAO{

	@Override
	public List<GiaoVu> getAllGiaoVu() {
		// TODO Auto-generated method stub
		List<GiaoVu> giaoVuList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from GIAOVU";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				GiaoVu giaoVu = new GiaoVu();
				giaoVu.setMaGVu(Main.taiKhoanDAO.getTaiKhoan(resultSet.getString("MaGVu")));
				giaoVu.setTenGVu(resultSet.getString("TenGVu"));
				giaoVu.setGioiTinh(resultSet.getString("GioiTinh"));
				giaoVu.setNgaySinh(Main.simpleDateFormat.parse(resultSet.getString("NgaySinh")));
				giaoVu.setDiaChi(resultSet.getString("DiaChi"));
				
				giaoVuList.add(giaoVu);
			}
		} catch (Exception e) {
			System.out.println("Error fetching GiaoVuDAO: " + e.getMessage());
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
		
		return giaoVuList;
	}

	@Override
	public GiaoVu getGiaoVu(String maGVu) {
		// TODO Auto-generated method stub
		GiaoVu giaoVu = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from GIAOVU where MaGVu = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maGVu);
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
			System.out.println("Error fetching GiaoVuDAO: " + e.getMessage());
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
	public boolean addGiaoVu(GiaoVu gv) {
		// TODO Auto-generated method stub
		if (getGiaoVu(gv.getMaGVu().getTenTK()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into GIAOVU(MaGVu, TenGVu, GioiTinh, NgaySinh, DiaChi) "
					+ "values(?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, gv.getMaGVu().getTenTK());
			preparedStatement.setString(2, gv.getTenGVu());
			preparedStatement.setString(3, gv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(gv.getNgaySinh().getTime()); 

			preparedStatement.setDate(4, sqlNgaySinh);
			preparedStatement.setString(5, gv.getDiaChi());
			
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding GiaoVu: " + e.getMessage());
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
	public boolean updateGiaoVu(GiaoVu gv) {
		// TODO Auto-generated method stub
		if (getGiaoVu(gv.getMaGVu().getTenTK()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update GIAOVU "
					+ "set TenGVu = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?"
					+ " where MaGVu = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, gv.getTenGVu());
			preparedStatement.setString(2, gv.getGioiTinh());
			java.sql.Date sqlNgaySinh = new java.sql.Date(gv.getNgaySinh().getTime()); 
			
			preparedStatement.setDate(3, sqlNgaySinh);	
			preparedStatement.setString(4, gv.getDiaChi());
			preparedStatement.setString(5, gv.getMaGVu().getTenTK());
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Error updating GiaoVu: " + e.getMessage());
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
	public boolean deleteGiaoVu(String maGVu) {
		// TODO Auto-generated method stub
		if (getGiaoVu(maGVu) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from GIAOVU"
					+ " where MaGVu = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maGVu);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting GiaoVu: " + e.getMessage());
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
		List<GiaoVu> giaoVuList = getAllGiaoVu();
		Object[][] data = new Object[giaoVuList.size()][5];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = giaoVuList.get(i).getMaGVu().getTenTK();
			data[i][1] = giaoVuList.get(i).getTenGVu();
			data[i][2] = giaoVuList.get(i).getGioiTinh();
			data[i][3] = Main.simpleDateFormat.format(giaoVuList.get(i).getNgaySinh());
			data[i][4]= giaoVuList.get(i).getDiaChi();
		}

		return data;
	}

}
