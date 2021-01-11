package com.example.minim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.minim2.models.User;
import com.example.minim2.service.gitHubService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private gitHubService gitHubAPI;
    private Loading loadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Connectem a retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
        Retrofit retrofitinstance = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        gitHubAPI = retrofitinstance.create(gitHubService.class);



    }

    public void sendButtonClick(View v){

        userName = findViewById(R.id.textName);//Recuperamos el nombre del usuario
        String nameOfUser = userName.getText().toString();
        //loadingScreen = new Loading(MainActivity.this);
        //loadingScreen.startLoadingDialog();
        buscarInfo(nameOfUser);

    }

    private void buscarInfo(String nameOfUser) {
        Call<User> call = gitHubAPI.getInfoUser(nameOfUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){

                    //Guardamos el usuario que recibimos en nuestra variable
                    User infoUser = User.getInstance();
                    infoUser.setLogin(response.body().getLogin());
                    infoUser.setAvatar_url(response.body().getAvatar_url());
                    infoUser.setFollowers(response.body().getFollowers());
                    infoUser.setFollowing(response.body().getFollowing());
                    infoUser.setRepos_url(response.body().getRepos_url());

                    Intent intent = new Intent(getApplicationContext(),UserView.class);
                    intent.putExtra("User",infoUser);
                    startActivity(intent);
                    //loadingScreen.dismissDialog();
                }
                else{
                    //Mostramos pantalla d'error
                    //loadingScreen.dismissDialog();
                    Intent intent = new Intent(getApplicationContext(),ErrorView.class);
                    intent.putExtra("Error",String.valueOf(response.code()));
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //loadingScreen.dismissDialog();
                Intent intent = new Intent(getApplicationContext(),ErrorView.class);
                intent.putExtra("Error",t.getMessage());
                startActivity(intent);

            }
        });

    }


}