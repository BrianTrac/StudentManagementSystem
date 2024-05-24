package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.dao.MonHocDAO;
import brian.model.MonHoc;

public class MSSQLMonHocDAO implements MonHocDAO{

	@Override
	public List<MonHoc> getAllMonHoc() {
		// TODO Auto-generated method stub
		List<MonHoc> monHocList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from MONHOC";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MonHoc monHoc = new MonHoc();
				monHoc.setMaMH(resultSet.getString("MaMH"));
				monHoc.setTenMH(resultSet.getString("TenMH"));
				monHoc.setSoTC(resultSet.getInt("SoTC"));
			
				monHocList.add(monHoc);
			}
		} catch (Exception e) {
			System.out.println("Error fetching MonHocDAO: " + e.getMessage());
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
		
		return monHocList;
	}

	@Override
	public MonHoc getMonHoc(String maMH) {
		// TODO Auto-generated method stub
		MonHoc monHoc = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from MONHOC where MaMH = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maMH);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				monHoc = new MonHoc();
				monHoc.setMaMH(resultSet.getString("MaMH"));
				monHoc.setTenMH(resultSet.getString("TenMH"));
				monHoc.setSoTC(resultSet.getInt("SoTC"));
			
			}
		} catch (Exception e) {
			System.out.println("Error fetching MonHocDAO: " + e.getMessage());
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
		
		return monHoc;
	}

	@Override
	public boolean addMonHoc(MonHoc mh) {
		// TODO Auto-generated method stub
		if (getMonHoc(mh.getMaMH()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into MONHOC(MaMH, TenMH, SoTC) "
					+ "values(?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, mh.getMaMH());
			preparedStatement.setString(2, mh.getTenMH());
			preparedStatement.setString(3, String.valueOf(mh.getSoTC()));
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding MonHoc: " + e.getMessage());
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
	public boolean updateMonHoc(MonHoc mh) {
		// TODO Auto-generated method stub
		if (getMonHoc(mh.getMaMH()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update MONHOC "
					+ "set TenMH = ?, SoTC = ?"
					+ " where MaMH = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, mh.getTenMH());
			preparedStatement.setString(2, String.valueOf(mh.getSoTC()));
			preparedStatement.setString(3, mh.getMaMH());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating MonHoc: " + e.getMessage());
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
	public boolean deleteMonHoc(String maMH) {
		// TODO Auto-generated method stub
		if (getMonHoc(maMH) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from MONHOC"
					+ " where MaMH = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maMH);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting MonHoc: " + e.getMessage());
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
		List<MonHoc> monHocList = getAllMonHoc();
		Object[][] data = new Object[monHocList.size()][3];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = monHocList.get(i).getMaMH();
			data[i][1] = monHocList.get(i).getTenMH();
			data[i][2] = String.valueOf(monHocList.get(i).getSoTC());
		}
		
		return data;
	}

}
