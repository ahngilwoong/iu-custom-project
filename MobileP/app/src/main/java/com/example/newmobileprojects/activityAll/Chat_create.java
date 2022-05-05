package com.example.newmobileprojects.activityAll;

//public class Chat_create extends AppCompatActivity {
//
//    EditText user_chat,user_edit;
//    Button go;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.create_chatroom);
//        user_chat = (EditText) findViewById(R.id.user_chat);
//        user_edit = (EditText) findViewById(R.id.user_edit);
//        go = (Button) findViewById(R.id.user_next);
//
//        go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Chat_create.this, MainActivity.class);
//                intent.putExtra("chatname",user_chat.getText().toString());
//                intent.putExtra("nickname",user_edit.getText().toString());
//                startActivity(intent);
//            }
//        });
//    }
//
//}