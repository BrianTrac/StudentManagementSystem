package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.HocKiDAO;
import brian.model.HocKi;

public class MSSQLHocKiDAO implements HocKiDAO{

	@Override
	public List<HocKi> getAllHocKi() {
		// TODO Auto-generated method stub
		List<HocKi> hocKiList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from HOCKI";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HocKi hocKi = new HocKi();
				hocKi.setMaHK(resultSet.getString("MaHK"));
				hocKi.setTenHK(resultSet.getString("TenHK"));
				hocKi.setNamHoc(resultSet.getInt("NamHoc"));
				hocKi.setNgayBatDau(Main.simpleDateFormat.parse(resultSet.getString("NgayBatDau")));
				hocKi.setNgayKetThuc(Main.simpleDateFormat.parse(resultSet.getString("NgayKetThuc")));
				
				hocKiList.add(hocKi);
			}
		} catch (Exception e) {
			System.out.println("Error fetching HocKiDAO: " + e.getMessage());
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
		
		return hocKiList;
	}

	@Override
	public HocKi getHocKi(String maHK) {
		// TODO Auto-generated method stub
		HocKi hocKi = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from HOCKI where MaHK = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHK);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				hocKi = new HocKi();
				hocKi.setMaHK(resultSet.getString("MaHK"));
				hocKi.setTenHK(resultSet.getString("TenHK"));
				hocKi.setNamHoc(resultSet.getInt("NamHoc"));
				hocKi.setNgayBatDau(Main.simpleDateFormat.parse(resultSet.getString("NgayBatDau")));
				hocKi.setNgayKetThuc(Main.simpleDateFormat.parse(resultSet.getString("NgayKetThuc")));
			
			}
		} catch (Exception e) {
			System.out.println("Error fetching HocKiDAO: " + e.getMessage());
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
		
		return hocKi;
	}

	@Override
	public boolean addHocKi(HocKi hk) {
		// TODO Auto-generated method stub
		if (getHocKi(hk.getMaHK()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into HOCKI(MaHK, TenHK, NamHoc, NgayBatDau, NgayKetThuc) "
					+ "values(?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, hk.getMaHK());
			preparedStatement.setString(2, hk.getTenHK());
			preparedStatement.setString(3, String.valueOf(hk.getNamHoc()));
			preparedStatement.setString(4, Main.simpleDateFormat.format(hk.getNgayBatDau()));
			preparedStatement.setString(5, Main.simpleDateFormat.format(hk.getNgayKetThuc()));
						
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding HocKi: " + e.getMessage());
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
	public boolean updateHocKi(HocKi hk) {
		// TODO Auto-generated method stub
		if (getHocKi(hk.getMaHK()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update HOCKI "
					+ "set MaHK = ?, TenHK = ?, NamHoc = ?, NgayBatDau = ?, NgayKetThuc = ?"
					+ "where MaHK = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, hk.getMaHK());
			preparedStatement.setString(2, hk.getTenHK());
			preparedStatement.setString(3, String.valueOf(hk.getNamHoc()));
			preparedStatement.setString(4, Main.simpleDateFormat.format(hk.getNgayBatDau()));
			preparedStatement.setString(5, Main.simpleDateFormat.format(hk.getNgayKetThuc()));
			preparedStatement.setString(6, hk.getMaHK());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating HocKi: " + e.getMessage());
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
	public boolean deleteHocKi(String maHK) {
		// TODO Auto-generated method stub
		if (getHocKi(maHK) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from HOCKI"
					+ "where MaHK = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maHK);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting HocKi: " + e.getMessage());
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
		List<HocKi> hocKiList = getAllHocKi();
		Object[][] data = new Object[hocKiList.size()][5];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = hocKiList.get(i).getMaHK();
			data[i][1] = hocKiList.get(i).getTenHK();
			data[i][2] = String.valueOf(hocKiList.get(i).getNamHoc());
			data[i][3] = Main.simpleDateFormat.format(hocKiList.get(i).getNgayBatDau());
			data[i][4] = Main.simpleDateFormat.format(hocKiList.get(i).getNgayKetThuc());
			
		}
		
		return data;
	}

}
