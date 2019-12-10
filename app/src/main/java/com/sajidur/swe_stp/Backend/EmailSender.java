package com.sajidur.swe_stp.Backend;

import android.os.AsyncTask;

public class EmailSender {

    String Body;
    String Email;
    public void SendVerificationEmail(final String UserEmail, String Vcode){
        Email=UserEmail;
        Body="Your Verification Code is "+Vcode;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail mail = new Mail("verify.diure@gmail.com","01910778878");
                String[] to ={Email};
                mail.set_from("verify.diure@gmail.com");
                mail.set_to(to);
                mail.set_subject("Email Verification");
                mail.setBody(Body);
                try {
                    if(mail.send()){
                        System.out.println("mail sent");
                    }else{
                        System.out.println("mail not sent");
                    }
                } catch (Exception e) {
                    System.out.println("email error"+e.toString());
                }
            }

        }).start();

    }



}
/*
class sendmail extends AsyncTask<Void,Void,Boolean> {

    @Override
    protected Boolean doInBackground(Void... voids) {


        Mail mail = new Mail("verify.diure@gmail.com","01910778878");
        String[] to ={"sajidurbd@gmail.com"};
        mail.set_from("verify.diure@gmail.com");
        mail.set_to(to);
        mail.set_subject("Testing");
        mail.setBody("this is test mail");
        try {
            if(mail.send()){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println("email error"+e.toString());
            return false;
        }
    }


}

 */