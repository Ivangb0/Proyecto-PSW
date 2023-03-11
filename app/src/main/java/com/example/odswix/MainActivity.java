package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.transform.Result;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdb?useSSL=true";

    private static final String USERNAME = "KogMaw";
    private static final String PASSWORD = "WIXAdmin1";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
    }

    public void buttonConnectToMySQL(View view){
        try{
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            Toast.makeText(this,"CONNECTED",Toast.LENGTH_LONG);

            Statement statement = connection.createStatement();
            StringBuffer stringBuffer = new StringBuffer();
            ResultSet resultSet = statement.executeQuery("select * from perros");

            while (resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + resultSet.getString(2) + "\n");

            }


            textView.setText(stringBuffer.toString());
            connection.close();
        }
        catch (Exception e){
            textView.setText(e.toString());
            System.out.print(e.toString());
        }
    }
}