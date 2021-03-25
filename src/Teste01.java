import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Teste01 {

	public static void main(String[] args) {
		try {
			teste();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
public static void teste() {
		Connection conn = null;
		try {
			conn = getConnection();

			if (conn == null) { 
				System.out.println("Erro no driver SQL");
			} else {	
				String sql = "select a.atec_id as id,";
				sql += "   cast(a.atec_id as number(10)) as id_cast"; 
				sql += " from t_atividade_economica a";
				sql += "   where a.atec_id = '0111203'";
				
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
			    
				if (rs.next()) {
					System.out.println("id: [" + rs.getString("id") + "]");
					System.out.println("id_cast: [" + rs.getString("id_cast") + "]");
				}  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}	
		}
	}
	
	public static Connection getConnection()  {
		final String driverJDBC = "oracle.jdbc.OracleDriver";
		final String urlBanco  = "jdbc:oracle:thin:@10.2.13.5:1521:SCHEMA";
		final String userBanco = "USER";
		final String passBanco = "SENHA";
		
		Connection conexao = null;
		try {
			Class.forName(driverJDBC);
			conexao = DriverManager.getConnection(urlBanco, userBanco, passBanco);
			System.out.println("getConnection - Conexão OK.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}		

}
