package com.example.project_prm392_se1614;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392_se1614.entity.MyDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.IllegalWriteException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPassWordActivity extends AppCompatActivity {

    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgotpassword);

        Button send = findViewById(R.id.send);
        send.setOnClickListener(this::sendMail);

        TextView signIn = findViewById(R.id.fgsignin);
        signIn.setOnClickListener(this::returnSignIn);
        database = MyDatabase.getInstance(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void returnSignIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void sendMail(View view) {
        EditText email = findViewById(R.id.fgemail);
        if(email.getText().toString() == null){
            Toast.makeText(this, "email must required!", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
        String emailRecipe = "anhnhhe153607@fpt.edu.vn";//email.getText().toString().trim()
//        if(database.getUserDao().getUserByEmail(emailRecipe) == null){
//            Toast.makeText(this, "your Email incorrect! must using email for your account", Toast.LENGTH_SHORT).show();
//            return;
//        }
        Properties properties = System.getProperties();
        String userMail = "anhh34711@gmail.com";
        String pass = "bxmkoksetsqycjxf";
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom("anhh34711@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipe));

            UUID code = UUID.randomUUID();

            message.setSubject("Reset password!");
            message.setText("This is code for change Password: "+code.toString());

            File cacheDir = this.getCacheDir();

            File cacheFile = new File(cacheDir, "uuid_forgot_password.txt");

            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                outputStream.write(code.toString().getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            new Thread(() -> {
                try {
                    Log.d("sending ", "sendMail: ");
                    Transport.send(message,userMail, pass);
                    // delete cache after 1phut
                    Thread.sleep(60 * 1000);
                    deleteCacheForgot();
                    Log.d("sending ", "sendMail: ");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();

        Intent intent = new Intent(this, ConfirmMailPassActivity.class);
        startActivity(intent);

        }catch (Exception e){
            deleteCacheForgot();
            e.printStackTrace();
            Log.e("Send mail ", "send mail to "+emailRecipe +" failed because "+e.getMessage());
            Toast.makeText(this, "system can't execute this action now! please try again after a while ", Toast.LENGTH_LONG).show();
        }
        }).start();
    }

    public void deleteCacheForgot(){
        File cacheDir = this.getCacheDir();
        File cacheFile = new File(cacheDir, "uuid_forgot_password.txt");

        if(cacheDir.exists()) {
            cacheFile.delete();
        }
    }
}
