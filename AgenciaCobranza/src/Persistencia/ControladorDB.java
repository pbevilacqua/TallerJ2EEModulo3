package Persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
			DataSource ds = (DataSource) initialContext.lookup("java:/MySql_AgenciaDS");
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
			String insertQuery = "INSERT INTO Ticket (TicketNro,Matricula,FchHraVenta,FchHraEst,CantMin,ImpTotal,TerminalNro) VALUES(?,?,?,?,?,?,?);";
			pstmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ticket.getTicketNro());
			pstmt.setString(2, ticket.getMatricula());
			pstmt.setTimestamp(3, new java.sql.Timestamp(ticket.getFchHraVenta().getTime()));
			pstmt.setTimestamp(4, new java.sql.Timestamp(ticket.getFchHraEst().getTime()));
			pstmt.setInt(5, ticket.getCantMin());
			pstmt.setFloat(6, ticket.getImpTotal());
			pstmt.setInt(7, ticket.getTerminalNro());

			int i = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				ticket.setTicketNro(rs.getInt(1));
				System.out.println(i + " registro/s ingresado/s");	
			}
			else{
				rs.next();
			}

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

	public void anularTicket(Ticket ticket) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecerConexion();

			String updateQuery = "UPDATE Ticket SET CodAnul = ?, FchHraAnul = ? WHERE TicketNro = ?;";
			pstmt = con.prepareStatement(updateQuery);
			pstmt.setInt(1, ticket.getCodAnul());
			pstmt.setTimestamp(2, new java.sql.Timestamp(ticket.getFchHraAnul().getTime()));
			pstmt.setInt(3, ticket.getTicketNro());

			int i = pstmt.executeUpdate();

			System.out.println("Ticket anulado con exito");

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
	
	public ArrayList<Ticket> obtenerVentasPorFecha(Date fechaDesde, Date fechaHasta) {
		ArrayList<Ticket> lts = new ArrayList<Ticket>();		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = establecerConexion();
			String selectSQL = "SELECT * FROM Ticket WHERE FchHraVenta>= ? and FchHraVenta<= ?;";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setTimestamp(1, new java.sql.Timestamp(fechaDesde.getTime()));
			pstmt.setTimestamp(2, new java.sql.Timestamp(fechaHasta.getTime()));
			rs = pstmt.executeQuery();

			Ticket ticket;
			while(rs.next()) {
				
				ticket = new Ticket();
				
				ticket.setTicketNro(rs.getInt("TicketNro"));
				ticket.setMatricula(rs.getString("Matricula"));
				ticket.setFchHraVenta(rs.getTimestamp("FchHraVenta"));
				ticket.setFchHraEst(rs.getTimestamp("FchHraEst"));
				ticket.setCantMin(rs.getInt("CantMin"));
				ticket.setImpTotal(rs.getFloat("ImpTotal"));
			    ticket.setCodAnul(rs.getInt("CodAnul")); 
			    ticket.setFchHraAnul(rs.getTimestamp("FchHraAnul"));
			    ticket.setTerminalNro(rs.getInt("TerminalNro"));

			    lts.add(ticket);
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
				System.out.println("Ocurrio un error al liberar los recursos en Obtener Ventas por Fecha");
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
			
			String insertQuery = "SELECT * FROM usuario WHERE UsuarioNombre = ? and UsuarioContrasena = ?";
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
	
	public boolean usuarioAdministrador(String usuario, int rolId) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try {
			con = establecerConexion();
			
			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			
			String insertQuery = "SELECT * FROM usuariorol WHERE UsuarioNombre = ? and RolId = ?";
			stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, usuario);
			stmt.setInt(2, rolId);
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
						.println("Ocurrio un error al liberar los recursos luego de consultar si Usuario Administrador");
				e.printStackTrace();
			}

		}
		return resultado;
	}
	
	public boolean usuarioTerminal(String usuario, int rolId, int terminal) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try {
			con = establecerConexion();
			
			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			
			String insertQuery = "SELECT * FROM usuariorolterminal WHERE UsuarioNombre = ? and RolId = ? and TerminalId = ?";
			stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, usuario);
			stmt.setInt(2, rolId);
			stmt.setInt(3, terminal);
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
						.println("Ocurrio un error al liberar los recursos luego de consultar si Usuario Administrador Terminal");
				e.printStackTrace();
			}

		}
		return resultado;
	}
}
