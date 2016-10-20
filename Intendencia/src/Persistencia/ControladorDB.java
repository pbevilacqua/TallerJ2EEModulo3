package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

	public void reservaTicket(Ticket ticket, Mensaje mensaje) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecerConexion();

			String msg = "";
			if (!existeAgencia(ticket.getAgenciaNro())) {

				msg = "Agencia no existe";

				mensaje.setCodigo(202);
				mensaje.setMensaje(msg);
				System.out.println(msg);
			} else {

				String insertQuery = "INSERT INTO Ticket (TicketNro,Matricula,FchHraVenta,FchHraEst,CantMin,ImpTotal,AgenciaNro) VALUES(?,?,?,?,?,?,?);";
				pstmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, 0);
				pstmt.setString(2, ticket.getMatricula());
				pstmt.setTimestamp(3, new java.sql.Timestamp(ticket.getFchHraVenta().getTime()));
				pstmt.setTimestamp(4, new java.sql.Timestamp(ticket.getFchHraEst().getTime()));
				pstmt.setInt(5, ticket.getCantMin());
				pstmt.setFloat(6, ticket.getImpTotal());
				pstmt.setInt(7, ticket.getAgenciaNro());

				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				ticket.setTicketNro(rs.getInt(1));

				if (rs.getInt(1) > 0) {
					mensaje.setCodigo(0);
					mensaje.setMensaje("Ticket ingresado con exito");

					System.out.println("Ticket ingresado con exito");
				} else {

				}

			}

		} catch (

		Exception e) {
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
				String msg = "";
				if (!existeAgencia(ticket.getAgenciaNro())) {

					msg = "Agencia no existe";

					mensaje.setCodigo(1002);
					mensaje.setMensaje(msg);
					System.out.println(msg);
				} else {

					if (!existeTicket(ticket.getTicketNro())) {
						msg = "Ticket no existe";

						mensaje.setCodigo(1001);
						mensaje.setMensaje(msg);
						System.out.println(msg);
					} else {
						if (!existeTicketAgencia(ticket.getTicketNro(), ticket.getAgenciaNro())) {
							msg = "Ticket no fue vendido en Agencia";

							mensaje.setCodigo(1003);
							mensaje.setMensaje(msg);
							System.out.println(msg);
						} else {

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

						}
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
				System.out.println("Ocurrio un error al liberar los recursos en anulacion ticket");
				e.printStackTrace();
			}

		}

	}

	public boolean existeAgencia(int agenciaNro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ageNro = 0;
		try {
			con = establecerConexion();
			String selectSQL = "SELECT AgenciaNro FROM Agencia WHERE AgenciaNro = ?";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, agenciaNro);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ageNro = rs.getInt(1);
			}

			return ageNro > 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en consulta agencia");
				e.printStackTrace();
			}

		}
		return ageNro > 0;
	}

	public boolean existeTicket(int ticketNro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int tckNro = 0;
		try {
			con = establecerConexion();

			String selectSQL = "SELECT TicketNro FROM Ticket WHERE TicketNro = ?";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ticketNro);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				tckNro = rs.getInt(1);
			}

			return tckNro > 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en consulta ticket");
				e.printStackTrace();
			}

		}
		return tckNro > 0;
	}

	public boolean existeTicketAgencia(int ticketNro, int agenciaNro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int tckNro = 0;
		try {
			con = establecerConexion();

			String selectSQL = "SELECT TicketNro FROM Ticket WHERE TicketNro = ? and AgenciaNro = ?";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, ticketNro);
			pstmt.setInt(2, agenciaNro);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				tckNro = rs.getInt(1);
			}

			return tckNro > 0;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en consulta ticket");
				e.printStackTrace();
			}

		}
		return tckNro > 0;
	}
	
	public ArrayList<Ticket> listarTicket(Date fechaDesde, Date fechaHasta) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArrayList<Ticket> lts = new ArrayList<Ticket>();
		int tckNro = 0;
		try {
			con = establecerConexion();

			String selectSQL = "SELECT TicketNro, Matricula, FchHraVenta, FchHraEst, CantMin, ImpTotal, CodAnul, FchHraAnul , AgenciaNro FROM Ticket where fchhraventa >= ? and fchhraventa <= ?";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setTimestamp(1, new java.sql.Timestamp(fechaDesde.getTime()));
			pstmt.setTimestamp(2, new java.sql.Timestamp(fechaHasta.getTime()));
			ResultSet rs = pstmt.executeQuery();
			
			Ticket ticket = new Ticket();
			while (rs.next()) {
				ticket.setTicketNro(rs.getInt("TicketNro"));
				ticket.setMatricula(rs.getString("Matricula"));
				
				Timestamp ts = rs.getTimestamp("FchHraEst");
				if (ts != null)
					ticket.setFchHraEst(new Date(ts.getTime()));
				
				ts = rs.getTimestamp("FchHraVenta");
				if (ts != null)
					ticket.setFchHraVenta(new Date(rs.getTimestamp("FchHraVenta").getTime()));
				
				ticket.setCantMin(rs.getInt("CantMin"));
				ticket.setImpTotal(rs.getFloat("ImpTotal"));
				ticket.setCodAnul(rs.getInt("CodAnul"));

				ts = rs.getTimestamp("FchHraAnul");
				if (ts != null)
					ticket.setFchHraAnul(new Date(rs.getTimestamp("FchHraAnul").getTime()));
				
				ticket.setAgenciaNro(rs.getInt("AgenciaNro"));
				lts.add(ticket);
				ticket = new Ticket();
			}

			return lts;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en listar Tickets");
				e.printStackTrace();
			}

		}
		return lts;
	}
	
	
	public boolean existeUsuario(String nombre, String contrasena) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try {
			con = establecerConexion();
			
			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			
			String insertQuery = "SELECT * FROM usuarios WHERE UsuarioNombre = ? and UsuarioContrasena = ?";
			stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, nombre);
			stmt.setString(2, contrasena);
			rs = stmt.executeQuery();
			

			if (rs.next()) {

				resultado = true;
			}
			else{
				resultado = false;
			}


		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out
						.println("Ocurrio un error al liberar los recursos luego de consultar si Existe Usuario");
				e.printStackTrace();
			}

		}
		return resultado;

	}
}
