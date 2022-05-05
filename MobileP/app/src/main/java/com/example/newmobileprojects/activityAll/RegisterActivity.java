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
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        EditText et1,et2,et3,et4,et5;
        et1 = (EditText)findViewById(R.id.n1);
        et1.setPrivateImeOptions("defaultInputmode=korean;");
        et2 = (EditText)findViewById(R.id.n2);
        et3 = (EditText)findViewById(R.id.n3);
        et4 = (EditText)findViewById(R.id.n4);
        et5 = (EditText)findViewById(R.id.n5);





        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et1.getText().toString().trim();
                String student_num = et2.getText().toString().trim();
                String student_password = et3.getText().toString().trim();
                String place = et4.getText().toString().trim();
                String phone_number = et5.getText().toString().trim();


                mAuth.createUserWithEmailAndPassword(student_num, student_password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(RegisterActivity.this, "가입이 완료되었습니다. 등록된 학번으로 다시 로그인해주세요", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });


                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                intent.putExtra("it_check", Checked(view));
                startActivity(intent);
            }
        });

    }
//
//    public void register(String name, String student_num, String student_password, String place, String phone_number) {
//
//        //여기에서 직접 변수를 만들어서 값을 직접 넣는것도 가능합니다.
//        // ex) 갓 태어난 동물만 입력해서 int age=1; 등을 넣는 경우
//
//        //User.java에서 선언했던 함수.
//
//        //child는 해당 키 위치로 이동하는 함수입니다.
//        //키가 없는데 "user_info"와 name같이 값을 지정한 경우 자동으로 생성합니다.
//        databaseReference.child("user_info").child(name).setValue(user);
//
//    }
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




