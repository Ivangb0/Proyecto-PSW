package Persistence;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public abstract class DBA<T> {
    private static final int DB_VERSION = 1;
    private Dao<T, Integer> dao;

    public void init(Class clazz){
        try {
            ConnectionSource connection = new JdbcConnectionSource("jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true", "KogMaw", "WIXAdmin1");
            dao = DaoManager.createDao(connection,clazz);
        }
        catch (Exception e) {System.out.println(e);}

    }

    public  Dao<T, Integer> getDao(){
        return dao;
    }

    public T guardar(T entity){
        try {
            this.getDao().create(entity);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<T> obtenerTodos(){
        try {
            return this.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void delete(int id){
        try {
            this.getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public T obtener(int id){
        try {
            return this.getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizar(T t){
        try {
            this.getDao().update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}