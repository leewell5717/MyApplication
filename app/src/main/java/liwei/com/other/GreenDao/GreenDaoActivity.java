package liwei.com.other.GreenDao;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;
import liwei.com.Utils;

public class GreenDaoActivity extends Activity {

    @BindView(R.id.insert_btn)
    public Button insertBtn;
    @BindView(R.id.delete_btn)
    public Button deleteBtn;
    @BindView(R.id.query_btn)
    public Button queryBtn;
    @BindView(R.id.modify_btn)
    public Button modifyBtn;
    @BindView(R.id.listview)
    public ListView listview;

    private ShopAdapter adapter;
    private List<Shop> shopList;

    private String[] names = {"正宗梅菜扣肉 聪厨梅干菜扣肉 家宴常备方便菜虎皮红烧肉 2盒包邮",
                    "虎皮青椒 正宗川菜酸辣口味 3袋包邮 先到先得 售完即止",
                    "鱼香肉丝 麻辣 酸辣 家常菜正宗川菜 1盒包邮",
                    "四川菜麻婆豆腐 麻辣鲜香 超麻 家常菜 2盒包邮"};
    private String[] imageUrls = {"https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=03c935b94c2309f7e36faa10420f0c39/64380cd7912397ddedc7b8295382b2b7d0a28764.jpg",
                "https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=f5454dc5590fd9f9a417526b152cd42b/8c1001e93901213fa6b32c655ce736d12e2e9582.jpg",
                "https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=16d3c3393c7adab439d01c41bbd5b36b/21a4462309f7905276e8372f06f3d7ca7bcbd528.jpg",
                "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D220/sign=0dd55e0fba7eca8016053ee5a1229712/8d5494eef01f3a2992312de29325bc315c607c25.jpg"};
    private String[] prices = {"38.82","19.4","29.6","25.2"};
    private int[] sealed = {1456,490,721,2378};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);

        shopList = new ArrayList<>();
        adapter = new ShopAdapter(this,shopList);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Shop shop = adapter.getItem(position);
                ShopDaoUtils.deleteShop(shop.getId());
                adapter.deleteDatas(position);
                return false;
            }
        });
    }

    /**
     * 增加
     */
    private void add(){
        Shop shop = new Shop();
        shop.setType(Shop.TYPE_1);
        Random random = new Random();
        shop.setName(names[random.nextInt(4)]);
        shop.setImage_url(imageUrls[random.nextInt(4)]);
        shop.setPrice(prices[random.nextInt(4)]);
        shop.setSell_num(sealed[random.nextInt(4)]);
        if(ShopDaoUtils.insertShop(shop)){
            Utils.showToastBottom("插入成功");
        }else{
            Utils.showToastBottom("插入失败");
        }
        adapter.addDatas(shop);
    }

    /**
     * 删除
     */
    private void delete(){
        if(adapter.getCount() == 0){
            Utils.showToastCenter("没有数据可删除了");
            return;
        }
        ShopDaoUtils.deleteAllShop();
        adapter.deleteAllDatas();
    }

    /**
     * 查询
     */
    private void query(){
        if(shopList.size() != 0){
            shopList.clear();
        }
        shopList = ShopDaoUtils.queryAll();
        if(shopList.size() == 0){
            Utils.showToastCenter("没有数据");
            return;
        }
        adapter.deleteAllDatas();
        adapter.addDatas(shopList);
    }

    /**
     * 修改
     */
    private void modify(){
        if(adapter.getCount() == 0){
            Utils.showToastBottom("没有可修改的数据");
            return;
        }
        Shop shop1 = adapter.getItem(adapter.getCount() - 1);
        shop1.setName("这是修改后的name");
        shop1.setPrice("666");
        shop1.setSell_num(6666);
        shop1.setImage_url("http://img1.imgtn.bdimg.com/it/u=1061758076,905679959&fm=27&gp=0.jpg");
        ShopDaoUtils.updateShop(shop1);
        query();
    }

    @OnClick({R.id.insert_btn,R.id.delete_btn,R.id.query_btn,R.id.modify_btn})
    public void click(View v){
        switch (v.getId()){
            case R.id.insert_btn:
                add();
                break;
            case R.id.delete_btn:
                delete();
                break;
            case R.id.query_btn:
                query();
                break;
            case R.id.modify_btn:
                modify();
                break;
        }
    }
}