package liwei.com.other.GreenDao;

import java.util.List;

import liwei.com.App.MyApplication;

public class ShopDaoUtils {

    /*
     * 使用GreenDao 实现简单的增删改查，下面是基本方法
     * 增加单个数据
     *      getShopDao().insert(shop);
     *      getShopDao().insertOrReplace(shop);
     * 增加多个数据
     *      getShopDao().insertInTx(shopList);
     *      getShopDao().insertOrReplaceInTx(shopList);
     * 查询全部
     *      List< Shop> list = getShopDao().loadAll();
     *      List< Shop> list = getShopDao().queryBuilder().list();
     * 查询附加单个条件
     *      .where()
     *      .whereOr()
     * 查询附加多个条件
     *      .where(, , ,)
     *      .whereOr(, , ,)
     * 查询附加排序
     *      .orderDesc()
     *      .orderAsc()
     * 查询限制当页个数
     *      .limit()
     * 查询总个数
     *      .count()
     * 修改单个数据
     *      getShopDao().update(shop);
     * 修改多个数据
     *      getShopDao().updateInTx(shopList);
     * 删除单个数据
     *      getTABUserDao().delete(user);
     * 删除多个数据
     *      getUserDao().deleteInTx(userList);
     * 删除数据ByKey
     *      getTABUserDao().deleteByKey();
     */


    /**
     * 添加数据，如果有重复则覆盖
     */
    public static boolean insertShop(Shop shop){
        long id = MyApplication.getDaoInstant().getShopDao().insertOrReplace(shop);
        return id >= 0;
    }

    /**
     * 根据id删除数据
     */
    public static void deleteShop(long id){
        MyApplication.getDaoInstant().getShopDao().deleteByKey(id);
    }

    public static void deleteAllShop(){
        MyApplication.getDaoInstant().getShopDao().deleteAll();
    }

    /**
     * 更新数据
     */
    public static void updateShop(Shop shop){
        MyApplication.getDaoInstant().getShopDao().update(shop);
    }

    /**
     * 查询type为1的所有商品
     */
    public static List<Shop> queryShopType1(){
        return MyApplication.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_1)).list();
    }

    /**
     * 查询所有商品
     */
    public static List<Shop> queryAll(){
        return MyApplication.getDaoInstant().getShopDao().loadAll();
    }
}