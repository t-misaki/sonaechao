package com.example.misakitatsuya.hello_world;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Spinner;


public class setting extends Activity {

    protected void onStart() {
        super.onStart();
        //大人、小人、幼児の人数を呼び出す
        SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
        SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
        SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
        int a = adult_pref.getInt("adult_key", 0);
        int b = children_pref.getInt("children_key", 0);
        int c = baby_pref.getInt("baby_key", 0);
        ((Spinner)findViewById(R.id.adultspinner)).setSelection(a);
        ((Spinner)findViewById(R.id.childrenspinner)).setSelection(b);
        ((Spinner)findViewById(R.id.babyspinner)).setSelection(c);

        //期日の値を呼び出す
        SharedPreferences limit_pref = getSharedPreferences("limit_pref", MODE_PRIVATE);
        int d = limit_pref.getInt("limit_key", 0);
        int e=d;
        switch(e) {
            case 14:
                e = 0;
                break;
            case 30:
                e = 1;
                break;
            case 60:
                e = 2;
                break;
            default:
                break;
        }
        ((Spinner)findViewById(R.id.limitspinner)).setSelection(e);

        //設定日数の値を呼び出す
        SharedPreferences setting_pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
        int f = setting_pref.getInt("setting_key", 0);
        int g=f;
        switch(g) {
            case 1:
                g = 0;
                break;
            case 3:
                g = 1;
                break;
            case 7:
                g = 2;
                break;
            default:
                break;
        }
        ((Spinner)findViewById(R.id.settingspinner)).setSelection(g);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ImageButton btnhome = (ImageButton)findViewById(R.id.HomeButton3);
        btnhome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ホーム画面移動
                //大人、小人、幼児の人数を保存
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                Spinner adult_sp = (Spinner)findViewById(R.id.adultspinner);
                Spinner children_sp = (Spinner)findViewById(R.id.childrenspinner);
                Spinner baby_sp = (Spinner)findViewById(R.id.babyspinner);
                String adult_str = (String)adult_sp.getSelectedItem();
                String children_str = (String)children_sp.getSelectedItem();
                String baby_str = (String)baby_sp.getSelectedItem();
                int a = Integer.parseInt(adult_str);
                int b = Integer.parseInt(children_str);
                int c = Integer.parseInt(baby_str);
                SharedPreferences.Editor adult_e = adult_pref.edit();
                SharedPreferences.Editor children_e = children_pref.edit();
                SharedPreferences.Editor baby_e = baby_pref.edit();
                adult_e.putInt("adult_key", a);
                children_e.putInt("children_key", b);
                baby_e.putInt("baby_key", c);
                adult_e.commit();
                children_e.commit();
                baby_e.commit();

                //期日の値保存
                SharedPreferences limit_pref = getSharedPreferences("limit_pref",MODE_PRIVATE);
                Spinner limit_sp = (Spinner)findViewById(R.id.limitspinner);
                String limit_str = (String)limit_sp.getSelectedItem();
                int d = Integer.parseInt(limit_str);
                SharedPreferences.Editor limit_e = limit_pref.edit();
                limit_e.putInt("limit_key", d);
                limit_e.commit();

                //設定日数の値の保存
                SharedPreferences setting_pref = getSharedPreferences("setting_pref",MODE_PRIVATE);
                Spinner setting_sp = (Spinner)findViewById(R.id.settingspinner);
                String setting_str = (String)setting_sp.getSelectedItem();
                int e = Integer.parseInt(setting_str);
                SharedPreferences.Editor setting_e = setting_pref.edit();
                setting_e.putInt("setting_key", e);
                setting_e.commit();

                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.MainActivity");
                startActivity(intent);
            }
        });


        ImageButton btnhijo = (ImageButton)findViewById(R.id.HijoButton3);
        btnhijo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ホーム画面移動
                //大人、小人、幼児の人数を保存
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                Spinner adult_sp = (Spinner) findViewById(R.id.adultspinner);
                Spinner children_sp = (Spinner) findViewById(R.id.childrenspinner);
                Spinner baby_sp = (Spinner) findViewById(R.id.babyspinner);
                String adult_str = (String) adult_sp.getSelectedItem();
                String children_str = (String) children_sp.getSelectedItem();
                String baby_str = (String) baby_sp.getSelectedItem();
                int a = Integer.parseInt(adult_str);
                int b = Integer.parseInt(children_str);
                int c = Integer.parseInt(baby_str);
                SharedPreferences.Editor adult_e = adult_pref.edit();
                SharedPreferences.Editor children_e = children_pref.edit();
                SharedPreferences.Editor baby_e = baby_pref.edit();
                adult_e.putInt("adult_key", a);
                children_e.putInt("children_key", b);
                baby_e.putInt("baby_key", c);
                adult_e.commit();
                children_e.commit();
                baby_e.commit();

                //期日の値保存
                SharedPreferences limit_pref = getSharedPreferences("limit_pref", MODE_PRIVATE);
                Spinner limit_sp = (Spinner) findViewById(R.id.limitspinner);
                String limit_str = (String) limit_sp.getSelectedItem();
                int d = Integer.parseInt(limit_str);
                SharedPreferences.Editor limit_e = limit_pref.edit();
                limit_e.putInt("limit_key", d);
                limit_e.commit();

                //設定日数の値の保存
                SharedPreferences setting_pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
                Spinner setting_sp = (Spinner) findViewById(R.id.settingspinner);
                String setting_str = (String) setting_sp.getSelectedItem();
                int e = Integer.parseInt(setting_str);
                SharedPreferences.Editor setting_e = setting_pref.edit();
                setting_e.putInt("setting_key", e);
                setting_e.commit();

                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.hijo");
                startActivity(intent);
            }
        });

        ImageButton btnbichiku = (ImageButton)findViewById(R.id.BichikuButton3);
        btnbichiku.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //ホーム画面移動
                //大人、小人、幼児の人数を保存
                SharedPreferences adult_pref = getSharedPreferences("adult_pref", MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref", MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref", MODE_PRIVATE);
                Spinner adult_sp = (Spinner) findViewById(R.id.adultspinner);
                Spinner children_sp = (Spinner) findViewById(R.id.childrenspinner);
                Spinner baby_sp = (Spinner) findViewById(R.id.babyspinner);
                String adult_str = (String) adult_sp.getSelectedItem();
                String children_str = (String) children_sp.getSelectedItem();
                String baby_str = (String) baby_sp.getSelectedItem();
                int a = Integer.parseInt(adult_str);
                int b = Integer.parseInt(children_str);
                int c = Integer.parseInt(baby_str);
                SharedPreferences.Editor adult_e = adult_pref.edit();
                SharedPreferences.Editor children_e = children_pref.edit();
                SharedPreferences.Editor baby_e = baby_pref.edit();
                adult_e.putInt("adult_key", a);
                children_e.putInt("children_key", b);
                baby_e.putInt("baby_key", c);
                adult_e.commit();
                children_e.commit();
                baby_e.commit();

                //期日の値保存
                SharedPreferences limit_pref = getSharedPreferences("limit_pref", MODE_PRIVATE);
                Spinner limit_sp = (Spinner) findViewById(R.id.limitspinner);
                String limit_str = (String) limit_sp.getSelectedItem();
                int d = Integer.parseInt(limit_str);
                SharedPreferences.Editor limit_e = limit_pref.edit();
                limit_e.putInt("limit_key", d);
                limit_e.commit();

                //設定日数の値の保存
                SharedPreferences setting_pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
                Spinner setting_sp = (Spinner) findViewById(R.id.settingspinner);
                String setting_str = (String) setting_sp.getSelectedItem();
                int e = Integer.parseInt(setting_str);
                SharedPreferences.Editor setting_e = setting_pref.edit();
                setting_e.putInt("setting_key", e);
                setting_e.commit();

                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.bichiku");
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
