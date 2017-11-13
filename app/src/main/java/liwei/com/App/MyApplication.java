package liwei.com.App;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import liwei.com.other.GreenDao.DaoMaster;
import liwei.com.other.GreenDao.DaoSession;

public class MyApplication extends Application {

    private static DaoSession daoSession;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
        context = getApplicationContext();
        setupDatabase();
    }

    public static Context getApp(){
        return context;
    }

    /**
     * 配置数据库
     */
    private void setupDatabase(){

        /*
         * DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
         * DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
         * DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
         */

        //创建数据库shop.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase database = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster master = new DaoMaster(database);
        //获取dao对象管理者
        daoSession = master.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}