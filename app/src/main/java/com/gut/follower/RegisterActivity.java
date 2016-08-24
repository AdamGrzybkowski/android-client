package com.gut.follower;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gut.follower.model.Account;
import com.gut.follower.utility.JConductorService;
import com.gut.follower.utility.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gut.follower.commons.InputValidator.checkIfUserCredentialsNotEmpty;
import static com.gut.follower.commons.InputValidator.createAccount;

public class RegisterActivity extends AppCompatActivity{

    private EditText email;
    private EditText username;
    private EditText password;
    private Button registerBtn;

    private JConductorService jConductorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        jConductorService = ServiceGenerator.createService(JConductorService.class);
    }

    private void initView() {
        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        if(checkIfUserCredentialsNotEmpty(username, password, email)){
            Account account = createAccount(username, password, email);
            Call<Account> call = jConductorService.register(account);
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        loginUser();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                       response.message(),
                                       Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),
                                   t.getMessage(),
                                   Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loginUser() {
        // TODO: This method is used in two Activities - we need to find out how to separate it.
        if(checkIfUserCredentialsNotEmpty(username, password)){
            Call<Account> call = jConductorService.login(createAccount(username, password));
            call.enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                       response.message(),
                                       Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),
                                   t.getMessage(),
                                   Toast.LENGTH_SHORT).show();
                }
            });

        }
        else
            Toast.makeText(getApplicationContext(),
                           "User credentials can not be empty",
                           Toast.LENGTH_SHORT).show();
    }
}
