package connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Watchout!, not singlton connection class
public class ConnectionPool {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/myitemes?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_NAME = "root";
    private static final String DB_PASSWORD = "root";
    private int maxPoolSize = 5;
    private int connNum = 0;

    private static final String SQL_VERIFYCONN = "select 1";

    Stack<Connection> freePool = new Stack<>();
    List<Connection> occupiedPool = new ArrayList<>();

    public ConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection conn = null;
        if (isFull()) {
            throw new SQLException("Connection pool is full");
        }
        conn = getConnectionFromPool();
        if (conn == null) {
            conn = creatNewConnectionForPool();
        }
        conn = makeAviable(conn);
        return conn;
    }

    public synchronized void returnConnection(Connection conn) throws SQLException {
        if (conn == null) {
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(conn)) {
            throw new SQLException(
                    "Connection is returned already or it isn't fo this pool");
        }
        freePool.push(conn);

    }

    private synchronized boolean isFull() {
        return ((freePool.size() == 0) && (connNum >= maxPoolSize));
    }

    private Connection creatNewConnectionForPool() throws SQLException {
        Connection conn = creatNewConnection();
        connNum++;
        occupiedPool.add(conn);
        return conn;
    }

    private Connection creatNewConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        return conn;
    }

    private Connection getConnectionFromPool() {
        Connection conn = null;
        if (freePool.size() > 0) {
            conn = freePool.pop();
            occupiedPool.add(conn);
        }
        return conn;
    }

    private Connection makeAviable(Connection conn) throws SQLException {
        if (isConnectionAviable(conn)) {
            return conn;
        }
        occupiedPool.remove(conn);
        connNum--;
        conn.close();

        conn = creatNewConnection();
        occupiedPool.add(conn);
        connNum++;
        return conn;
    }

    private boolean isConnectionAviable(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.executeQuery(SQL_VERIFYCONN);
            return true;
        } catch (SQLException e) {
            return false;
        }


    }

}