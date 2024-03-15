package utils;

import java.sql.*;
import java.util.Map;

public class DBUtil {
    private static String url;
    private static String user;
    private static String password;
    //静态块，注册驱动(驱动只需要主动并注册一次)
    static {
        //获取文件对应的Map对象
        Map<String,String> map = PropertiesUtil.getProperties("src\\jdbc.properties");
        try {
            Class.forName(map.get("jdbcDriverClassName"));
            url = map.get("url");
            user = map.get("user");
            password = map.get("password");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //获得连接对象
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //资源释放
    public static void closeAll(Connection connection, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
