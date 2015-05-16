package com.example.misakitatsuya.hello_world;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    final Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(calendar.YEAR);
    int mMonth = calendar.get(calendar.MONTH);
    int mDay = calendar.get(calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //グラフ画像の設定
        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
        SharedPreferences setting_pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
        SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
        SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
        SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
        int retorutogohan = pref.getInt("retorutogohan_num", 0);
        int kandume = pref.getInt("kandume_num", 0);
        int kanmen = pref.getInt("kanmen_num", 0);
        int kanpan = pref.getInt("kanpan_num", 0);
        int kan2 = pref.getInt("kandume2_num", 0);
        int retoruto = pref.getInt("retoruto_num", 0);
        int freaze = pref.getInt("freaze_num", 0);
        int mizu = pref.getInt("mizu_num", 0);
        int rinyu = pref.getInt("rinyu_num", 0);
        int konamilk = pref.getInt("konamilk_num", 0);
        int karori = pref.getInt("karori_num", 0);
        int okasi = pref.getInt("okasi_num",0);

        int setting_day = setting_pref.getInt("setting_key", 0);
        int adult_n = adult_pref.getInt("adult_key", 0);
        int child_n = children_pref.getInt("children_key", 0);
        int baby_n = baby_pref.getInt("baby_key", 0);

        int adult_need[][] = {{  3,  9, 21},{  3,  9, 21}};
        int child_need[][] = {{  2,  6, 14},{  2,  6, 14}};
        int baby_need[][] =  {{  3,  9, 21},{  2,  6, 14}};

        double food_meter = 0;
        double mizu_meter = 0;

        // A　=　大人必要数　ｘ　大人人数
        int adult_number = adult_need[0][1] * adult_n;
        // 入力されている量　=　乾パンｘ3　＋　カロリーメイトｘ3　＋　他・・・
        int sumfood = (kanpan * 3) + (karori * 3) + retorutogohan + kandume + kanmen + kan2 + retoruto + freaze + rinyu + konamilk + okasi;
        // 入力されている量　＞＝　A　→　50％
        if ( sumfood >= adult_number ) {
            food_meter = 50.0;
        } else { // 入力されている量　＜　A　→　入力されている量　÷　必要数　ｘ　100　÷　2
            food_meter = sumfood / adult_number * 100 / 2;
        }

        // 水
        // 入力されている量　=　水の量（本数？）
        int summizu = mizu;
        // 入力されている量　＞＝　A　→　50％
        //int summizu =
        // 入力されている量　＜　A　→　入力されている量　÷　必要数　ｘ　100　÷　2

        // C　=　小人必要数　ｘ　小人人数　
        // 入力されている量　=　乾パンｘ3　＋　カロリーメイトｘ3　＋　他・・・
        // 入力されている量　＞＝　C　→　50％
        // 入力されている量　＜　C　→　入力されている量　÷　必要数　ｘ　１００　÷　2
        // 水
        // 入力されている量　=　水の量（本数？）
        // 入力されている量　＞＝　C　→　50％
        // 入力されている量　＜　C　→　入力されている量　÷　必要数　ｘ　１００　÷　2

        // B　=　幼児必要数　ｘ　幼児人数　
        // 入力されている量　=　離乳食ｘ１　＋　粉ミルクｘ3
        // 入力されている量　＞＝　B　→　50％
        // 入力されている量　＜　B　→　入力されている量　÷　必要数　ｘ　１００　÷　2
        // 水
        // 入力されている量　=　水の量（本数？）
        // 入力されている量　＞＝　B　→　50％
        // 入力されている量　＜　B　→　入力されている量　÷　必要数　ｘ　１００　÷　2

        //日付表示
        Calendar cal = Calendar.getInstance();
        TextView tv = (TextView) findViewById(R.id.textView3);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        tv.setText(year + "年" + (month+1) + "月" + day + "日");

        //非常食最終入力日表示
        SharedPreferences lastyearpref = getSharedPreferences("lastyearpref", MODE_PRIVATE);
        SharedPreferences lastmonthpref = getSharedPreferences("lastmonthpref", MODE_PRIVATE);
        SharedPreferences lastdaypref = getSharedPreferences("lastdaypref", MODE_PRIVATE);
        int lastyear = lastyearpref.getInt("lastyear_key", 0);
        int lastmonth = lastmonthpref.getInt("lastmonth_key", 0);
        int lastday = lastdaypref.getInt("lastday_key", 0);
        lastmonth += 1;
        String laststr = "非常食最終変更日:" + lastyear + "年" + lastmonth + "月" + lastday + "日";
        TextView lasttv = (TextView)findViewById(R.id.hijolasttext);
        lasttv.setText(laststr);

        //備蓄品最終入力日表示
        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);
        int lastyearb = lastyearprefb.getInt("lastyear_keyb", 0);
        int lastmonthb = lastmonthprefb.getInt("lastmonth_keyb", 0);
        int lastdayb = lastdayprefb.getInt("lastday_keyb", 0);
        lastmonthb += 1;
        String laststrb = "備蓄品最終変更日:" + lastyearb + "年" + lastmonthb + "月" + lastdayb + "日";
        TextView lasttvb = (TextView)findViewById(R.id.bichikulasttext);
        lasttvb.setText(laststrb);

        //非常食合計
        int hijosum = retorutogohan + kandume + kanmen + kanpan + kan2 + retoruto + freaze + karori + rinyu + konamilk + okasi;
        String hijostr = "非常食の合計　"+String.valueOf(hijosum)+"個";
        TextView hijosum_tv = (TextView)findViewById(R.id.hijosum);
        hijosum_tv.setText(hijostr);

        //備蓄品合計
        SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
        int m = bichikupref.getInt("pop1num",0);
        int n = bichikupref.getInt("pop2num",0);
        int o = bichikupref.getInt("pop3num",0);
        int pp = bichikupref.getInt("pop4num",0);
        int q = bichikupref.getInt("pop5num",0);
        int r = bichikupref.getInt("pop6num",0);
        int s = bichikupref.getInt("pop7num",0);
        int t = bichikupref.getInt("pop8num",0);
        int bichikusum = m+n+o+pp+q+r+s+t;
        String bichikustr = "備蓄品の合計　"+String.valueOf(bichikusum)+"個";
        TextView bichikusum_tv = (TextView)findViewById(R.id.bichikusum);
        bichikusum_tv.setText(bichikustr);

        //アラート設定（レトルトご飯）
        Calendar today = Calendar.getInstance();
        SharedPreferences limit_pref1 = getSharedPreferences("limit_pref", MODE_PRIVATE);
        SharedPreferences year_pref1 = getSharedPreferences("year_pref1", MODE_PRIVATE);
        SharedPreferences month_pref1 = getSharedPreferences("month_pref1", MODE_PRIVATE);
        SharedPreferences day_pref1 = getSharedPreferences("day_pref1", MODE_PRIVATE);
        int limit = limit_pref1.getInt("limit_key", 0);
        String lyearstr = String.valueOf(year_pref1.getInt("year_key1", 0));
        String lmonthstr = String.valueOf(month_pref1.getInt("month_key1", 0));
        String ldaystr = String.valueOf(day_pref1.getInt("day_key1", 0));
        String toyearstr = String.valueOf(today.get(calendar.YEAR));
        String tomonthstr = String.valueOf(today.get(Calendar.MONTH));
        String todaystr = String.valueOf(today.get(calendar.DATE));
        double sa = 0.0;

        try{
            sa  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr + "/" + lmonthstr + "/" + ldaystr);
        } catch (ParseException e) {
            System.out.println(e);
        }


        if ( sa == 0.0 ) {
            TextView alert = (TextView)findViewById(R.id.retorutog);
            alert.setText("レトルトご飯の賞味期限は今日です。");
        } else if ( sa < 0.0 ) {
            TextView alert = (TextView)findViewById(R.id.retorutog);
            alert.setText("レトルトご飯の賞味期限が" + (int)sa * -1 + "日過ぎています。");
        } else {
            if ( sa < limit ) {
                TextView alert = (TextView)findViewById(R.id.retorutog);
                alert.setText("レトルトご飯の賞味期限は残り" + (int)sa + "日です。");
            } else {
                TextView alert = (TextView)findViewById(R.id.retorutog);
                alert.setText("");
            }
        }

        // テキスト画面遷移(Clickable)
        TextView testtext = (TextView)findViewById(R.id.retorutog);
        testtext.setClickable(true);
        testtext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.retorutog) {
                    //レトルトご飯ポップ
                    // アラートダイアログの出力
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("レトルトご飯、炊き込みご飯");
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    final View viw = inflater.inflate(R.layout.activity_retorutogohan, null);
                    // プリファレンスの生成
                    SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                    int i = 0;
                    i = pref.getInt("retorutogohan_num", i);
                    String str = String.valueOf(i);
                    // 必ずView変数で生成したデータを使うこと
                    EditText et = (EditText) viw.findViewById(R.id.retorutogohantext);
                    // 値をいれる
                    et.setText(str);

                    //人数の表示
                    SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                    SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                    SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                    int a = adult_pref.getInt("adult_key", 0);
                    int b = children_pref.getInt("children_key", 0);
                    int c = baby_pref.getInt("baby_key", 0);
                    String adult_str = "大人" + String.valueOf(a) + "人";
                    String children_str = "小人" + String.valueOf(b) + "人";
                    String baby_str = "幼児" + String.valueOf(c) + "人";
                    TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                    TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                    TextView baby_tv = (TextView) viw.findViewById(R.id.babytext);
                    adult_tv.setText(adult_str);
                    children_tv.setText(children_str);
                    baby_tv.setText(baby_str);

                    // 決定ボタンを押された時の処理
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                            EditText et = (EditText) viw.findViewById(R.id.retorutogohantext);
                            String str = et.getText().toString();
                            int i = Integer.parseInt(str);

                            SharedPreferences.Editor e = pref.edit();
                            e.putInt("retorutogohan_num", i);
                            e.commit();

                            //最終入力日の日付記録
                            SharedPreferences lastyearpref = getSharedPreferences("lastyearpref", MODE_PRIVATE);
                            SharedPreferences lastmonthpref = getSharedPreferences("lastmonthpref", MODE_PRIVATE);
                            SharedPreferences lastdaypref = getSharedPreferences("lastdaypref", MODE_PRIVATE);

                            SharedPreferences.Editor lastyear_e = lastyearpref.edit();
                            SharedPreferences.Editor lastmonth_e = lastmonthpref.edit();
                            SharedPreferences.Editor lastday_e = lastdaypref.edit();

                            lastyear_e.putInt("lastyear_key", mYear);
                            lastmonth_e.putInt("lastmonth_key", mMonth);
                            lastday_e.putInt("lastday_key", mDay);

                            lastyear_e.commit();
                            lastmonth_e.commit();
                            lastday_e.commit();
                        }
                    });
                    alert.setView(viw);
                    alert.show();

                    // カレンダー設定
                    SharedPreferences year_pref1 = getSharedPreferences("year_pref1", MODE_PRIVATE);
                    SharedPreferences month_pref1 = getSharedPreferences("month_pref1", MODE_PRIVATE);
                    SharedPreferences day_pref1 = getSharedPreferences("day_pref1", MODE_PRIVATE);
                    int limit_year1 = year_pref1.getInt("year_key1", mYear);
                    int limit_month1 = month_pref1.getInt("month_key1", mMonth);
                    int limit_day1 = day_pref1.getInt("day_key1", mDay);
                    String limit_str1 = "賞味期限は" + String.valueOf(limit_year1) + "年" + String.valueOf(limit_month1 + 1) + "月" + String.valueOf(limit_day1) + "日です。";
                    TextView limit_tv1 = (TextView) viw.findViewById(R.id.textView30);
                    limit_tv1.setText(limit_str1);

                    Button calbutton = (Button) viw.findViewById(R.id.calbutton);
                    calbutton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                            DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                                public void onDateSet(
                                        DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    mYear = year;
                                    mMonth = monthOfYear;
                                    mDay = dayOfMonth;

                                    SharedPreferences year_pref1 = getSharedPreferences("year_pref1", MODE_PRIVATE);
                                    SharedPreferences month_pref1 = getSharedPreferences("month_pref1", MODE_PRIVATE);
                                    SharedPreferences day_pref1 = getSharedPreferences("day_pref1", MODE_PRIVATE);

                                    SharedPreferences.Editor year_e1 = year_pref1.edit();
                                    SharedPreferences.Editor month_e1 = month_pref1.edit();
                                    SharedPreferences.Editor day_e1 = day_pref1.edit();
                                    year_e1.putInt("year_key1", mYear);
                                    month_e1.putInt("month_key1", mMonth);
                                    day_e1.putInt("day_key1", mDay);
                                    year_e1.commit();
                                    month_e1.commit();
                                    day_e1.commit();

                                    int limit_year1 = year_pref1.getInt("year_key1", mYear);
                                    int limit_month1 = month_pref1.getInt("month_key1", mMonth);
                                    int limit_day1 = day_pref1.getInt("day_key1", mDay);
                                    String limit_str1 = "賞味期限は" + String.valueOf(limit_year1) + "年" + String.valueOf(limit_month1 + 1) + "月" + String.valueOf(limit_day1) + "日です。";
                                    TextView limit_tv1 = (TextView) viw.findViewById(R.id.textView30);
                                    limit_tv1.setText(limit_str1);
                                }
                            };
                            // DatePickerDialog の作成
                            DatePickerDialog datePickerDialog1 = new DatePickerDialog(MainActivity.this, listener, mYear, mMonth, mDay);

                            //DatePickerDialog の表示
                            datePickerDialog1.show();
                        }
                    });

                }
            }
        });

        //ボタンの場所指定(設定)
        Button btnsetting = (Button)findViewById(R.id.button_segue);

        btnsetting.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.setting");
                startActivity(intent);//画面を出す
            }
        });

        //ボタン場所指定(備蓄品)
        Button btnbichiku = (Button)findViewById(R.id.button3);

        btnbichiku.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.bichiku");
                startActivity(intent);//画面を出す
            }
        });

        //ボタン場所指定(非常食)
        Button btnhijo = (Button)findViewById(R.id.button2);

        btnhijo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.hijo");
                startActivity(intent);//画面を出す
            }
        });
    }

    private double differenceDays(String strDate1, String strDate2)
            throws ParseException {
        int ret = 0;

//        SimpleDateFormat formatA = new SimpleDateFormat("yyyy/MM/dd");

        Date date1 = SimpleDateFormat.getDateInstance().parse(strDate1);
        Date date2 = SimpleDateFormat.getDateInstance().parse(strDate2);

        // 日付を比較.
        int diff = date1.compareTo(date2);
        if (diff == 0) {//日付1と日付2は同じ.
            return ret;
        } else if (diff > 0) {//日付1は日付2より未来の日付.
            ret = (int)(date2.getTime() - date1.getTime());
        } else {//日付2は日付1より未来の日付.
            ret = (int)(date2.getTime() - date1.getTime());
        }
        return ret / 1000 / 60 / 60 / 24.0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
