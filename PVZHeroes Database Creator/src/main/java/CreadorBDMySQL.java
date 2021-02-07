import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreadorBDMySQL {

	static ArrayList<String> ListaNombres = new ArrayList();
    static ArrayList<Integer> ListaAtaques = new ArrayList();
    static ArrayList<Integer> ListaDefensas = new ArrayList();
    static ArrayList<Integer> ListaCostes = new ArrayList();
    static ArrayList<String> ListaClases = new ArrayList();
    static ArrayList<String> ListaTribus = new ArrayList();
    static ArrayList<String> ListaAtributos = new ArrayList();
    static ArrayList<String> ListaHabilidades = new ArrayList();
    static ArrayList<String> ListaRarezas = new ArrayList();
    static ArrayList<String> ListaMazos = new ArrayList();
    static ArrayList<String> ListaTipos = new ArrayList();
    static ArrayList<String> ListaURLs = new ArrayList();
    static ArrayList<Integer> ListaNumeroAtributos = new ArrayList();
    static ArrayList<String> ListaImagenes = new ArrayList();

    private final static String NOMBRE_BBDD_SQLITE = "PVZHeroes.db";
    private final static String NOMBRE_BBDD_MYSQL = "pvzheroes";
    private final static String USUARIO_MYSQL = "webuser";
    private final static String CONTRASEÑA_MYSQL = "password";
    private static String URL;
    private static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {      
        AbrirBBDD("SQLite");
        RellenarListas();
        AbrirBBDD("MySQL");
        RellenarTablaMySQL();
    }
    

    private static void AbrirBBDD(String Tipo) {
        try {
            if (Tipo.equals("SQLite")) {
                URL = "jdbc:sqlite:" + NOMBRE_BBDD_SQLITE;
                conn = DriverManager.getConnection(URL);
            } else {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + NOMBRE_BBDD_MYSQL, USUARIO_MYSQL, CONTRASEÑA_MYSQL);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de " + "Tipo " + ": " + ex);
        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }

    private static void RellenarListas() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cartas;");
            while (rs.next()) {
                ListaNombres.add(rs.getString(1));
                ListaAtaques.add(rs.getInt(2));
                ListaDefensas.add(rs.getInt(3));
                ListaCostes.add(rs.getInt(4));
                ListaClases.add(rs.getString(5));
                ListaTribus.add(rs.getString(6));
                ListaAtributos.add(rs.getString(7));
                ListaHabilidades.add(rs.getString(8));
                ListaRarezas.add(rs.getString(9));
                ListaMazos.add(rs.getString(10));
                ListaTipos.add(rs.getString(11));
                ListaURLs.add(rs.getString(12));
                ListaNumeroAtributos.add(rs.getInt(13));
                ListaImagenes.add(rs.getString(14));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private static void RellenarTablaMySQL() {
        String sentenciaSQL = "INSERT INTO Cartas VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pe = null;
        try {
            pe = conn.prepareStatement(sentenciaSQL);
        } catch (SQLException e) {
            System.out.println("Excepción de SQL " + e.getCause());
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < ListaNombres.size(); i++) {
            try {
                pe.setString(1, ListaNombres.get(i));
                pe.setInt(2, ListaAtaques.get(i));
                pe.setInt(3, ListaDefensas.get(i));
                pe.setInt(4, ListaCostes.get(i));
                pe.setString(5, ListaClases.get(i));
                pe.setString(6, ListaTribus.get(i));
                pe.setString(7, ListaAtributos.get(i));
                pe.setString(8, ListaHabilidades.get(i));
                pe.setString(9, ListaRarezas.get(i));
                pe.setString(10, ListaMazos.get(i));
                pe.setString(11, ListaTipos.get(i));
                pe.setString(12, ListaURLs.get(i));
                pe.setInt(13, ListaNumeroAtributos.get(i));     
                pe.setString(14, ListaImagenes.get(i));
                pe.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}