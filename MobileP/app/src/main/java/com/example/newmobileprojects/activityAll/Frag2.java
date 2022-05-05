package com.example.newmobileprojects.activityAll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.newmobileprojects.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Frag2 extends Fragment {
    private ListView listView;
    private EditText editText;
    private Button button;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> room = new ArrayList<>();

    private String chat_room_name, chat_user_name;

    private DatabaseReference reference;
    private String key;
    private String chat_user;
    private String chat_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vs = inflater.inflate(R.layout.test2, null);


        listView = (ListView) vs.findViewById(R.id.list);
        editText = (EditText) vs.findViewById(R.id.editText);
        button = (Button) vs.findViewById(R.id.button);

        if(getArguments() != null){
            chat_user_name = getArguments().getString("chat_user_name");
            chat_room_name = getArguments().getString("chat_room_name");
            getActivity().setTitle(chat_room_name + " 채팅방");
            reference = FirebaseDatabase.getInstance().getReference().child(chat_room_name);

            arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, room);
            listView.setAdapter(arrayAdapter);
            button.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {

                    Map<String, Object> map = new HashMap<String, Object>();
                    key = reference.push().getKey();

                    reference.updateChildren(map);

                    DatabaseReference root = reference.child(key);

                    Map<String, Object> objectMap = new HashMap<String, Object>();

                    objectMap.put("name", chat_user_name);
                    objectMap.put("message", editText.getText().toString());

                    root.updateChildren(objectMap);

                    editText.setText("");
                }
            });

            reference.addChildEventListener(new ChildEventListener() {
                @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    chatConversation(dataSnapshot);
                }

                @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    chatConversation(dataSnapshot);
                }

                @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{

        }





        return vs;
    }

    private void chatConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            chat_message = (String) ((DataSnapshot) i.next()).getValue();
            chat_user = (String) ((DataSnapshot) i.next()).getValue();

            arrayAdapter.add(chat_user + " : " + chat_message);
        }

        arrayAdapter.notifyDataSetChanged();
    }

}