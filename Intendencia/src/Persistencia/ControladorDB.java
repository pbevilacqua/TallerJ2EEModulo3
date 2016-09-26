package Persistencia;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

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
			DataSource ds = (DataSource) initialContext.lookup("java:/MySqlDS");
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
}
