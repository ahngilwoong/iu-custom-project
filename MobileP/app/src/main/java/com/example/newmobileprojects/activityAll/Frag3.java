package com.example.newmobileprojects.activityAll;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.newmobileprojects.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Frag3 extends Fragment {

    TextView  et,et2,Amain,Bmain,Cmain;
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_WEEK);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v3 = inflater.inflate(R.layout.fragment3, null);
        et = (TextView) v3.findViewById(R.id.textV);
        et2 = (TextView) v3.findViewById(R.id.textV2);
        Amain = (TextView) v3.findViewById(R.id.Amain);
        Bmain = (TextView) v3.findViewById(R.id.Bmain);
        Cmain = (TextView) v3.findViewById(R.id.Cmain);

        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd"); // 오늘의 날짜를 불러옴
        String today = sdf.format(date)+"일"+"\n ♥ 오늘의 늘빛관 학식 메뉴 ♥";
        et.setText(today);

        if(day == 0 || day ==7) { // 주말일 경우
            et2.setText("\n주말은 학식을 운영하지 않습니다.\n");
            Amain.setText("주말은 학식을 운영하지 않습니다.");
            Bmain.setText("주말은 학식을 운영하지 않습니다.");
            Cmain.setText("주말은 학식을 운영하지 않습니다.");
        }else{  // 주말이 아닐 경우
            GetTitle task = new GetTitle();
            task.execute();
        }
            return v3;
    }

    private class GetTitle extends AsyncTask<Void, Void, Map<String,String>> { // jsoup을 이용한 파싱
        Calendar cal = Calendar.getInstance();
        ArrayList<String> ar = new ArrayList<>();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        @Override
        protected Map<String, String> doInBackground(Void... params) { // Map을 이용하여 파싱한 데이터를 저장
            Map<String,String> result = new HashMap<String,String>();
            String url = "https://www.inje.ac.kr/kor/Template/Bsub_page.asp?Ltype=5&Ltype2=3&Ltype3=3&Tname=S_Food&Ldir=board/S_Food&Lpage=s_food_view&d1n=5&d2n=4&d3n=4&d4n=0#lnb";
            int cnt= 0;
            try {
                Document document = Jsoup.connect(url).get();
                Elements elements = document.select("div.bl-box");
                result.put("week", elements.text());
                elements = document.select("table tr td:eq("+day+")"); // 인제대 학식 HTML 테이블 안에 내용 가져옴 ( 일 단위로 가져옴 )
                for(Element element: elements){
                    if(cnt==5)break; // 5번까지가 늘빛관 학식에 관한 내용 그 이후는 하연관 학식에 관한 내용
                    ar.add(element.text()); // A메뉴 B메뉴 C메뉴를 어레이 리스트에 저장
                    cnt++;
                }
                result.put("menuA", ar.get(0));
                result.put("menuB", ar.get(1));
                result.put("menuC", ar.get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map) { // 행위
            et2.setText("\n"+map.get("week")+" 주차"+"\n");
            Amain.setText("\n"+"\n"+"\n"+map.get("menuA")+"\n");
            Bmain.setText("\n"+"\n"+"\n"+map.get("menuB")+"\n");
            Cmain.setText("\n"+"\n"+"\n"+map.get("menuC")+"\n");
        }
    }



}
