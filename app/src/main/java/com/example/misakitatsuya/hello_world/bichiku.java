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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class bichiku extends Activity {

    final Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(calendar.YEAR);
    int mMonth = calendar.get(calendar.MONTH);
    int mDay = calendar.get(calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bichiku);

        // 小人下着ポップ
        ImageButton btnkodomo = (ImageButton)findViewById(R.id.kodomobutton);
        btnkodomo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("小人衣類（下着）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_kodomo, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("kodomo_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kodomotext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int b = children_pref.getInt("children_key", 0);
                String children_str = "小人" + String.valueOf(b) + "人";
                TextView children_tv = (TextView) viw.findViewById(R.id.childrentext);
                children_tv.setText(children_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kodomotext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("kodomo_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 懐中電灯ポップ
        ImageButton btnraito = (ImageButton)findViewById(R.id.raitobutton);
        btnraito.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("懐中電灯（乾電池:単3）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_raito, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("raito_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.raitotext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.raitotext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("raito_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // コップポップ
        ImageButton btnkoppu = (ImageButton)findViewById(R.id.koppubutton);
        btnkoppu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("コップ（プラスチック）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_koppu, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("koppu_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kopputext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kopputext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("koppu_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 器ポップ
        ImageButton btnutuwa = (ImageButton)findViewById(R.id.utuwabutton);
        btnutuwa.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("紙皿");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_utuwa, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("utuwa_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.utuwatext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.utuwatext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("utuwa_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // タオルポップ
        ImageButton btntaoru = (ImageButton)findViewById(R.id.taorubutton);
        btntaoru.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("タオル");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_taoru, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("taoru_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.taorutext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.taorutext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("taoru_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // ラップポップ
        ImageButton btnrappu = (ImageButton)findViewById(R.id.rappubutton);
        btnrappu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ラップ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_rappu, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("rappu_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.rapputext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.rapputext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("rappu_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 袋ポップ
        ImageButton btnhukuro = (ImageButton)findViewById(R.id.hukurobutton);
        btnhukuro.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("袋");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_hukuro, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("hukuro_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.hukurotext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.hukurotext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("hukuro_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // スプーンポップ
        ImageButton btnspoon = (ImageButton)findViewById(R.id.spoonbutton);
        btnspoon.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("スプーン");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_spoon, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("spoon_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.spoontext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.spoontext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("spoon_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 箸ポップ
        ImageButton btnhasi = (ImageButton)findViewById(R.id.hasibutton);
        btnhasi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("割り箸");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_hasi, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("hasi_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.hasitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.hasitext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("hasi_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // ラジオポップ
        ImageButton btnradio = (ImageButton)findViewById(R.id.radiobutton);
        btnradio.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ラジオ（乾電池:単3）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_radio, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("radio_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.radiotext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.radiotext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("radio_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 缶切りポップ
        ImageButton btnkankiri = (ImageButton)findViewById(R.id.kankiributton);
        btnkankiri.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("缶切り");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_kankiri, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("kankiri_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.kankiritext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.kankiritext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("kankiri_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // マスクポップ
        ImageButton btnmasuku = (ImageButton)findViewById(R.id.masukubutton);
        btnmasuku.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("マスク");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_masuku, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("masuku_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.masukutext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.masukutext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("masuku_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 携帯充電器ポップ
        ImageButton btnzyuden = (ImageButton)findViewById(R.id.zyudenbutton);
        btnzyuden.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("携帯電話充電器（乾電池:単3）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_zyuden, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("zyuden_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.zyudentext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.zyudentext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("zyuden_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 寝袋ポップ
        ImageButton btnnebukuro = (ImageButton)findViewById(R.id.nebukurobutton);
        btnnebukuro.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("寝袋");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_nebukuro, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("nebukuro_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.nebukurotext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.nebukurotext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("nebukuro_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // 哺乳瓶ポップ
        ImageButton btnbin = (ImageButton)findViewById(R.id.binbutton);
        btnbin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("哺乳びん");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_bin, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("bin_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.bintext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.bintext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("bin_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // おむつポップ
        ImageButton btnomutu = (ImageButton)findViewById(R.id.omutubutton);
        btnomutu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("おむつ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_omutu, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("omutu_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.omututext);
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
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.omututext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("omutu_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // ガスコンロポップ
        ImageButton btngas = (ImageButton)findViewById(R.id.gasbutton);
        btngas.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ガスコンロ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_gas, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("gas_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.gastext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.gastext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("gas_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        // マッチ・ライターポップ
        ImageButton btnmatti = (ImageButton)findViewById(R.id.mattibutton);
        btnmatti.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("マッチ・ライター");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_matti, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("matti_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.mattitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                                EditText et = (EditText)viw.findViewById(R.id.mattitext);
                                String str = et.getText().toString();
                                int i = Integer.parseInt(str);

                                SharedPreferences.Editor e = bichikupref.edit();
                                e.putInt("matti_num",i);
                                e.commit();

                                //最終入力日の日付記録
                                SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                                SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                                SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                                SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                                SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                                SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                                lastyear_eb.putInt("lastyear_keyb", mYear);
                                lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                                lastday_eb.putInt("lastday_keyb",mDay);

                                lastyear_eb.commit();
                                lastmonth_eb.commit();
                                lastday_eb.commit();
                            }
                        });
                alert.setView(viw);
                alert.show();
            }
        });

        // ガスボンベポップ
        ImageButton btngasbomb = (ImageButton)findViewById(R.id.gasbombbutton);
        btngasbomb.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ガスボンベ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_gasbomb, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("gasbomb_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.gasbombtext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.gasbombtext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("gasbomb_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();

                // 消費期限カレンダー設定
                SharedPreferences year_pref13 = getSharedPreferences("year_pref13", MODE_PRIVATE);
                SharedPreferences month_pref13 = getSharedPreferences("month_pref13", MODE_PRIVATE);
                SharedPreferences day_pref13 = getSharedPreferences("day_pref13", MODE_PRIVATE);
                int limit_year13 = year_pref13.getInt("year_key13", mYear);
                int limit_month13 = month_pref13.getInt("month_key130", mMonth);
                int limit_day13 = day_pref13.getInt("day_key13", mDay);
                String limit_str13 = "消費期限は" + String.valueOf(limit_year13) + "年" + String.valueOf(limit_month13 + 1) + "月" + String.valueOf(limit_day13) + "日です。";
                TextView limit_tv13 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv13.setText(limit_str13);

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

                                SharedPreferences year_pref13 = getSharedPreferences("year_pref13", MODE_PRIVATE);
                                SharedPreferences month_pref13 = getSharedPreferences("month_pref13", MODE_PRIVATE);
                                SharedPreferences day_pref13 = getSharedPreferences("day_pref13", MODE_PRIVATE);

                                SharedPreferences.Editor year_e13 = year_pref13.edit();
                                SharedPreferences.Editor month_e13 = month_pref13.edit();
                                SharedPreferences.Editor day_e13 = day_pref13.edit();
                                year_e13.putInt("year_key13", mYear);
                                month_e13.putInt("month_key13", mMonth);
                                day_e13.putInt("day_key13", mDay);
                                year_e13.commit();
                                month_e13.commit();
                                day_e13.commit();

                                int limit_year13 = year_pref13.getInt("year_key13", mYear);
                                int limit_month13 = month_pref13.getInt("month_key13", mMonth);
                                int limit_day13 = day_pref13.getInt("day_key13", mDay);
                                String limit_str13 = "賞味期限は" + String.valueOf(limit_year13) + "年" + String.valueOf(limit_month13 + 1) + "月" + String.valueOf(limit_day13) + "日です。";
                                TextView limit_tv13 = (TextView) viw.findViewById(R.id.textView30);
                                limit_tv13.setText(limit_str13);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog13 = new DatePickerDialog(bichiku.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog13.show();
                    }
                });
            }
        });

        // 乾電池ポップ
        ImageButton btndenti = (ImageButton)findViewById(R.id.dentibutton);
        btndenti.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("乾電池（単3）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_denti, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("denti_num", i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText) viw.findViewById(R.id.dentitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key", 0);
                String adult_str = "大人" + String.valueOf(a) + "人";
                TextView adult_tv = (TextView) viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.dentitext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("denti_num", i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb", mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });
                alert.setView(viw);
                alert.show();

                // 消費期限カレンダー設定
                SharedPreferences year_pref14 = getSharedPreferences("year_pref14", MODE_PRIVATE);
                SharedPreferences month_pref14 = getSharedPreferences("month_pref14", MODE_PRIVATE);
                SharedPreferences day_pref14 = getSharedPreferences("day_pref14", MODE_PRIVATE);
                int limit_year14 = year_pref14.getInt("year_key14", mYear);
                int limit_month14 = month_pref14.getInt("month_key14", mMonth);
                int limit_day14 = day_pref14.getInt("day_key14", mDay);
                String limit_str14 = "消費期限は" + String.valueOf(limit_year14) + "年" + String.valueOf(limit_month14 + 1) + "月" + String.valueOf(limit_day14) + "日です。";
                TextView limit_tv14 = (TextView)viw.findViewById(R.id.textView30);
                limit_tv14.setText(limit_str14);

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

                                SharedPreferences year_pref14 = getSharedPreferences("year_pref14", MODE_PRIVATE);
                                SharedPreferences month_pref14 = getSharedPreferences("month_pref14", MODE_PRIVATE);
                                SharedPreferences day_pref14 = getSharedPreferences("day_pref14", MODE_PRIVATE);

                                SharedPreferences.Editor year_e14 = year_pref14.edit();
                                SharedPreferences.Editor month_e14 = month_pref14.edit();
                                SharedPreferences.Editor day_e14 = day_pref14.edit();
                                year_e14.putInt("year_key14", mYear);
                                month_e14.putInt("month_key14", mMonth);
                                day_e14.putInt("day_key14", mDay);
                                year_e14.commit();
                                month_e14.commit();
                                day_e14.commit();

                                int limit_year14 = year_pref14.getInt("year_key14", mYear);
                                int limit_month14 = month_pref14.getInt("month_key14", mMonth);
                                int limit_day14 = day_pref14.getInt("day_key14", mDay);
                                String limit_str14 = "賞味期限は" + String.valueOf(limit_year14) + "年" + String.valueOf(limit_month14 + 1) + "月" + String.valueOf(limit_day14) + "日です。";
                                TextView limit_tv14 = (TextView) viw.findViewById(R.id.textView30);
                                limit_tv14.setText(limit_str14);
                            }
                        };
                        // DatePickerDialog の作成
                        DatePickerDialog datePickerDialog14 = new DatePickerDialog(bichiku.this, listener, mYear, mMonth, mDay);

                        //DatePickerDialog の表示
                        datePickerDialog14.show();
                    }
                });
            }
        });

        // アルミホイルポップ
        ImageButton btnalmi = (ImageButton)findViewById(R.id.almibutton);
        btnalmi.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("アルミホイル");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_almi, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("almi_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.almitext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.almitext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("almi_num",i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb",mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        // 軍手ポップ
        ImageButton btngunte = (ImageButton)findViewById(R.id.guntebutton);
        btngunte.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("軍手");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_gunte, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("gunte_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.guntetext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.guntetext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("gunte_num",i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb",mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        // 笛ポップ
        ImageButton btnhue = (ImageButton)findViewById(R.id.huebutton);
        btnhue.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("笛（防犯ブザー）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_hue, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("hue_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.huetext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.huetext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("hue_num",i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb",mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        // 大人下着ポップ
        ImageButton btnotona = (ImageButton)findViewById(R.id.otonabutton);
        btnotona.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("大人衣類（下着）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_otona, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("otona_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.otonatext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.otonatext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("otona_num",i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb",mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        // ティッシュポップ
        ImageButton btnthissyu = (ImageButton)findViewById(R.id.thissyubutton);
        btnthissyu.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ティッシュ・ウェットティッシュ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_thissyu, null);
                // プリファレンスの生成
                SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = bichikupref.getInt("thissyu_num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.thissyutext);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                adult_tv.setText(adult_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences bichikupref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.thissyutext);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = bichikupref.edit();
                        e.putInt("thissyu_num",i);
                        e.commit();

                        //最終入力日の日付記録
                        SharedPreferences lastyearprefb = getSharedPreferences("lastyearprefb", MODE_PRIVATE);
                        SharedPreferences lastmonthprefb = getSharedPreferences("lastmonthprefb", MODE_PRIVATE);
                        SharedPreferences lastdayprefb = getSharedPreferences("lastdayprefb", MODE_PRIVATE);

                        SharedPreferences.Editor lastyear_eb = lastyearprefb.edit();
                        SharedPreferences.Editor lastmonth_eb = lastmonthprefb.edit();
                        SharedPreferences.Editor lastday_eb = lastdayprefb.edit();

                        lastyear_eb.putInt("lastyear_keyb", mYear);
                        lastmonth_eb.putInt("lastmonth_keyb", mMonth);
                        lastday_eb.putInt("lastday_keyb",mDay);

                        lastyear_eb.commit();
                        lastmonth_eb.commit();
                        lastday_eb.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        // ボタン場所指定(ホーム)
        ImageButton btnhome = (ImageButton)findViewById(R.id.HomeButton2);
        btnhome.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.MainActivity");
                startActivity(intent);
            }
        });

        // ボタン場所指定(非常食)
        ImageButton btnhijo = (ImageButton)findViewById(R.id.HijoButton2);
        btnhijo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.hijo");
                startActivity(intent);
            }
        });

        // ボタン場所指定(設定)
        ImageButton btnsetting = (ImageButton)findViewById(R.id.SettingButton2);
        btnsetting.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.setting");
                startActivity(intent);
            }
        });

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bichiku, menu);
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
