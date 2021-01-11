package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minim2.models.MyAdapter;
import com.example.minim2.models.Repo;
import com.example.minim2.models.User;
import com.example.minim2.service.gitHubService;
import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserView extends AppCompatActivity {

    User infoUser;

    private TextView textName;
    private TextView textFollowers;
    private TextView textFollowing;
    private ImageView imagenUser;
    private RecyclerView myRecycler;
    private MyAdapter myAdapter;
    private gitHubService gitHubAPI;

    private List<Repo> reposList;

    private Loading loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textName=findViewById(R.id.textUserName);
        textFollowers=findViewById(R.id.textFollowers);
        textFollowing=findViewById(R.id.textFollowing);
        myRecycler=findViewById(R.id.myRecycler);
        imagenUser=(ImageView)findViewById(R.id.imageUser);

        infoUser=(User)getIntent().getExtras().getSerializable("User");


        loadingScreen = new Loading(UserView.this);
        loadingScreen.startLoadingDialog();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        Retrofit retrofitinstance = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        gitHubAPI = retrofitinstance.create(gitHubService.class);

        searchRepos(infoUser.getLogin());
        //Ponemos cosas en text i image

        textName.setText(infoUser.getLogin());
        String followersTextDisplay = textFollowers.getText ().toString() + infoUser.getFollowers();
        String followingTextDisplay = textFollowing.getText ().toString() + infoUser.getFollowing();

        textFollowing.setText(followingTextDisplay);
        textFollowers.setText(followersTextDisplay);
        //Ponemos imagen con el Picaso

        Picasso.get().load(infoUser.getAvatar_url()).into(imagenUser);//para conseguir la imagen



    }

    private void searchRepos(String login) {
        Call<List<Repo>> call = gitHubAPI.getReposUser(login);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                if(response.code()==200) {

                    reposList=response.body();
                    //Mostramos recycler view

                    myRecycler.setHasFixedSize(true);
                    myRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myAdapter = new MyAdapter(getApplicationContext(),reposList);
                    myRecycler.setAdapter(myAdapter);
                    loadingScreen.dismissDialog();


                }
                else{
                    //Mostramos pantalla d'error
                    loadingScreen.dismissDialog();
                    Intent intent = new Intent(getApplicationContext(),ErrorView.class);
                    intent.putExtra("Error",String.valueOf(response.code()));
                    startActivity(intent);

                }


            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                loadingScreen.dismissDialog();
                Intent intent = new Intent(getApplicationContext(),ErrorView.class);
                intent.putExtra("Error",t.getMessage());
                startActivity(intent);


            }
        });





    }
}