package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import brian.Main;
import brian.dao.HKHienTaiDAO;
import brian.model.HKHienTai;

public class MSSQLHKHienTaiDAO implements HKHienTaiDAO{

	@Override
	public HKHienTai getHKHienTai() {
		// TODO Auto-generated method stub
		HKHienTai hkHienTai = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from HKHIENTAI";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				hkHienTai = new HKHienTai();
				hkHienTai.setMaHKHT(resultSet.getString("MaHKHT"));
				hkHienTai.setMaHK(Main.hocKiDAO.getHocKi(resultSet.getString("MaHK")));
			}
		} catch (Exception e) {
			System.out.println("Error fetching HKHienTaiDAO: " + e.getMessage());
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
		
		return hkHienTai;
	}

	@Override
	public boolean updateGKHienTai(HKHienTai hkHienTai) {
		// TODO Auto-generated method stub
		if (getHKHienTai() == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update HKHIENTAI"
					+ "set MaHKHT = ?, MaHK = ?"
					+ "where MaHKHT = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, hkHienTai.getMaHKHT());
			preparedStatement.setString(2, hkHienTai.getMaHK().getMaHK());
			preparedStatement.setString(3, hkHienTai.getMaHKHT());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating HKHienTai: " + e.getMessage());
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

}
