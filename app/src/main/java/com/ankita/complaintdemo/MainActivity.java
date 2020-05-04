package com.ankita.complaintdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.Toast;

import com.ankita.complaintdemo.adapter.ItemComplaintAdapter;
import com.ankita.complaintdemo.repository.APIClient;
import com.ankita.complaintdemo.repository.APIInterface;
import com.ankita.complaintdemo.repository.CreateResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvItemComplaint;
    APIInterface apiInterface;
    ItemComplaintAdapter itemComplaintAdapter,itemComplaintAdapter1;
    ArrayList<CreateResponse.User> userArrayList;
    CreateResponse.User user;
    boolean isLoading=false;
    ProgressBar pbLoading;
    TabLayout tabLayout;
    int page_no=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        callApi(page_no);
        initScrollListener();
tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if(tab.getPosition()==1){
            itemComplaintAdapter1=new ItemComplaintAdapter(MainActivity.this,ItemComplaintAdapter.getPandingList());
            rvItemComplaint.setAdapter(itemComplaintAdapter1);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});


    }
    private void initScrollListener() {
        rvItemComplaint.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == userArrayList.size() - 1) {
                        //bottom of list!
                        loadMore();

                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        pbLoading.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


//                int scrollPosition = userArrayList.size(); // 10
//                int currentSize = scrollPosition; // 10
//                int nextLimit = currentSize + 10; // 20
//
//                while (currentSize < nextLimit) { // 20<40
//                    currentSize++;
//
//                }
                if(page_no!=4){page_no+=1;
                    callApi(page_no);}
                else {
                    Toast.makeText(MainActivity.this,"four pages is loaded",Toast.LENGTH_SHORT).show();
                }

                Log.d("###", "" + page_no);
                pbLoading.setVisibility(View.GONE);
                isLoading = false;

            }
        }, 2550);
    }
    private void callApi(int no) {
        Call<CreateResponse> responseCall=apiInterface.doGetListResour(1,1
                ,no,10,"UserName","ASC");

        responseCall.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                CreateResponse response1=response.body();
                Log.d("___@___",response.code()+" ");
                if(response1!=null){
                    for(CreateResponse.User usr:response1.data.obj.comp_list){
                        Log.d("___@___",usr.code);
                        userArrayList.add(usr);
                    }
                    setAdapter();
                }
                else {
                    Log.d("___@___","data is not available");
                }

            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.d("___@___","Failed");

            }
        });
    }

    private void setAdapter() {
        itemComplaintAdapter.notifyItemInserted(itemComplaintAdapter.getItemCount()-1);
           }

    private void init() {
        rvItemComplaint=findViewById(R.id.rvItemComplaint);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvItemComplaint.setLayoutManager(linearLayoutManager);
        apiInterface= APIClient.getClient().create(APIInterface.class);
        userArrayList=new ArrayList<>();
        itemComplaintAdapter=new ItemComplaintAdapter(this,userArrayList);
        rvItemComplaint.setAdapter(itemComplaintAdapter);
        pbLoading=findViewById(R.id.pbLoading);
        tabLayout=findViewById(R.id.tabLayout);

    }
}
