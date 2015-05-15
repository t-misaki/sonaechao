package com.example.misakitatsuya.hello_world;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class bichiku extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bichiku);

        //ポップアップ(1)
        ImageButton btnpop1 = (ImageButton)findViewById(R.id.pop1);
        btnpop1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ガスボンベ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup1, null);
               // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop1num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押された時の処理
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences", MODE_PRIVATE);
                        EditText et = (EditText) viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop1num", i);
                        e.commit();
                    }
                });
                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(2)
        ImageButton btnpop2 = (ImageButton)findViewById(R.id.pop2);
        btnpop2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //アラートダイアログの出力
                AlertDialog.Builder alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("マッチ・ライター");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup2, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop2num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                                EditText et = (EditText)viw.findViewById(R.id.textView18);
                                String str = et.getText().toString();
                                int i = Integer.parseInt(str);

                                SharedPreferences.Editor e = pref.edit();
                                e.putInt("pop2num",i);
                                e.commit();
                            }
                        });
                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(3)
        ImageButton btnpop3 = (ImageButton)findViewById(R.id.pop3);
        btnpop3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ガスボンベ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup3, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop3num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop3num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(4)
        ImageButton btnpop4 = (ImageButton)findViewById(R.id.pop4);
        btnpop4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ラップ・アルミホイル");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup4, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop4num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop4num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(5)
        ImageButton btnpop5 = (ImageButton)findViewById(R.id.pop5);
        btnpop5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("軍手");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup5, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop5num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop5num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(6)
        ImageButton btnpop6 = (ImageButton)findViewById(R.id.pop6);
        btnpop6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("笛（防犯ブザー）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup6, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop6num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop6num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(7)
        ImageButton btnpop7 = (ImageButton)findViewById(R.id.pop7);
        btnpop7.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("衣類（下着）");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup7, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop7num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop7num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ポップアップ(8)
        ImageButton btnpop8 = (ImageButton)findViewById(R.id.pop8);
        btnpop8.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // アラートダイアログの出力
                AlertDialog.Builder alert;
                alert = new AlertDialog.Builder(bichiku.this);
                alert.setTitle("ティッシュ");
                LayoutInflater inflater = LayoutInflater.from(bichiku.this);
                final View viw = inflater.inflate(R.layout.activity_popup8, null);
                // プリファレンスの生成
                SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                int i = 0;
                i = pref.getInt("pop8num",i);
                String str = String.valueOf(i);
                // 必ずView変数で生成したデータを使うこと
                EditText et = (EditText)viw.findViewById(R.id.textView18);
                // 値をいれる
                et.setText(str);

                //人数の表示
                SharedPreferences adult_pref = getSharedPreferences("adult_pref",MODE_PRIVATE);
                SharedPreferences children_pref = getSharedPreferences("children_pref",MODE_PRIVATE);
                SharedPreferences baby_pref = getSharedPreferences("baby_pref",MODE_PRIVATE);
                int a = adult_pref.getInt("adult_key",0);
                int b = children_pref.getInt("children_key",0);
                int c = baby_pref.getInt("baby_key",0);
                String adult_str = "大人"+String.valueOf(a)+"人";
                String children_str = "小人"+String.valueOf(b)+"人";
                String baby_str = "幼児"+String.valueOf(c)+"人";
                TextView adult_tv = (TextView)viw.findViewById(R.id.adulttext);
                TextView children_tv = (TextView)viw.findViewById(R.id.childrentext);
                TextView baby_tv = (TextView)viw.findViewById(R.id.babytext);
                adult_tv.setText(adult_str);
                children_tv.setText(children_str);
                baby_tv.setText(baby_str);

                // 決定ボタンを押す
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pref = getSharedPreferences("Preferences",MODE_PRIVATE);
                        EditText et = (EditText)viw.findViewById(R.id.textView18);
                        String str = et.getText().toString();
                        int i = Integer.parseInt(str);

                        SharedPreferences.Editor e = pref.edit();
                        e.putInt("pop8num",i);
                        e.commit();
                    }
                });

                alert.setView(viw);
                alert.show();
            }
        });

        //ボタン場所指定(ホーム)
        Button btnhome = (Button)findViewById(R.id.button12);
        btnhome.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.MainActivity");
                startActivity(intent);
            }
        });

        //ボタン場所指定(非常食)
        Button btnhijo = (Button)findViewById(R.id.button13);
        btnhijo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.misakitatsuya.hello_world", "com.example.misakitatsuya.hello_world.hijo");
                startActivity(intent);
            }
        });

        //ボタン場所指定(設定)
        Button btnsetting = (Button)findViewById(R.id.button15);
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
