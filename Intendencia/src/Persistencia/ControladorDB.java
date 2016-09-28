package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import DataTypes.Ticket;

public class ControladorDB {
	
	// Singleton Manager_Persistencia
	private static ControladorDB controladorDB;

	private ControladorDB() {
	}

	public static ControladorDB getControladorDB() {
		if (controladorDB == null) {
			controladorDB = new ControladorDB();
		}
		return controladorDB;
	}

	public Connection establecerConexion() {
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/MySQL_IMMDS");
			con = ds.getConnection();

			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar con la base de datos SQL");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return con;
	}
	
	public void reservaTicket(Ticket ticket) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecerConexion();
			String insertQuery = "INSERT INTO Ticket (TicketNro,Matricula,FchHraVenta,CantMin,ImpTotal,AgenciaNro) VALUES(?,?,?,?,?,?);";
			pstmt = con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setString(2, ticket.getMatricula());
			pstmt.setDate(3, ticket.getFchHraVenta());
			pstmt.setInt(4, ticket.getCantMin());
			pstmt.setFloat(5, ticket.getImpTotal());
			pstmt.setInt(6, ticket.getAgenciaNro());

			int i = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			ticket.setTicketNro(rs.getInt(1));
			System.out.println(i + " registro/s ingresado/s");

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw (new Exception());
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en reserva ticket");
				e.printStackTrace();
			}

		}

	}
}