package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private Connection connection;//真实的连接
    private int index;//索引

    public MyConnection(int index){
        this.index = index;
    }

    private static String driver = DBConfig.getConfig("driver");
    private static String url = DBConfig.getConfig("url");
    private static String user = DBConfig.getConfig("user");
    private static String password = DBConfig.getConfig("password");;

    //只加载驱动一次
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //每次调用构造方法，块都会执行一遍（每次new出一个连接）
    {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
