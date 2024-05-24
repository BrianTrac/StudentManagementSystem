package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.dao.LopDAO;
import brian.model.Lop;

public class MSSQLLopDAO implements LopDAO{

	@Override
	public List<Lop> getAllLop() {
		// TODO Auto-generated method stub
		List<Lop> lopList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from LOP";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Lop lop = new Lop();
				lop.setMaLop(resultSet.getString("MaLop"));
				lop.setTenLop(resultSet.getString("TenLop"));
				lop.setSoSV(resultSet.getInt("SoSV"));
				
				lopList.add(lop);
			}
		} catch (Exception e) {
			System.out.println("Error fetching LopDAO: " + e.getMessage());
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
		
		return lopList;
	}

	@Override
	public Lop getLop(String maLop) {
		// TODO Auto-generated method stub
		Lop lop = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from LOP where MaLop = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maLop);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				lop = new Lop();
				lop.setMaLop(resultSet.getString("MaLop"));
				lop.setTenLop(resultSet.getString("TenLop"));
				lop.setSoSV(resultSet.getInt("SoSV"));
			}
		} catch (Exception e) {
			System.out.println("Error fetching LopDAO: " + e.getMessage());
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
		
		return lop;
	}

	@Override
	public boolean addLop(Lop lop) {
		// TODO Auto-generated method stub
		if (getLop(lop.getMaLop()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into Lop(MaLop, TenLop, SoSV) "
					+ "values(?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, lop.getMaLop());
			preparedStatement.setString(2, lop.getTenLop());
			preparedStatement.setInt(3, lop.getSoSV());
			
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding Lop: " + e.getMessage());
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
	public boolean updateLop(Lop lop) {
		// TODO Auto-generated method stub
		if (getLop(lop.getMaLop()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update LOP "
					+ "set TenLop = ?, SoSV = ?"
					+ " where MaLop = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, lop.getTenLop());
			preparedStatement.setInt(2, lop.getSoSV());
			preparedStatement.setString(3, lop.getMaLop());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating Lop: " + e.getMessage());
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
	public boolean deleteLop(String maLop) {
		// TODO Auto-generated method stub
		if (getLop(maLop) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from LOP"
					+ " where MaLop = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maLop);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting Lop: " + e.getMessage());
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
		List<Lop> lopList = getAllLop();
		Object[][] data = new Object[lopList.size()][3];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = lopList.get(i).getMaLop();
			data[i][1] = lopList.get(i).getTenLop();
			data[i][2] = String.valueOf(lopList.get(i).getSoSV());
			
		}
		
		return data;
	}

}
