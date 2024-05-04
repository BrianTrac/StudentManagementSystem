package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.DKHPDAO;
import brian.model.DKHP;

public class MSSQLDKHPDAO implements DKHPDAO{

	@Override
	public List<DKHP> getAllDKHP() {
		// TODO Auto-generated method stub
		List<DKHP> dkhpList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from DKHP";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				DKHP dkhp = new DKHP();
				dkhp.setMaHP(Main.hocPhanDAO.getHocPhan(resultSet.getString("MaHP")));
				dkhp.setMaSV(Main.sinhVienDAO.getSinhVien(resultSet.getString("MaSV")));
				dkhp.setThoiGianDKHP(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("ThoiGianDKHP")));
				dkhp.setDiem(resultSet.getDouble("Diem"));
				
				dkhpList.add(dkhp);
			}
		} catch (Exception e) {
			System.out.println("Error fetching DKHPDAO: " + e.getMessage());
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
		
		return dkhpList;
	}

	@Override
	public DKHP getDKHP(String maHP, String maSV) {
		// TODO Auto-generated method stub
		DKHP dkhp = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from DKHP where MaHP = ? and MaSV = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHP);
			preparedStatement.setString(2, maSV);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				dkhp = new DKHP();
				dkhp.setMaHP(Main.hocPhanDAO.getHocPhan(resultSet.getString("MaHP")));
				dkhp.setMaSV(Main.sinhVienDAO.getSinhVien(resultSet.getString("MaSV")));
				dkhp.setThoiGianDKHP(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("ThoiGianDKHP")));
				dkhp.setDiem(resultSet.getDouble("Diem"));
				
			}
		} catch (Exception e) {
			System.out.println("Error fetching DKHPDAO: " + e.getMessage());
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
		
		return dkhp;
	}

	@Override
	public boolean addDKHP(DKHP dkhp) {
		// TODO Auto-generated method stub
		if (getDKHP(dkhp.getMaHP().getMaHP(), dkhp.getMaSV().getMaSV().getTenTK()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into DKHP(MaHP, MaSV, ThoiGianDKHP, Diem) "
					+ "values(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, dkhp.getMaHP().getMaHP());
			preparedStatement.setString(2, dkhp.getMaSV().getMaSV().getTenTK());
			preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dkhp.getThoiGianDKHP()));
			preparedStatement.setString(4, String.valueOf(dkhp.getDiem()));
			
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding DKHP: " + e.getMessage());
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
	public boolean deleteDKHP(String maHP, String maSV) {
		// TODO Auto-generated method stub
		if (getDKHP(maHP, maSV) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from DKHP"
					+ "where MaHP = ? and MaSV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maHP);
			preparedStatement.setString(2, maSV);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting DKHP: " + e.getMessage());
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
	public boolean updateDKHP(DKHP dkhp) {
		// TODO Auto-generated method stub
		if (getDKHP(dkhp.getMaHP().getMaHP(), dkhp.getMaSV().getMaSV().getTenTK()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update DKHP "
					+ "set MaHP = ?, MaSV = ?, ThoiGianDKHP = ?, Diem = ?"
					+ "where MaHP = ? and MaSV = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, dkhp.getMaHP().getMaHP());
			preparedStatement.setString(2, dkhp.getMaSV().getMaSV().getTenTK());
			preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dkhp.getThoiGianDKHP()));
			preparedStatement.setString(4, String.valueOf(dkhp.getDiem()));
			preparedStatement.setString(5, dkhp.getMaHP().getMaHP());
			preparedStatement.setString(6, dkhp.getMaSV().getMaSV().getTenTK());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating DKHP: " + e.getMessage());
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
		List<DKHP> dkhpList = getAllDKHP();
		Object[][] data = new Object[dkhpList.size()][4];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = dkhpList.get(i).getMaHP().getMaHP();
			data[i][1] = dkhpList.get(i).getMaSV().getMaSV().getTenTK();
			data[i][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dkhpList.get(i).getThoiGianDKHP());
			data[i][3] = String.valueOf(dkhpList.get(i).getDiem());
		}
		
		return data;
	}

}
