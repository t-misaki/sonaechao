package com.example.misakitatsuya.hello_world;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class hijo extends ActionBarActivity {

    final Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(calendar.YEAR);
    int mMonth = calendar.get(calendar.MONTH);
    int mDay = calendar.get(calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hijo);

        //レトルトご飯ポップ
        ImageButton btnretorutogohan = (ImageButton) findViewById(R.id.ImageButton);
        btnretorutogohan.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("レトルトご飯、炊き込みご飯");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
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
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.retorutogohantext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
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
                        DatePickerDialog datePickerDialog1 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog1.show();
                    }
                });
            }
        });


        //缶詰ポップ
        ImageButton btnkandume = (ImageButton) findViewById(R.id.kandumebutton);
        btnkandume.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("缶詰（ご飯）");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_kandume, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("kandume_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kandumetext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kandumetext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("kandume_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref2 = getSharedPreferences("year_pref2",MODE_PRIVATE);
                SharedPreferences month_pref2 = getSharedPreferences("month_pref2", MODE_PRIVATE);
                SharedPreferences day_pref2 = getSharedPreferences("day_pref2", MODE_PRIVATE);
                int limit_year2 = year_pref2.getInt("year_key2",mYear);
                int limit_month2 = month_pref2.getInt("month_key2",mMonth);
                int limit_day2 = day_pref2.getInt("day_key2",mDay);
                String limit_str2 = "賞味期限は" + String.valueOf(limit_year2) + "年" + String.valueOf(limit_month2+1) + "月" + String.valueOf(limit_day2) + "日です。";
                TextView limit_tv2 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv2.setText(limit_str2);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
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

                                SharedPreferences year_pref2 = getSharedPreferences("year_pref2",MODE_PRIVATE);
                                SharedPreferences month_pref2 = getSharedPreferences("month_pref2", MODE_PRIVATE);
                                SharedPreferences day_pref2 = getSharedPreferences("day_pref2", MODE_PRIVATE);

                                SharedPreferences.Editor year_e2 = year_pref2.edit();
                                SharedPreferences.Editor month_e2 = month_pref2.edit();
                                SharedPreferences.Editor day_e2 = day_pref2.edit();
                                year_e2.putInt("year_key2", mYear);
                                month_e2.putInt("month_key2", mMonth);
                                day_e2.putInt("day_key2", mDay);
                                year_e2.commit();
                                month_e2.commit();
                                day_e2.commit();

                                int limit_year2 = year_pref2.getInt("year_key2",mYear);
                                int limit_month2 = month_pref2.getInt("month_key2",mMonth);
                                int limit_day2 = day_pref2.getInt("day_key2",mDay);
                                String limit_str2 = "賞味期限は" + String.valueOf(limit_year2) + "年" + String.valueOf(limit_month2+1) + "月" + String.valueOf(limit_day2) + "日です。";
                                TextView limit_tv2 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv2.setText(limit_str2);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog2 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog2.show();
                    }
                });
            }
        });

        //乾麺ポップ
        ImageButton btnkanmen = (ImageButton) findViewById(R.id.kanmenbutton);
        btnkanmen.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                        AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                        alert.setTitle("乾麺、即席めん、カップ麺");
                        LayoutInflater inflater = LayoutInflater.from(hijo.this);
                        final View viw = inflater.inflate(R.layout.activity_kanmen, null);
                        // プリファレンスの生成
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        int i = 0;
                        i = pref.getInt("kanmen_num", i);
                        String str = String.valueOf(i);
                        // 必ずView変数で生成したデータを使うこと
                        EditText et = (EditText) viw.findViewById(R.id.kanmentext);
                        // 値をいれる
                        et.setText(str);

                        //人数の表示
                        SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                        SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                        int a = adult_pref.getInt("adult_key", 0);
                        int b = children_pref.getInt("children_key", 0);
                        String adult_str = "大人" + String.valueOf(a) + "人";
                        String children_str = "小人" + String.valueOf(b) + "人";
                        TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                        TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                        adult_tv.setText(adult_str);
                        children_tv.setText(children_str);

                        // 決定ボタンを押された時の処理
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                                EditText et = (EditText) viw.findViewById(R.id.kanmentext);
                                String str = et.getText().toString();
                                if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                                    str = "0";
                                }
                                int i = Integer.parseInt(str);
                                SharedPreferences.Editor e = pref.edit();
                                e.putInt("kanmen_num", i);
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

                        //カレンダー設定
                        SharedPreferences year_pref3 = getSharedPreferences("year_pref3", MODE_PRIVATE);
                        SharedPreferences month_pref3 = getSharedPreferences("month_pref3", MODE_PRIVATE);
                        SharedPreferences day_pref3 = getSharedPreferences("day_pref3", MODE_PRIVATE);
                        int limit_year3 = year_pref3.getInt("year_key3", mYear);
                        int limit_month3 = month_pref3.getInt("month_key3", mMonth);
                        int limit_day3 = day_pref3.getInt("day_key3", mDay);
                        String limit_str3 = "賞味期限は" + String.valueOf(limit_year3) + "年" + String.valueOf(limit_month3 + 1) + "月" + String.valueOf(limit_day3) + "日です。";
                        TextView limit_tv3 = (TextView) viw.findViewById(R.id.textView30);
                        limit_tv3.setText(limit_str3);

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

                                        SharedPreferences year_pref3 = getSharedPreferences("year_pref3", MODE_PRIVATE);
                                        SharedPreferences month_pref3 = getSharedPreferences("month_pref3", MODE_PRIVATE);
                                        SharedPreferences day_pref3 = getSharedPreferences("day_pref3", MODE_PRIVATE);

                                        SharedPreferences.Editor year_e3 = year_pref3.edit();
                                        SharedPreferences.Editor month_e3 = month_pref3.edit();
                                        SharedPreferences.Editor day_e3 = day_pref3.edit();
                                        year_e3.putInt("year_key3", mYear);
                                        month_e3.putInt("month_key3", mMonth);
                                        day_e3.putInt("day_key3", mDay);
                                        year_e3.commit();
                                        month_e3.commit();
                                        day_e3.commit();

                                        int limit_year3 = year_pref3.getInt("year_key3", mYear);
                                        int limit_month3 = month_pref3.getInt("month_key3", mMonth);
                                        int limit_day3 = day_pref3.getInt("day_key3", mDay);
                                        String limit_str3 = "賞味期限は" + String.valueOf(limit_year3) + "年" + String.valueOf(limit_month3 + 1) + "月" + String.valueOf(limit_day3) + "日です。";
                                        TextView limit_tv3 = (TextView) viw.findViewById(R.id.textView30);
                                        limit_tv3.setText(limit_str3);
                                    }
                                };
                                // DatePickerDialog の作成
                                DatePickerDialog datePickerDialog3 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                                //DatePickerDialog の表示
                                datePickerDialog3.show();
                    }
                });
            }
        });

        //乾パンポップ
        ImageButton btnkanpan = (ImageButton) findViewById(R.id.kanpanbutton);
        btnkanpan.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("乾パン");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_kanpan, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("kanpan_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kanpantext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kanpantext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("kanpan_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref4 = getSharedPreferences("year_pref4",MODE_PRIVATE);
                SharedPreferences month_pref4 = getSharedPreferences("month_pref4", MODE_PRIVATE);
                SharedPreferences day_pref4 = getSharedPreferences("day_pref4", MODE_PRIVATE);
                int limit_year4 = year_pref4.getInt("year_key4",mYear);
                int limit_month4 = month_pref4.getInt("month_key4",mMonth);
                int limit_day4 = day_pref4.getInt("day_key4",mDay);
                String limit_str4 = "賞味期限は" + String.valueOf(limit_year4) + "年" + String.valueOf(limit_month4+1) + "月" + String.valueOf(limit_day4) + "日です。";
                TextView limit_tv4 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv4.setText(limit_str4);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
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

                                SharedPreferences year_pref4 = getSharedPreferences("year_pref4",MODE_PRIVATE);
                                SharedPreferences month_pref4 = getSharedPreferences("month_pref4", MODE_PRIVATE);
                                SharedPreferences day_pref4 = getSharedPreferences("day_pref4", MODE_PRIVATE);

                                SharedPreferences.Editor year_e4 = year_pref4.edit();
                                SharedPreferences.Editor month_e4 = month_pref4.edit();
                                SharedPreferences.Editor day_e4 = day_pref4.edit();
                                year_e4.putInt("year_key4", mYear);
                                month_e4.putInt("month_key4", mMonth);
                                day_e4.putInt("day_key4", mDay);
                                year_e4.commit();
                                month_e4.commit();
                                day_e4.commit();

                                int limit_year4 = year_pref4.getInt("year_key4",mYear);
                                int limit_month4 = month_pref4.getInt("month_key4",mMonth);
                                int limit_day4 = day_pref4.getInt("day_key4",mDay);
                                String limit_str4 = "賞味期限は" + String.valueOf(limit_year4) + "年" + String.valueOf(limit_month4+1) + "月" + String.valueOf(limit_day4) + "日です。";
                                TextView limit_tv4 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv4.setText(limit_str4);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog4 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog4.show();
                    }
                });
            }
        });

        //缶詰主菜ポップ
        ImageButton btnkandume2 = (ImageButton) findViewById(R.id.kandumebutton2);
        btnkandume2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("肉・魚・豆などの缶詰");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_kandume_syusai, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("kandume2_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kandume_syusaitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kandume_syusaitext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("kandume2_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref5 = getSharedPreferences("year_pref5",MODE_PRIVATE);
                SharedPreferences month_pref5 = getSharedPreferences("month_pref5", MODE_PRIVATE);
                SharedPreferences day_pref5 = getSharedPreferences("day_pref5", MODE_PRIVATE);
                int limit_year5 = year_pref5.getInt("year_key5",mYear);
                int limit_month5 = month_pref5.getInt("month_key5",mMonth);
                int limit_day5 = day_pref5.getInt("day_key5",mDay);
                String limit_str5 = "賞味期限は" + String.valueOf(limit_year5) + "年" + String.valueOf(limit_month5+1) + "月" + String.valueOf(limit_day5) + "日です。";
                TextView limit_tv5 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv5.setText(limit_str5);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
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

                                SharedPreferences year_pref5 = getSharedPreferences("year_pref5",MODE_PRIVATE);
                                SharedPreferences month_pref5 = getSharedPreferences("month_pref5", MODE_PRIVATE);
                                SharedPreferences day_pref5 = getSharedPreferences("day_pref5", MODE_PRIVATE);

                                SharedPreferences.Editor year_e5 = year_pref5.edit();
                                SharedPreferences.Editor month_e5 = month_pref5.edit();
                                SharedPreferences.Editor day_e5 = day_pref5.edit();
                                year_e5.putInt("year_key5", mYear);
                                month_e5.putInt("month_key5", mMonth);
                                day_e5.putInt("day_key5", mDay);
                                year_e5.commit();
                                month_e5.commit();
                                day_e5.commit();

                                int limit_year5 = year_pref5.getInt("year_key5",mYear);
                                int limit_month5 = month_pref5.getInt("month_key5",mMonth);
                                int limit_day5 = day_pref5.getInt("day_key5",mDay);
                                String limit_str5 = "賞味期限は" + String.valueOf(limit_year5) + "年" + String.valueOf(limit_month5+1) + "月" + String.valueOf(limit_day5) + "日です。";
                                TextView limit_tv5 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv5.setText(limit_str5);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog5 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog5.show();
                    }
                });
            }
        });

        //レトルトポップ
        ImageButton btnretoruto = (ImageButton) findViewById(R.id.retorutobutton);
        btnretoruto.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("レトルト食品");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_retoruto, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("retoruto_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.retorutotext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.retorutotext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("retoruto_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref6 = getSharedPreferences("year_pref6",MODE_PRIVATE);
                SharedPreferences month_pref6 = getSharedPreferences("month_pref6", MODE_PRIVATE);
                SharedPreferences day_pref6 = getSharedPreferences("day_pref6", MODE_PRIVATE);
                int limit_year6 = year_pref6.getInt("year_key6",mYear);
                int limit_month6 = month_pref6.getInt("month_key6",mMonth);
                int limit_day6 = day_pref6.getInt("day_key6",mDay);
                String limit_str6 = "賞味期限は" + String.valueOf(limit_year6) + "年" + String.valueOf(limit_month6+1) + "月" + String.valueOf(limit_day6) + "日です。";
                TextView limit_tv6 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv6.setText(limit_str6);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
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

                                SharedPreferences year_pref6 = getSharedPreferences("year_pref6",MODE_PRIVATE);
                                SharedPreferences month_pref6 = getSharedPreferences("month_pref6", MODE_PRIVATE);
                                SharedPreferences day_pref6 = getSharedPreferences("day_pref6", MODE_PRIVATE);

                                SharedPreferences.Editor year_e6 = year_pref6.edit();
                                SharedPreferences.Editor month_e6 = month_pref6.edit();
                                SharedPreferences.Editor day_e6 = day_pref6.edit();
                                year_e6.putInt("year_key6", mYear);
                                month_e6.putInt("month_key6", mMonth);
                                day_e6.putInt("day_key6", mDay);
                                year_e6.commit();
                                month_e6.commit();
                                day_e6.commit();

                                int limit_year6 = year_pref6.getInt("year_key6",mYear);
                                int limit_month6 = month_pref6.getInt("month_key6",mMonth);
                                int limit_day6 = day_pref6.getInt("day_key6",mDay);
                                String limit_str6 = "賞味期限は" + String.valueOf(limit_year6) + "年" + String.valueOf(limit_month6+1) + "月" + String.valueOf(limit_day6) + "日です。";
                                TextView limit_tv6 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv6.setText(limit_str6);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog6 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog6.show();
                    }
                });
            }
        });

        //フリーズドライポップ
        ImageButton btnfreaze = (ImageButton) findViewById(R.id.freazedry_button);
        btnfreaze.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("フリーズドライ");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_freaze, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("freaze_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.freazetext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.freazetext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("freaze_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref7 = getSharedPreferences("year_pref7",MODE_PRIVATE);
                SharedPreferences month_pref7 = getSharedPreferences("month_pref7", MODE_PRIVATE);
                SharedPreferences day_pref7 = getSharedPreferences("day_pref7", MODE_PRIVATE);
                int limit_year7 = year_pref7.getInt("year_key7",mYear);
                int limit_month7 = month_pref7.getInt("month_key7",mMonth);
                int limit_day7 = day_pref7.getInt("day_key7",mDay);
                String limit_str7 = "賞味期限は" + String.valueOf(limit_year7) + "年" + String.valueOf(limit_month7+1) + "月" + String.valueOf(limit_day7) + "日です。";
                TextView limit_tv7 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv7.setText(limit_str7);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
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

                                SharedPreferences year_pref7 = getSharedPreferences("year_pref7", MODE_PRIVATE);
                                SharedPreferences month_pref7 = getSharedPreferences("month_pref7", MODE_PRIVATE);
                                SharedPreferences day_pref7 = getSharedPreferences("day_pref7", MODE_PRIVATE);

                                SharedPreferences.Editor year_e7 = year_pref7.edit();
                                SharedPreferences.Editor month_e7 = month_pref7.edit();
                                SharedPreferences.Editor day_e7 = day_pref7.edit();
                                year_e7.putInt("year_key7", mYear);
                                month_e7.putInt("month_key7", mMonth);
                                day_e7.putInt("day_key7", mDay);
                                year_e7.commit();
                                month_e7.commit();
                                day_e7.commit();

                                int limit_year7 = year_pref7.getInt("year_key7", mYear);
                                int limit_month7 = month_pref7.getInt("month_key7", mMonth);
                                int limit_day7 = day_pref7.getInt("day_key7", mDay);
                                String limit_str7 = "賞味期限は" + String.valueOf(limit_year7) + "年" + String.valueOf(limit_month7 + 1) + "月" + String.valueOf(limit_day7) + "日です。";
                                TextView limit_tv7 = (TextView) viw.findViewById(R.id.textView30);
                                limit_tv7.setText(limit_str7);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog7 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog7.show();
                    }
                });
            }
        });

        //水ポップ
        ImageButton btnmizu = (ImageButton) findViewById(R.id.mizu_button);
        btnmizu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("水");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_mizu, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("mizu_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.mizutext);
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
                        EditText et = (EditText) viw.findViewById(R.id.mizutext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("mizu_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref8 = getSharedPreferences("year_pref8", MODE_PRIVATE);
                SharedPreferences month_pref8 = getSharedPreferences("month_pref8", MODE_PRIVATE);
                SharedPreferences day_pref8 = getSharedPreferences("day_pref8", MODE_PRIVATE);
                int limit_year8 = year_pref8.getInt("year_key8", mYear);
                int limit_month8 = month_pref8.getInt("month_key8", mMonth);
                int limit_day8 = day_pref8.getInt("day_key8", mDay);
                String limit_str8 = "賞味期限は" + String.valueOf(limit_year8) + "年" + String.valueOf(limit_month8 + 1) + "月" + String.valueOf(limit_day8) + "日です。";
                TextView limit_tv8 = (TextView) viw.findViewById(R.id.textView30);
                limit_tv8.setText(limit_str8);

                Button calbutton = (Button) viw.findViewById(R.id.calbutton);
                calbutton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                SharedPreferences year_pref8 = getSharedPreferences("year_pref8", MODE_PRIVATE);
                                SharedPreferences month_pref8 = getSharedPreferences("month_pref8", MODE_PRIVATE);
                                SharedPreferences day_pref8 = getSharedPreferences("day_pref8", MODE_PRIVATE);

                                SharedPreferences.Editor year_e8 = year_pref8.edit();
                                SharedPreferences.Editor month_e8 = month_pref8.edit();
                                SharedPreferences.Editor day_e8 = day_pref8.edit();
                                year_e8.putInt("year_key8", mYear);
                                month_e8.putInt("month_key8", mMonth);
                                day_e8.putInt("day_key8", mDay);
                                year_e8.commit();
                                month_e8.commit();
                                day_e8.commit();

                                int limit_year8 = year_pref8.getInt("year_key8", mYear);
                                int limit_month8 = month_pref8.getInt("month_key8", mMonth);
                                int limit_day8 = day_pref8.getInt("day_key8", mDay);
                                String limit_str8 = "賞味期限は" + String.valueOf(limit_year8) + "年" + String.valueOf(limit_month8 + 1) + "月" + String.valueOf(limit_day8) + "日です。";
                                TextView limit_tv8 = (TextView) viw.findViewById(R.id.textView30);
                                limit_tv8.setText(limit_str8);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog8 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog8.show();
                    }
                });
            }
        });

        //離乳食ポップ
        ImageButton btnrinyu = (ImageButton) findViewById(R.id.rinyubutton);
        btnrinyu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("離乳食");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_rinyu, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("rinyu_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.rinyutext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                int c = baby_pref.getInt("baby_key", 0);
                String baby_str = "幼児" + String.valueOf(c) + "人";
                TextView baby_tv = (TextView) viw.findViewById(R.id.babytext);
                baby_tv.setText(baby_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.rinyutext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("rinyu_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref9 = getSharedPreferences("year_pref9",MODE_PRIVATE);
                SharedPreferences month_pref9 = getSharedPreferences("month_pref9", MODE_PRIVATE);
                SharedPreferences day_pref9 = getSharedPreferences("day_pref9", MODE_PRIVATE);
                int limit_year9 = year_pref9.getInt("year_key9",mYear);
                int limit_month9 = month_pref9.getInt("month_key9",mMonth);
                int limit_day9 = day_pref9.getInt("day_key9",mDay);
                String limit_str9 = "賞味期限は" + String.valueOf(limit_year9) + "年" + String.valueOf(limit_month9+1) + "月" + String.valueOf(limit_day9) + "日です。";
                TextView limit_tv9 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv9.setText(limit_str9);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
                calbutton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                SharedPreferences year_pref9 = getSharedPreferences("year_pref9",MODE_PRIVATE);
                                SharedPreferences month_pref9 = getSharedPreferences("month_pref9", MODE_PRIVATE);
                                SharedPreferences day_pref9 = getSharedPreferences("day_pref9", MODE_PRIVATE);

                                SharedPreferences.Editor year_e9 = year_pref9.edit();
                                SharedPreferences.Editor month_e9 = month_pref9.edit();
                                SharedPreferences.Editor day_e9 = day_pref9.edit();
                                year_e9.putInt("year_key9", mYear);
                                month_e9.putInt("month_key9", mMonth);
                                day_e9.putInt("day_key9", mDay);
                                year_e9.commit();
                                month_e9.commit();
                                day_e9.commit();

                                int limit_year9 = year_pref9.getInt("year_key9",mYear);
                                int limit_month9 = month_pref9.getInt("month_key9",mMonth);
                                int limit_day9 = day_pref9.getInt("day_key9",mDay);
                                String limit_str9 = "賞味期限は" + String.valueOf(limit_year9) + "年" + String.valueOf(limit_month9+1) + "月" + String.valueOf(limit_day9) + "日です。";
                                TextView limit_tv9 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv9.setText(limit_str9);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog9 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog9.show();
                    }
                });
            }
        });

        //カロリポップ
        ImageButton btnkarori = (ImageButton) findViewById(R.id.karoributton);
        btnkarori.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("カロリーメイト（ロングライフ版）");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_karori, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("karori_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.karoritext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.karoritext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("karori_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref10 = getSharedPreferences("year_pref10",MODE_PRIVATE);
                SharedPreferences month_pref10 = getSharedPreferences("month_pref10", MODE_PRIVATE);
                SharedPreferences day_pref10 = getSharedPreferences("day_pref10", MODE_PRIVATE);
                int limit_year10 = year_pref10.getInt("year_key10",mYear);
                int limit_month10 = month_pref10.getInt("month_key10",mMonth);
                int limit_day10 = day_pref10.getInt("day_key10",mDay);
                String limit_str10 = "賞味期限は" + String.valueOf(limit_year10) + "年" + String.valueOf(limit_month10+1) + "月" + String.valueOf(limit_day10) + "日です。";
                TextView limit_tv10 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv10.setText(limit_str10);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
                calbutton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                SharedPreferences year_pref10 = getSharedPreferences("year_pref10",MODE_PRIVATE);
                                SharedPreferences month_pref10 = getSharedPreferences("month_pref10", MODE_PRIVATE);
                                SharedPreferences day_pref10 = getSharedPreferences("day_pref10", MODE_PRIVATE);

                                SharedPreferences.Editor year_e10 = year_pref10.edit();
                                SharedPreferences.Editor month_e10 = month_pref10.edit();
                                SharedPreferences.Editor day_e10 = day_pref10.edit();
                                year_e10.putInt("year_key10", mYear);
                                month_e10.putInt("month_key10", mMonth);
                                day_e10.putInt("day_key10", mDay);
                                year_e10.commit();
                                month_e10.commit();
                                day_e10.commit();

                                int limit_year10 = year_pref10.getInt("year_key10",mYear);
                                int limit_month10 = month_pref10.getInt("month_key10",mMonth);
                                int limit_day10 = day_pref10.getInt("day_key10",mDay);
                                String limit_str10 = "賞味期限は" + String.valueOf(limit_year10) + "年" + String.valueOf(limit_month10+1) + "月" + String.valueOf(limit_day10) + "日です。";
                                TextView limit_tv10 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv10.setText(limit_str10);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog10 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog10.show();
                    }
                });
            }
        });

        //お菓子ポップ
        ImageButton btnokasi = (ImageButton) findViewById(R.id.okasibutton);
        btnokasi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("菓子類（チョコレート、飴、ビスケット...）");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_okasi, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("okasi_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.okasitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                int b = children_pref.getInt("children_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.okasitext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("okasi_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref11 = getSharedPreferences("year_pref11",MODE_PRIVATE);
                SharedPreferences month_pref11 = getSharedPreferences("month_pref11", MODE_PRIVATE);
                SharedPreferences day_pref11 = getSharedPreferences("day_pref11", MODE_PRIVATE);
                int limit_year11 = year_pref11.getInt("year_key11",mYear);
                int limit_month11 = month_pref11.getInt("month_key11",mMonth);
                int limit_day11 = day_pref11.getInt("day_key11",mDay);
                String limit_str11 = "賞味期限は" + String.valueOf(limit_year11) + "年" + String.valueOf(limit_month11+1) + "月" + String.valueOf(limit_day11) + "日です。";
                TextView limit_tv11 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv11.setText(limit_str11);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
                calbutton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                SharedPreferences year_pref11 = getSharedPreferences("year_pref11",MODE_PRIVATE);
                                SharedPreferences month_pref11 = getSharedPreferences("month_pref11", MODE_PRIVATE);
                                SharedPreferences day_pref11 = getSharedPreferences("day_pref11", MODE_PRIVATE);

                                SharedPreferences.Editor year_e11 = year_pref11.edit();
                                SharedPreferences.Editor month_e11 = month_pref11.edit();
                                SharedPreferences.Editor day_e11 = day_pref11.edit();
                                year_e11.putInt("year_key11", mYear);
                                month_e11.putInt("month_key11", mMonth);
                                day_e11.putInt("day_key11", mDay);
                                year_e11.commit();
                                month_e11.commit();
                                day_e11.commit();

                                int limit_year11 = year_pref11.getInt("year_key11",mYear);
                                int limit_month11 = month_pref11.getInt("month_key11",mMonth);
                                int limit_day11 = day_pref11.getInt("day_key11",mDay);
                                String limit_str11 = "賞味期限は" + String.valueOf(limit_year11) + "年" + String.valueOf(limit_month11+1) + "月" + String.valueOf(limit_day11) + "日です。";
                                TextView limit_tv11 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv11.setText(limit_str11);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog11 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog11.show();
                    }
                });
            }
        });

        //粉ミルクポップ
        ImageButton btnkonamilk = (ImageButton) findViewById(R.id.konamilkbutton);
        btnkonamilk.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(hijo.this);
                alert.setTitle("粉ミルク");
                LayoutInflater inflater = LayoutInflater.from(hijo.this);
                final View viw = inflater.inflate(R.layout.activity_konamilk, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("konamilk_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.konamilktext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                int c = baby_pref.getInt("baby_key", 0);
                String baby_str = "幼児" + String.valueOf(c) + "人";
                TextView baby_tv = (TextView) viw.findViewById(R.id.babytext);
                baby_tv.setText(baby_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.konamilktext);
                        String str = et.getText().toString();
                        if (str.length() <= 0) { // 値が空白値の場合0を入れる処理
                            str = "0";
                        }
                        int i = Integer.parseInt(str);
                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("konamilk_num", i);
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
                        lastday_e.putInt("lastday_key",mDay);

                        lastyear_e.commit();
                        lastmonth_e.commit();
                        lastday_e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                //カレンダー設定
                SharedPreferences year_pref12 = getSharedPreferences("year_pref12",MODE_PRIVATE);
                SharedPreferences month_pref12 = getSharedPreferences("month_pref12", MODE_PRIVATE);
                SharedPreferences day_pref12 = getSharedPreferences("day_pref12", MODE_PRIVATE);
                int limit_year12 = year_pref12.getInt("year_key12",mYear);
                int limit_month12 = month_pref12.getInt("month_key12",mMonth);
                int limit_day12 = day_pref12.getInt("day_key12",mDay);
                String limit_str12 = "賞味期限は" + String.valueOf(limit_year12) + "年" + String.valueOf(limit_month12+1) + "月" + String.valueOf(limit_day12) + "日です。";
                TextView limit_tv12 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv12.setText(limit_str12);

                Button calbutton = (Button)viw.findViewById(R.id.calbutton);
                calbutton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //DatePickerDialog の日付が変更されたときに呼び出されるコールバックを登録
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mYear = year;
                                mMonth = monthOfYear;
                                mDay = dayOfMonth;

                                SharedPreferences year_pref12 = getSharedPreferences("year_pref12",MODE_PRIVATE);
                                SharedPreferences month_pref12 = getSharedPreferences("month_pref12", MODE_PRIVATE);
                                SharedPreferences day_pref12 = getSharedPreferences("day_pref12", MODE_PRIVATE);

                                SharedPreferences.Editor year_e12 = year_pref12.edit();
                                SharedPreferences.Editor month_e12 = month_pref12.edit();
                                SharedPreferences.Editor day_e12 = day_pref12.edit();
                                year_e12.putInt("year_key12", mYear);
                                month_e12.putInt("month_key12", mMonth);
                                day_e12.putInt("day_key12", mDay);
                                year_e12.commit();
                                month_e12.commit();
                                day_e12.commit();

                                int limit_year12 = year_pref12.getInt("year_key12",mYear);
                                int limit_month12 = month_pref12.getInt("month_key12",mMonth);
                                int limit_day12 = day_pref12.getInt("day_key12",mDay);
                                String limit_str12 = "賞味期限は" + String.valueOf(limit_year12) + "年" + String.valueOf(limit_month12+1) + "月" + String.valueOf(limit_day12) + "日です。";
                                TextView limit_tv12 = (TextView)viw.findViewById(R.id.textView30);
                                limit_tv12.setText(limit_str12);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog12 = new DatePickerDialog(hijo.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog12.show();
                    }
                });
            }
        });

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定（レトルトご飯)
        SharedPreferences limit_pref = getSharedPreferences("limit_pref", MODE_PRIVATE); // 期日のプリファレンス
        SharedPreferences year_pref1 = getSharedPreferences("year_pref1", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref1 = getSharedPreferences("month_pref1", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref1 = getSharedPreferences("day_pref1", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        int limit = limit_pref.getInt("limit_key", 0); // 期日
        String lyearstr1 = String.valueOf(year_pref1.getInt("year_key1", 0)); // 賞味期限（年）
        String lmonthstr1 = String.valueOf(month_pref1.getInt("month_key1", 0)); // 賞味期限（月）
        String ldaystr1 = String.valueOf(day_pref1.getInt("day_key1", 0)); // 賞味期限（日）
        String toyearstr = String.valueOf(mYear); // 現在の年
        String tomonthstr = String.valueOf(mMonth); // 現在の月
        String todaystr = String.valueOf(mDay); // 現在の日
        double sa1 = 0.0;

        try{
            sa1  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr1 + "/" + lmonthstr1 + "/" + ldaystr1);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa1 < 0.0 ) {
            ImageButton retorutog = (ImageButton) findViewById(R.id.ImageButton);
            retorutog.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 （缶詰（ご飯））
        SharedPreferences year_pref2 = getSharedPreferences("year_pref2", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref2 = getSharedPreferences("month_pref2", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref2 = getSharedPreferences("day_pref2", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr2 = String.valueOf(year_pref2.getInt("year_key2", 0)); // 賞味期限（年）
        String lmonthstr2 = String.valueOf(month_pref2.getInt("month_key2", 0)); // 賞味期限（月）
        String ldaystr2 = String.valueOf(day_pref2.getInt("day_key2", 0)); // 賞味期限（日）
        double sa2 = 0.0;

        try{
            sa2  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr2 + "/" + lmonthstr2 + "/" + ldaystr2);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa2 < 0.0 ) {
            ImageButton kandume = (ImageButton) findViewById(R.id.kandumebutton);
            kandume.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 （乾麺）
        SharedPreferences year_pref3 = getSharedPreferences("year_pref3", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref3 = getSharedPreferences("month_pref3", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref3 = getSharedPreferences("day_pref3", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr3 = String.valueOf(year_pref3.getInt("year_key3", 0)); // 賞味期限（年）
        String lmonthstr3 = String.valueOf(month_pref3.getInt("month_key3", 0)); // 賞味期限（月）
        String ldaystr3 = String.valueOf(day_pref3.getInt("day_key3", 0)); // 賞味期限（日）
        double sa3 = 0.0;

        try{
            sa3  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr3 + "/" + lmonthstr3 + "/" + ldaystr3);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa3 < 0.0 ) {
            ImageButton kanmen = (ImageButton) findViewById(R.id.kanmenbutton);
            kanmen.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 乾パン
        SharedPreferences year_pref4 = getSharedPreferences("year_pref4", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref4 = getSharedPreferences("month_pref4", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref4 = getSharedPreferences("day_pref4", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr4 = String.valueOf(year_pref4.getInt("year_key4", 0)); // 賞味期限（年）
        String lmonthstr4 = String.valueOf(month_pref4.getInt("month_key4", 0)); // 賞味期限（月）
        String ldaystr4 = String.valueOf(day_pref4.getInt("day_key4", 0)); // 賞味期限（日）
        double sa4 = 0.0;

        try{
            sa4  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr4 + "/" + lmonthstr4 + "/" + ldaystr4);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa4 < 0.0 ) {
            ImageButton kanpan = (ImageButton) findViewById(R.id.kanpanbutton);
            kanpan.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 缶詰（肉・魚）
        SharedPreferences year_pref5 = getSharedPreferences("year_pref5", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref5 = getSharedPreferences("month_pref5", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref5 = getSharedPreferences("day_pref5", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr5 = String.valueOf(year_pref5.getInt("year_key5", 0)); // 賞味期限（年）
        String lmonthstr5 = String.valueOf(month_pref5.getInt("month_key5", 0)); // 賞味期限（月）
        String ldaystr5 = String.valueOf(day_pref5.getInt("day_key5", 0)); // 賞味期限（日）
        double sa5 = 0.0;

        try{
            sa5  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr5 + "/" + lmonthstr5 + "/" + ldaystr5);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa5 < 0.0 ) {
            ImageButton kandume2 = (ImageButton) findViewById(R.id.kandumebutton2);
            kandume2.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 レトルト
        SharedPreferences year_pref6 = getSharedPreferences("year_pref6", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref6 = getSharedPreferences("month_pref6", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref6 = getSharedPreferences("day_pref6", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr6 = String.valueOf(year_pref6.getInt("year_key6", 0)); // 賞味期限（年）
        String lmonthstr6 = String.valueOf(month_pref6.getInt("month_key6", 0)); // 賞味期限（月）
        String ldaystr6 = String.valueOf(day_pref6.getInt("day_key6", 0)); // 賞味期限（日）
        double sa6 = 0.0;

        try{
            sa6  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr6 + "/" + lmonthstr6 + "/" + ldaystr6);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa6 < 0.0 ) {
            ImageButton retoruto = (ImageButton) findViewById(R.id.retorutobutton);
            retoruto.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 フリーズドライ
        SharedPreferences year_pref7 = getSharedPreferences("year_pref7", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref7 = getSharedPreferences("month_pref7", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref7 = getSharedPreferences("day_pref7", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr7 = String.valueOf(year_pref7.getInt("year_key7", 0)); // 賞味期限（年）
        String lmonthstr7 = String.valueOf(month_pref7.getInt("month_key7", 0)); // 賞味期限（月）
        String ldaystr7 = String.valueOf(day_pref7.getInt("day_key7", 0)); // 賞味期限（日）
        double sa7 = 0.0;

        try{
            sa7  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr7 + "/" + lmonthstr7 + "/" + ldaystr7);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa7 < 0.0 ) {
            ImageButton freaze = (ImageButton) findViewById(R.id.freazedry_button);
            freaze.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 水
        SharedPreferences year_pref8 = getSharedPreferences("year_pref8", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref8 = getSharedPreferences("month_pref8", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref8 = getSharedPreferences("day_pref8", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr8 = String.valueOf(year_pref8.getInt("year_key8", 0)); // 賞味期限（年）
        String lmonthstr8 = String.valueOf(month_pref8.getInt("month_key8", 0)); // 賞味期限（月）
        String ldaystr8 = String.valueOf(day_pref8.getInt("day_key8", 0)); // 賞味期限（日）
        double sa8 = 0.0;

        try{
            sa8  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr8 + "/" + lmonthstr8 + "/" + ldaystr8);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa8 < 0.0 ) {
            ImageButton mizu = (ImageButton) findViewById(R.id.mizu_button);
            mizu.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 離乳食
        SharedPreferences year_pref9 = getSharedPreferences("year_pref9", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref9 = getSharedPreferences("month_pref9", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref9 = getSharedPreferences("day_pref9", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr9 = String.valueOf(year_pref9.getInt("year_key9", 0)); // 賞味期限（年）
        String lmonthstr9 = String.valueOf(month_pref9.getInt("month_key9", 0)); // 賞味期限（月）
        String ldaystr9 = String.valueOf(day_pref9.getInt("day_key9", 0)); // 賞味期限（日）
        double sa9 = 0.0;

        try{
            sa9  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr9 + "/" + lmonthstr9 + "/" + ldaystr9);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa9 < 0.0 ) {
            ImageButton rinyu = (ImageButton) findViewById(R.id.rinyubutton);
            rinyu.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 カロリーメイト
        SharedPreferences year_pref10 = getSharedPreferences("year_pref10", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref10 = getSharedPreferences("month_pref10", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref10 = getSharedPreferences("day_pref10", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr10 = String.valueOf(year_pref10.getInt("year_key10", 0)); // 賞味期限（年）
        String lmonthstr10 = String.valueOf(month_pref10.getInt("month_key10", 0)); // 賞味期限（月）
        String ldaystr10 = String.valueOf(day_pref10.getInt("day_key10", 0)); // 賞味期限（日）
        double sa10 = 0.0;

        try{
            sa10  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr10 + "/" + lmonthstr10 + "/" + ldaystr10);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa10 < 0.0 ) {
            ImageButton karori = (ImageButton) findViewById(R.id.karoributton);
            karori.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 お菓子
        SharedPreferences year_pref11 = getSharedPreferences("year_pref11", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref11 = getSharedPreferences("month_pref11", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref11 = getSharedPreferences("day_pref11", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr11 = String.valueOf(year_pref11.getInt("year_key11", 0)); // 賞味期限（年）
        String lmonthstr11 = String.valueOf(month_pref11.getInt("month_key11", 0)); // 賞味期限（月）
        String ldaystr11 = String.valueOf(day_pref11.getInt("day_key11", 0)); // 賞味期限（日）
        double sa11 = 0.0;

        try{
            sa11  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr11 + "/" + lmonthstr11 + "/" + ldaystr11);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa11 < 0.0 ) {
            ImageButton okasi = (ImageButton) findViewById(R.id.okasibutton);
            okasi.setBackgroundColor(Color.RED);
        }

        // 賞味期限切れ赤淵設定
        // 要チェック欄、賞味期限設定 粉ミルク
        SharedPreferences year_pref12 = getSharedPreferences("year_pref12", MODE_PRIVATE); // 賞味期限の年のプリファレンス
        SharedPreferences month_pref12 = getSharedPreferences("month_pref12", MODE_PRIVATE); // 賞味期限の月のプリファレンス
        SharedPreferences day_pref12 = getSharedPreferences("day_pref12", MODE_PRIVATE); // 賞味期限の日のプリファレンス
        String lyearstr12 = String.valueOf(year_pref12.getInt("year_key12", 0)); // 賞味期限（年）
        String lmonthstr12 = String.valueOf(month_pref12.getInt("month_key12", 0)); // 賞味期限（月）
        String ldaystr12 = String.valueOf(day_pref12.getInt("day_key12", 0)); // 賞味期限（日）
        double sa12 = 0.0;

        try{
            sa12  = differenceDays(toyearstr + "/" + tomonthstr + "/" + todaystr, lyearstr12 + "/" + lmonthstr12 + "/" + ldaystr12);
        } catch (ParseException e) {
            System.out.println(e);
        }

        if ( sa12 < 0.0 ) {
            ImageButton konamilk = (ImageButton) findViewById(R.id.konamilkbutton);
            konamilk.setBackgroundColor(Color.RED);
        }


        //ボタン場所指定(ホーム)
        ImageButton btnhome = (ImageButton)findViewById(R.id.HomeButton1);

        btnhome.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.MainActivity");
                startActivity(intent);//画面を出す
            }
        });

        //ボタン場所指定(備蓄品)
        ImageButton btnbichiku = (ImageButton)findViewById(R.id.BichikuButton1);

        btnbichiku.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.bichiku");
                startActivity(intent);//画面を出す
            }
        });

        //ボタン場所指定(設定)
        ImageButton btnsetting = (ImageButton)findViewById(R.id.SettingButton1);

        btnsetting.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.setting");
                startActivity(intent);//画面を出す
            }
        });
    }

    private double differenceDays(String strDate1, String strDate2)
            throws ParseException {
        double ret = 0.0;

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
        getMenuInflater().inflate(R.menu.menu_hijo, menu);
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