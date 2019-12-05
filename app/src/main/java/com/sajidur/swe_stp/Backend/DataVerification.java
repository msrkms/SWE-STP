package com.sajidur.swe_stp.Backend;

public class DataVerification {




    public boolean Email(String Email){
        boolean email=false;
        if(Email.contains("@diu.edu.bd") || Email.contains("@daffodilvarsity.edu.bd")){
            email=true;
        }

        return email;
    }

    public boolean Name(String Name){
        boolean name=false;
        if(Name.length()>2 && String.valueOf(Name.charAt(0)).equals(String.valueOf(Name.charAt(0)).toUpperCase())){
            name=true;
        }

        return name;
    }

    public boolean Password(String Password){
        boolean password=false;
        if(Password.length()>=6 ){
            password=true;
        }

        return  password;
    }

    public boolean Phone(String Phone){
        boolean phone=false;
        if(Phone.startsWith("+88")){
        if(Phone.length()==14){
            phone=true;
        }

        }else if(Phone.startsWith("88")){

            if(Phone.length()==13){
                phone=true;
            }
        }else if (Phone.startsWith("0")){

            if(Phone.length()==11){
                phone=true;
            }
        }

        if(phone){
            try {
                long n= Long.parseLong(Phone);
            }catch (Exception e){
                phone=false;
            }
        }


        return phone;
    }


}
