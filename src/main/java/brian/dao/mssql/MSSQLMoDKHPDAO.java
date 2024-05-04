package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.MoDKHPDAO;
import brian.model.MoDKHP;

public class MSSQLMoDKHPDAO implements MoDKHPDAO{

	@Override
	public List<MoDKHP> getAllMoDKHP() {
		// TODO Auto-generated method stub
		List<MoDKHP> moDKHPList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from MODKHP";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MoDKHP moDKHP = new MoDKHP();
				moDKHP.setMaHK(Main.hocKiDAO.getHocKi(resultSet.getString("MaHK")));
				moDKHP.setSoLanMo(resultSet.getInt("SoLanMo"));
				moDKHP.setNgayBatDau(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("NgayBatDau")));
				moDKHP.setNgayKetThuc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("NgayKetThuc")));
		
				moDKHPList.add(moDKHP);
			}
		} catch (Exception e) {
			System.out.println("Error fetching MoDKHPDAO: " + e.getMessage());
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
		
		return moDKHPList;
	}

	@Override
	public MoDKHP getMoDKHP(String maHK, int soLanMo) {
		// TODO Auto-generated method stub
		MoDKHP moDKHP = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from MODKHP where MaHK = ? and SoLanMo = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHK);
			preparedStatement.setInt(2, soLanMo);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				moDKHP = new MoDKHP();
				moDKHP.setMaHK(Main.hocKiDAO.getHocKi(resultSet.getString("MaHK")));
				moDKHP.setSoLanMo(resultSet.getInt("SoLanMo"));
				moDKHP.setNgayBatDau(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("NgayBatDau")));
				moDKHP.setNgayKetThuc(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("NgayKetThuc")));
		
			}
		} catch (Exception e) {
			System.out.println("Error fetching MODKHPDAO: " + e.getMessage());
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
		
		return moDKHP;
	}

	@Override
	public boolean addMoDKHP(MoDKHP moDKHP) {
		// TODO Auto-generated method stub
		if (getMoDKHP(moDKHP.getMaHK().getMaHK(), moDKHP.getSoLanMo()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into MODKHP(MaHK, SoLanMo, NgayBatDau, NgayKetThuc) "
					+ "values(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, moDKHP.getMaHK().getMaHK());
			preparedStatement.setString(2, String.valueOf(moDKHP.getSoLanMo()));
			preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHP.getNgayBatDau()));
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHP.getNgayKetThuc()));
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding MoDKHP: " + e.getMessage());
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
	public boolean updateMoDKHP(MoDKHP moDKHP) {
		// TODO Auto-generated method stub
		if (getMoDKHP(moDKHP.getMaHK().getMaHK(), moDKHP.getSoLanMo()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update GIAOVIEN "
					+ "set MaHK = ?, SoLanMo = ?, NgayBatDau = ?, NgayKetThuc = ?"
					+ "where MaHK = ? and SoLanMo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, moDKHP.getMaHK().getMaHK());
			preparedStatement.setString(2, String.valueOf(moDKHP.getSoLanMo()));
			preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHP.getNgayBatDau()));
			preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHP.getNgayKetThuc()));
			preparedStatement.setString(5, moDKHP.getMaHK().getMaHK());
			preparedStatement.setString(6, String.valueOf(moDKHP.getSoLanMo()));
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating MoDKHP: " + e.getMessage());
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
	public boolean deleteMoDKHP(String maHK, int soLanMo) {
		// TODO Auto-generated method stub
		if (getMoDKHP(maHK, soLanMo) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from MODKHP"
					+ "where MaHK = ? and SoLanMo = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maHK);
			preparedStatement.setInt(2, soLanMo);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting MoDKHP: " + e.getMessage());
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
		List<MoDKHP> moDKHPList = getAllMoDKHP();
		Object[][] data = new Object[moDKHPList.size()][4];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = moDKHPList.get(i).getMaHK().getMaHK();
			data[i][1] = String.valueOf(moDKHPList.get(i).getSoLanMo());
			data[i][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHPList.get(i).getNgayBatDau());
			data[i][3] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moDKHPList.get(i).getNgayKetThuc());
			
		}
		
		return data;
	}

}
