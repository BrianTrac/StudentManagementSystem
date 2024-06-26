package brian.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import brian.Main;
import brian.dao.HocPhanDAO;
import brian.model.HocPhan;
import brian.model.SinhVien;

public class MSSQLHocPhanDAO implements HocPhanDAO{

	@Override
	public List<HocPhan> getAllHocPhan() {
		// TODO Auto-generated method stub
		List<HocPhan> hocPhanList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from HOCPHAN";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				HocPhan hocPhan = new HocPhan();
				hocPhan.setMaHP(resultSet.getString("MaHP"));
				hocPhan.setSiSo(resultSet.getInt("SiSo"));
				hocPhan.setDaDK(resultSet.getInt("DaDK"));
				hocPhan.setKhoa(resultSet.getString("Khoa"));
				hocPhan.setThu(resultSet.getString("Thu"));
				hocPhan.setKhungGio(resultSet.getString("KhungGio"));
				hocPhan.setPhong(resultSet.getString("Phong"));
				hocPhan.setDiaDiem(resultSet.getString("DiaDiem"));
				hocPhan.setMaGV(Main.giaoVienDAO.getGiaoVien(resultSet.getString("MaGV")));
				hocPhan.setMaMH(Main.monHocDAO.getMonHoc(resultSet.getString("MaMH")));
				hocPhan.setMaLop(Main.lopDAO.getLop(resultSet.getString("MaLop")));
				hocPhan.setMaHK(Main.hocKiDAO.getHocKi(resultSet.getString("MaHK")));
				
				hocPhanList.add(hocPhan);
			}
		} catch (Exception e) {
			System.out.println("Error fetching HocPhanDAO: " + e.getMessage());
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
		
		return hocPhanList;
	}

	@Override
	public HocPhan getHocPhan(String maHP) {
		// TODO Auto-generated method stub
		HocPhan hocPhan = null;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
		String sql = "select * from HOCPHAN where MaHP = ?";
		try {
			connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHP);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				hocPhan = new HocPhan();
				hocPhan.setMaHP(resultSet.getString("MaHP"));
				hocPhan.setSiSo(resultSet.getInt("SiSo"));
				hocPhan.setDaDK(resultSet.getInt("DaDK"));
				hocPhan.setKhoa(resultSet.getString("Khoa"));
				hocPhan.setThu(resultSet.getString("Thu"));
				hocPhan.setKhungGio(resultSet.getString("KhungGio"));
				hocPhan.setPhong(resultSet.getString("Phong"));
				hocPhan.setDiaDiem(resultSet.getString("DiaDiem"));
				hocPhan.setMaGV(Main.giaoVienDAO.getGiaoVien(resultSet.getString("MaGV")));
				hocPhan.setMaMH(Main.monHocDAO.getMonHoc(resultSet.getString("MaMH")));
				hocPhan.setMaLop(Main.lopDAO.getLop(resultSet.getString("MaLop")));
				hocPhan.setMaHK(Main.hocKiDAO.getHocKi(resultSet.getString("MaHK")));
				
			}
		} catch (Exception e) {
			System.out.println("Error fetching HocPhanDAO: " + e.getMessage());
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
		
		return hocPhan;
	}

	@Override
	public boolean addHocPhan(HocPhan hp) {
		// TODO Auto-generated method stub
		if (getHocPhan(hp.getMaHP()) != null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "insert into HOCPHAN(MaHP, MaMH, MaLop, MaHK, MaGV, SiSo, DaDK, Khoa, Thu, KhungGio, Phong, DiaDiem) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, hp.getMaHP());
			preparedStatement.setString(2, hp.getMaMH().getMaMH());
			preparedStatement.setString(3, hp.getMaLop().getMaLop());
			preparedStatement.setString(4, hp.getMaHK().getMaHK());
			preparedStatement.setString(5, hp.getMaGV().getMaGV());
			preparedStatement.setString(6, String.valueOf(hp.getSiSo()));
			preparedStatement.setString(7, String.valueOf(hp.getDaDK()));
			preparedStatement.setString(8, hp.getKhoa());
			preparedStatement.setString(9, hp.getThu());
			preparedStatement.setString(10, hp.getKhungGio());
			preparedStatement.setString(11, hp.getPhong());
			preparedStatement.setString(12, hp.getDiaDiem());
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error adding HocPhan: " + e.getMessage());
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
	public boolean updateHocPhan(HocPhan hp) {
		// TODO Auto-generated method stub
		if (getHocPhan(hp.getMaHP()) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "update HOCPHAN "
					+ "set MaMH = ?, MaLop = ?, MaHK = ?, MaGV = ?, SiSo = ?, DaDK = ?, Khoa = ?, Thu = ?, KhungGio = ?, Phong = ?, DiaDiem = ?"
					+ " where MaHP = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, hp.getMaMH().getMaMH());
			preparedStatement.setString(2, hp.getMaLop().getMaLop());
			preparedStatement.setString(3, hp.getMaHK().getMaHK());
			preparedStatement.setString(4, hp.getMaGV().getMaGV());
			preparedStatement.setString(5, String.valueOf(hp.getSiSo()));
			preparedStatement.setString(6, String.valueOf(hp.getDaDK()));
			preparedStatement.setString(7, hp.getKhoa());
			preparedStatement.setString(8, hp.getThu());
			preparedStatement.setString(9, hp.getKhungGio());
			preparedStatement.setString(10, hp.getPhong());
			preparedStatement.setString(11, hp.getDiaDiem());
			preparedStatement.setString(12, hp.getMaHP());
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error updating HocPhan: " + e.getMessage());
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
	public boolean deleteHocPhan(String maHP) {
		// TODO Auto-generated method stub
		if (getHocPhan(maHP) == null) {
			return false;
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = MSSQLDAOFactory.createConnection();
			String sql = "delete from HOCPHAN"
					+ " where MaHP = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, maHP);
		
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Error deleting HocPhan: " + e.getMessage());
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
	public List<SinhVien> getSinhVienListInHocPhan(String maHP) {
		List<SinhVien> sinhVienList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = " select sv.* "
	    		+    " from SinhVien sv"
	    		+ 	 " join DKHP dkhp on dkhp.MaSV = sv.MaSV"
	    		+ 	 " where dkhp.MaHP = ?";
	    
	    try {
	    	connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHP);
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
			System.out.println("Error fetching HocPhanDAO: " + e.getMessage());
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
	public List<SinhVien> getSinhVienListNotInHocPhan(String maHP) {
		List<SinhVien> sinhVienList = new ArrayList<>();
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT sv.* " +
                "FROM SinhVien sv " +
                "WHERE NOT EXISTS ( " +
                "   SELECT 1 FROM DKHP dkhp " +
                "   WHERE dkhp.MaSV = sv.MaSV AND dkhp.MaHP = ?" +
                ")"; // Corrected JOIN condition;
	    
	    try {
	    	connection = MSSQLDAOFactory.createConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, maHP);
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
			System.out.println("Error fetching HocPhanDAO: " + e.getMessage());
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
	public Object[][] getObjectMatrix(List<HocPhan> hocPhanList) {
		// TODO Auto-generated method stub
		Object[][] data = new Object[hocPhanList.size()][10];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = hocPhanList.get(i).getMaHP();
			data[i][1] = hocPhanList.get(i).getMaMH().getTenMH();
			data[i][2] = hocPhanList.get(i).getMaLop().getTenLop();
			data[i][3] = hocPhanList.get(i).getMaMH().getSoTC();
			data[i][4] = String.valueOf(hocPhanList.get(i).getSiSo());
			data[i][5] = String.valueOf(hocPhanList.get(i).getDaDK());
			data[i][6] = hocPhanList.get(i).getKhoa();
			data[i][7] = hocPhanList.get(i).getThu() + " " + hocPhanList.get(i).getKhungGio();
			data[i][8] = hocPhanList.get(i).getPhong() + " " + hocPhanList.get(i).getDiaDiem();
			data[i][9] = hocPhanList.get(i).getMaGV().getTenGV();
		}
		
		return data;
	}
	
	@Override 
	public Object[][] getObjectMatrix() {
		List<HocPhan> hocPhanList = getAllHocPhan();
		Object[][] data = new Object[hocPhanList.size()][12];
		
		for (int i = 0; i < data.length; i++) {
			data[i][0] = hocPhanList.get(i).getMaHP();
			data[i][1] = hocPhanList.get(i).getMaMH().getMaMH();
			data[i][2] = hocPhanList.get(i).getMaLop().getMaLop();
			data[i][3] = hocPhanList.get(i).getMaHK().getMaHK();
			data[i][4] = hocPhanList.get(i).getMaGV().getMaGV();
			data[i][5] = String.valueOf(hocPhanList.get(i).getSiSo());
			data[i][6] = String.valueOf(hocPhanList.get(i).getDaDK());
			data[i][7] = hocPhanList.get(i).getKhoa();
			data[i][8] = hocPhanList.get(i).getThu(); 
			data[i][9] = hocPhanList.get(i).getKhungGio();
			data[i][10] = hocPhanList.get(i).getPhong();
			data[i][11] = hocPhanList.get(i).getDiaDiem();
		}
		
		return data;
	}
}
