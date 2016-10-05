package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import DataTypes.Mensaje;
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

			// Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar con la base de datos SQL");
		} // catch (ClassNotFoundException e) {
			// System.out.println("ClassNotFoundException: " + e.getMessage());
			// }
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return con;
	}

	public void reservaTicket(Ticket ticket) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecerConexion();
			String insertQuery = "INSERT INTO Ticket (TicketNro,Matricula,FchHraVenta,FchHraEst,CantMin,ImpTotal,AgenciaNro) VALUES(?,?,?,?,?,?,?);";
			pstmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setString(2, ticket.getMatricula());
			pstmt.setTimestamp(3, new java.sql.Timestamp(ticket.getFchHraVenta().getTime()));
			pstmt.setTimestamp(4, new java.sql.Timestamp(ticket.getFchHraEst().getTime()));
			pstmt.setInt(5, ticket.getCantMin());
			pstmt.setFloat(6, ticket.getImpTotal());
			pstmt.setInt(7, ticket.getAgenciaNro());

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

	public void anularTicket(Ticket ticket, Mensaje mensaje) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecerConexion();

			Date fchHraAnul = new Date(new java.util.Date().getTime());
			int codAnul = 0;

			String selectSQL = "SELECT max(CodAnul) FROM Ticket";
			PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery(selectSQL);
			while (rs.next()) {
				codAnul = rs.getInt(1) + 1;
			}

			if (codAnul > 0) {

				String updateQuery = "UPDATE Ticket SET CodAnul = ?, FchHraAnul = ? WHERE TicketNro = ? AND AgenciaNro = ?;";
				pstmt = con.prepareStatement(updateQuery);
				pstmt.setInt(1, codAnul);
				pstmt.setTimestamp(2, new java.sql.Timestamp(fchHraAnul.getTime()));
				pstmt.setInt(3, ticket.getTicketNro());
				pstmt.setInt(4, ticket.getAgenciaNro());

				int i = pstmt.executeUpdate();

				ticket.setFchHraAnul(fchHraAnul);
				ticket.setCodAnul(codAnul);

				mensaje.setCodigo(0);
				mensaje.setMensaje("Ticket anulado con exito");

				System.out.println("Ticket anulado con exito");
			} else {
				selectSQL = "SELECT AgenciaNro FROM Ticket WHERE AgenciaNro = ?";
				preparedStatement = con.prepareStatement(selectSQL);
				preparedStatement.setInt(1, ticket.getAgenciaNro());
				rs = preparedStatement.executeQuery(selectSQL);

				int agenciaNro = 0;
				while (rs.next()) {
					agenciaNro = rs.getInt(1) + 1;
				}

				String msg = "";
				if (agenciaNro == 0) {

					msg = "Agencia no existe";

					mensaje.setCodigo(202);
					mensaje.setMensaje(msg);
					System.out.println(msg);
				} else {
					selectSQL = "SELECT TicketNro FROM Ticket WHERE TicketNro = ?";
					preparedStatement = con.prepareStatement(selectSQL);
					preparedStatement.setInt(1, ticket.getTicketNro());
					rs = preparedStatement.executeQuery(selectSQL);

					int ticketNro = 0;
					while (rs.next()) {
						ticketNro = rs.getInt(1) + 1;
					}

					if (ticketNro == 0) {
						msg = "Ticket no existe";

						mensaje.setCodigo(201);
						mensaje.setMensaje(msg);
						System.out.println(msg);
					} else {
						msg = "Ticket no fue vendido en Agencia";

						mensaje.setCodigo(203);
						mensaje.setMensaje(msg);
						System.out.println(msg);
					}
				}

			}

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

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

//	public boolean existeAgencia(int agenciaNro) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = establecerConexion();
//			String selectSQL = "SELECT AgenciaNro FROM Ticket WHERE AgenciaNro = ?";
//			pstmt = con.prepareStatement(selectSQL);
//			pstmt.setInt(1, agenciaNro);
//			ResultSet rs = pstmt.executeQuery(selectSQL);
//
//			int ageNro;
//			while (rs.next()) {
//				ageNro = rs.getInt(1) + 1;
//			}
//
//			return ageNro != 0;
//		} catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//
//		}
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//				if (con != null)
//					con.close();
//			} catch (SQLException e) {
//				System.out.println("Ocurrio un error al liberar los recursos en reserva ticket");
//				e.printStackTrace();
//			}
//
//		}
//	}
}
