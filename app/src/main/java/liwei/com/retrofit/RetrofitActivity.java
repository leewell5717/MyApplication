package liwei.com.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import liwei.com.R;
import liwei.com.retrofit.api.Api;
import liwei.com.retrofit.utils.RxFunction;
import liwei.com.retrofit.utils.RxManager;
import liwei.com.retrofit.utils.RxObserver;
import liwei.com.retrofit.utils.RxSchedulers;
import liwei.com.retrofit.utils2.SecondPackingActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends Activity {
    @BindView(R.id.get)
    public Button get;
    @BindView(R.id.post)
    public Button post;
    @BindView(R.id.error)
    public Button error;
    @BindView(R.id.packing_retrofit_json)
    public Button packingRetrofitJson;
    @BindView(R.id.packing_retrofit_bean)
    public Button packingRetrofitBean;
    @BindView(R.id.turn_packing_2)
    public Button turnPacking2;
    @BindView(R.id.get_result)
    public TextView getResult;
    @BindView(R.id.post_result)
    public TextView postResult;
    @BindView(R.id.packing_result_1)
    public TextView packingResult1;
    @BindView(R.id.packing_result_2)
    public TextView packingResult2;
    @BindView(R.id.recycler)
    public RecyclerView recycler;

    //可以配置在BaseActivity中
//    private final String TAG = getPackageName() + "." + getClass().getSimpleName();
    private static final String TAG = RetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.get,R.id.post,R.id.error,R.id.packing_retrofit_json,R.id.packing_retrofit_bean,R.id.turn_packing_2})
    public void click(View v){
        switch (v.getId()){
            case R.id.get:
                Retrofit retrofitGet = new Retrofit.Builder().baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create()).build();
                MyInterface postInterface = retrofitGet.create(MyInterface.class);
                Call<List<GitHubBean>> gitHubBeanCall = postInterface.listGitHubBeans("octocat");
                gitHubBeanCall.enqueue(new Callback<List<GitHubBean>>() {
                    @Override
                    public void onResponse(Call<List<GitHubBean>> call, Response<List<GitHubBean>> response) {
                        List<GitHubBean> dataList = response.body();
                        if(dataList != null && dataList.size() != 0){
                            getResult.setText("有"+dataList.size()+"条数据");
                            MyRecyclerAdapter adapter = new MyRecyclerAdapter(RetrofitActivity.this,dataList);
                            LinearLayoutManager llm = new LinearLayoutManager(RetrofitActivity.this,LinearLayoutManager.VERTICAL,false);
                            recycler.setLayoutManager(llm);
                            recycler.setAdapter(adapter);
                        }else{
                            getResult.setText("没有获得数据");
                        }
                    }
                    @Override
                    public void onFailure(Call<List<GitHubBean>> call, Throwable t) {
                        getResult.setText("error:"+t.getMessage()+"");
                    }
                });
                break;
            case R.id.post:
                Retrofit retrofitPost = new Retrofit.Builder().baseUrl("http://gc.ditu.aliyun.com/")
                        .addConverterFactory(GsonConverterFactory.create()).build();
                MyInterface getInterface = retrofitPost.create(MyInterface.class);
                Call<MapBean> getMapBean = getInterface.getMap("苏州市");
                getMapBean.enqueue(new Callback<MapBean>() {
                    @Override
                    public void onResponse(Call<MapBean> call, Response<MapBean> response) {
                        MapBean mapBean = response.body();
                        if(mapBean != null){
                            StringBuilder sb = new StringBuilder();
                            sb.append("返回的数据：").append("\n")
                                    .append("lon:").append(mapBean.getLon()).append("\n")
                                    .append("lat：").append(mapBean.getLat()).append("\n")
                                    .append("level：").append(mapBean.getLevel()).append("\n")
                                    .append("alevel:").append(mapBean.getAlevel()).append("\n")
                                    .append("address：").append(mapBean.getAddress()).append("\n")
                                    .append("cityName:").append(mapBean.getCityName());
                            postResult.setText(sb.toString());
                        }else{
                            postResult.setText("没有获得数据");
                        }
                    }
                    @Override
                    public void onFailure(Call<MapBean> call, Throwable t) {
                        postResult.setText("error:"+t.getMessage()+"");
                    }
                });
                break;
            case R.id.packing_retrofit_json:
                Api.getDefaultService().calendarJson("2017-8-30")
                        .subscribeOn(Schedulers.io())
                        // .compose(RxSchedulers.<String>io_main())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new RxObserver<String>(this, TAG, 0, true) {
                            @Override
                            public void onSuccess(int whichRequest, String s) {
                                packingResult1.setText(s+","+whichRequest);
                            }
                            @Override
                            public void onError(int whichRequest, Throwable e) {

                            }
                        });
                break;
            case R.id.packing_retrofit_bean:
                Api.getDefaultService()
                        .calendarBean("2017-8-30")
                        .map(new RxFunction<CalendarBean>())
                        .compose(RxSchedulers.<CalendarBean>io_main())
                        .subscribeWith(new RxObserver<CalendarBean>(this, TAG, 0, false) {
                            @Override
                            public void onSuccess(int whichRequest, CalendarBean calendar) {
                                packingResult2.setText(calendar.getLunar());
                            }
                            @Override
                            public void onError(int whichRequest, Throwable e) {

                            }
                        });
                break;
            case R.id.turn_packing_2:
                Intent intent = new Intent(RetrofitActivity.this, SecondPackingActivity.class);
                startActivity(intent);
                break;
            case R.id.error:
                throw new RuntimeException("I'm a cool exception and I crashed the main thread!");
        }
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerHolder>{

        private List<GitHubBean> dataLists;
        private Context context;

        private MyRecyclerAdapter(Context context,List<GitHubBean> dataLists){
            this.context = context;
            this.dataLists = dataLists;
        }

        @Override
        public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gitbub,parent,false);
            return new MyRecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(MyRecyclerHolder holder, int position) {
            GitHubBean gitHubBean = dataLists.get(position);
            holder.login.setText(gitHubBean.getLogin());
            holder.id.setText(String.valueOf(gitHubBean.getId()));
            holder.avatarUrl.setText(gitHubBean.getAvatar_url());
            holder.gravatarId.setText(gitHubBean.getGravatar_id());
            holder.url.setText(gitHubBean.getUrl());
            holder.htmlUrl.setText(gitHubBean.getHtml_url());
            holder.followersUrl.setText(gitHubBean.getFollowers_url());
            holder.followingUrl.setText(gitHubBean.getFollowing_url());
            holder.gistsUrl.setText(gitHubBean.getGists_url());
            holder.starredUrl.setText(gitHubBean.getStarred_url());
            holder.subscriptionsUrl.setText(gitHubBean.getSubscriptions_url());
            holder.organizationsUrl.setText(gitHubBean.getOrganizations_url());
            holder.reposUrl.setText(gitHubBean.getRepos_url());
            holder.eventsUrl.setText(gitHubBean.getEvents_url());
            holder.receivedEventsUrl.setText(gitHubBean.getReceived_events_url());
            holder.type.setText(gitHubBean.getType());
            holder.siteAdmin.setText(String.valueOf(gitHubBean.isSite_admin()));
        }

        @Override
        public int getItemCount() {
            return dataLists == null ? 0 : dataLists.size();
        }

        public class MyRecyclerHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.login)
            public TextView login;
            @BindView(R.id.text_id)
            public TextView id;
            @BindView(R.id.avatar_url)
            public TextView avatarUrl;
            @BindView(R.id.gravatar_id)
            public TextView gravatarId;
            @BindView(R.id.url)
            public TextView url;
            @BindView(R.id.html_url)
            public TextView htmlUrl;
            @BindView(R.id.followers_url)
            public TextView followersUrl;
            @BindView(R.id.following_url)
            public TextView followingUrl;
            @BindView(R.id.gists_url)
            public TextView gistsUrl;
            @BindView(R.id.starred_url)
            public TextView starredUrl;
            @BindView(R.id.subscriptions_url)
            public TextView subscriptionsUrl;
            @BindView(R.id.organizations_url)
            public TextView organizationsUrl;
            @BindView(R.id.repos_url)
            public TextView reposUrl;
            @BindView(R.id.events_url)
            public TextView eventsUrl;
            @BindView(R.id.received_events_url)
            public TextView receivedEventsUrl;
            @BindView(R.id.type)
            public TextView type;
            @BindView(R.id.site_admin)
            public TextView siteAdmin;

            public MyRecyclerHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxManager.getInstance().clear(TAG);
    }
}