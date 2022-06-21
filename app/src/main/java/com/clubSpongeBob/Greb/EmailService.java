package com.clubSpongeBob.Greb;

import android.os.StrictMode;
import android.util.Log;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    private static String sender;
    private static String password;
    private static String TAG = "Email Service";

    public EmailService(){
        try{
            sender = CommonUtils.getSContext().getString(R.string.EMAIL);
            password = CommonUtils.getSContext().getString(R.string.EMAIL_API_KEY);
            Log.i(TAG, "Sender email: " + sender);
            Log.i(TAG, "Sender email password: "+ password);
        }catch (Exception e){
            Log.e(TAG, "Error in initialising Email Service");
            Log.e(TAG, e.getMessage());
        }

    }

    public static void sendEmail(String emergencyContact, String location, String destination, Driver driver) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emergencyContact));
            message.setSubject("SOS from Greb");
            message.setText(String.format("Help, I am heading from %s to %s. I am in danger! The driver details as below:\nName: %s\nCar Model: %s\nCar Colour: %s\nCar Plate: %s",
                    location, destination,driver.getName(),driver.getCarModel(), driver.getCarColour(), driver.getCarPlate()));
            Transport.send(message);
            Log.i(TAG, "Email is sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
