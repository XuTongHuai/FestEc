package com.xutonghuai.latter.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by xutonghuai on 2018-03-23.
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }


    private void initDao(Context context){
        //创建数据库"info.db"
        final ReleaseOpenHelper releaseOpenHelper = new ReleaseOpenHelper(context,"fast_ec.db");
        //获取可写数据库
        final Database database = releaseOpenHelper.getWritableDb();
        //获取Dao对象管理者
        mDaoSession = new DaoMaster(database).newSession();
        //获取user_profile表的Dao对像
        mDao = mDaoSession.getUserProfileDao();
    }

    /**
     * 获取UserProfileDao的对象
     * @return
     */
    public final UserProfileDao getDao() {
        return mDao;
    }

}
