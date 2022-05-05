package com.example.newmobileprojects.activityAll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newmobileprojects.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity{
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText et1,et2,et3,et4;
        Button login, join;
        et1 = (EditText)findViewById(R.id.n1);
        et1.setPrivateImeOptions("defaultInputmode=korean;");
        et2 = (EditText)findViewById(R.id.n2);
        login = (Button) findViewById(R.id.login_btn);
        join = (Button) findViewById(R.id.join_btn);

        firebaseAuth = firebaseAuth.getInstance();

//        findViewById(R.id.Male).setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Checked(v); // 체크되었을 때 동작코드
//            }
//        });
//
//        // option2 체크박스가 눌렸을 때
//        findViewById(R.id.Female).setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Checked(v); // 체크되었을 때 동작코드
//            }
//        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et1.getText().toString().trim();
                String pwd = et2.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인하는것
                firebaseAuth.signInWithEmailAndPassword(id, pwd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {//실패했을때
                                    Toast.makeText(LoginActivity.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });



    }
//    public String Checked(View v) { // 체크되었을 때 동작할 메소드 구현
//        // TODO Auto-generated method stub
//        CheckBox option1 = (CheckBox) findViewById(R.id.Male); // option1체크박스
//        // 선언
//        CheckBox option2 = (CheckBox) findViewById(R.id.Female); // option1체크박스
//        // 선언
//
//        String resultText = ""; // 체크되었을 때 값을 저장할 스트링 값
//        if (option1.isChecked()) { // option1 이 체크되었다면
//            resultText = "남자";
//        }
//        if (option2.isChecked()) {
//            resultText = "여자"; // option2 이 체크되었다면
//        }
//
//        return resultText; // 체크된 값 리턴
//    }

}




