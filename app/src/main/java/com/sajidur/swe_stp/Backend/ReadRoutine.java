package com.sajidur.swe_stp.Backend;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadRoutine {
    ArrayList<RoutineStructure> arrayListRoutine=new ArrayList<RoutineStructure>();
    boolean save=false;
    String TeacherInfo="";
    int indexnumber=0;
    boolean time=false;
    String[] SlotTime=new String[6];
    int[] TimeCellNo = new int[6];
    int totalslot=6;
    int remainingslot=0;
    String Day="";
    boolean course=false;
    boolean dayfind=false;

    int couurseindex=0;
    int dataindex=0;
    String[] Data =  new String[3];

    JSONArray jsonArray= new JSONArray();
    JSONObject jsonObject=new JSONObject();
    public void Read(String fileurl){
        try {
            File myFile = new File(fileurl);
            FileInputStream fis = new FileInputStream(myFile);

            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);


            XSSFSheet mySheet = myWorkBook.getSheetAt(0);


            int firstrow=mySheet.getFirstRowNum();
            int lastrow=mySheet.getLastRowNum();
            int coursestart=2;


            boolean countdown=false;
            for(int i=firstrow;i<lastrow;i++){
                Row row = mySheet.getRow(i);
                Iterator<Cell> cellIterator = row.cellIterator();
                int l=3;

                for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);
                    try{


                        if(couurseindex>5){

                            couurseindex=0;

                        }
                        String value=cell.getStringCellValue();
                        if(time){

                            if(value.contains("-")){
                                SlotTime[indexnumber]=value;
                                indexnumber++;
                                if(totalslot<1){
                                    time=false;
                                    totalslot=6;
                                }
                                totalslot--;
                            }

                        }


                        if(value.length()>150){
                            TeacherInfo=value;
                            i=lastrow+1;
                            break;
                        }

                        if(value.equalsIgnoreCase("Saturday")){

                            dayfind=true;
                            coursestart=2;
                            countdown=true;
                        }
                        if(value.contains("day")){
                            Day=value;
                        }

                        if(course){


                            Data[dataindex]=value;
                            dataindex++;

                            if(dataindex>2){
                                dataindex=0;
                                save=true;

                            }
                        }

                        if(save){
                            dataindex=0;
                            save=false;

                            String Sec="";

                            if(Data[1].length()>6){
                                Sec= Character.toString(Data[1].charAt(6));
                                if(Sec.equals(" ")){
                                    Sec= Character.toString(Data[1].charAt(5));
                                }
                                if(Data[1].contains("LAB")){
                                    Sec= Character.toString(Data[1].charAt(Data[1].indexOf("LAB")-1));

                                }
                            }else if(Data[1].length()>5){
                                Sec= Character.toString(Data[1].charAt(5));
                            }




                            if(Sec.equals("(")){
                                Sec="";
                                for(int loop=Data[1].indexOf("(");loop<Data[1].length();loop++){
                                    Sec=Sec+Data[1].charAt(loop);
                                }

                            }

//                            Routine ru=new Routine(Day, SlotTime[couurseindex], Data[0], Sec, Data[2], Data[1]);
//                            new Data(ru);
                            if(Day.equals("Tuesday")){
                                //  System.out.println(couurseindex);
                            }
                            JSONObject jsonObject1=new JSONObject();


                            jsonObject1.put("Day",Day);
                            jsonObject1.put("Time",SlotTime[couurseindex]);
                            jsonObject1.put("RoomNo",Data[0]);
                            jsonObject1.put("CourseCode",Data[1]);
                            jsonObject1.put("Sec",Sec);
                            jsonObject1.put("Teacher",Data[2]);

                            jsonArray.put(jsonObject1);



                            couurseindex++;


                        }

                    }catch(Exception e){
                        couurseindex++;
                        dataindex=0;
                    }

                    if(course){
                        if(j>17){
                            j=row.getLastCellNum();
                            couurseindex=0;
                            dataindex=0;
                        }
                    }

                    l--;
                    if(l<1){

                    }

                }
                dataindex=0;
                if(countdown){
                    coursestart--;
                    if(coursestart<0){
                        countdown=false;
                    }
                }

                if(coursestart<0){
                    course=true;
                }

                if(dayfind){
                    time=true;
                }
            }


        }
        catch (IOException e) {
            System.out.println(e.toString());
        }



    }

    public JSONArray getRoutineJSONArray(){
        return jsonArray;
    }

}
