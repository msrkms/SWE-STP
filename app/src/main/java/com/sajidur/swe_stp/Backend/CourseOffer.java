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
import java.util.Iterator;

public class CourseOffer {


    JSONArray jsonArray= new JSONArray();

    public void read(String FilePath){
        boolean start=false;
        boolean startsec=false;
        boolean startcourse=false;
        int lavelandtermindex=0;
        String[] LavelandTerm = new String[12];
        int Year=0;
        int Semster=0;
        String Section="";
        String Course="";



        for (int i = 1; i < 5; i++) {

            for (int j = 1; j < 4; j++) {
                LavelandTerm[lavelandtermindex]=i+"_"+j;
                lavelandtermindex++;
            }
        }
        lavelandtermindex=0;

        try {
            File myFile = new File(FilePath);
            FileInputStream fis = new FileInputStream(myFile);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            int firstrow=mySheet.getFirstRowNum();
            int lastrow=mySheet.getLastRowNum();
            for(int i=firstrow;i<=lastrow;i++){
                Row row = mySheet.getRow(i);
                Iterator<Cell> cellIterator = row.cellIterator();
                for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
                    Cell cell = row.getCell(j);
                    try{


                        String value=cell.getStringCellValue();
                        if(value.equalsIgnoreCase(LavelandTerm[lavelandtermindex])){
                            start=true;
                            String[] data = new String[2];
                            data=value.split("_");
                            Year=Integer.parseInt(data[0]);
                            Semster=(((Year-1)*3)+Integer.parseInt(data[1]));
                            lavelandtermindex++;
                            startsec=true;
                        }

                        if(start){
                            if(startsec){
                                if(j==2){
                                    Section=value;
                                    startsec=false;
                                }
                            }

                            if(j==4){
                                if(Course.equalsIgnoreCase(value)){

                                }else{
                                    Course=value;
                                    if(Course.contains(" ")){
                                        StringBuilder sb = new StringBuilder(Course);
                                        sb.deleteCharAt(sb.indexOf(" "));
                                        Course=sb.toString();


                                    }

                                    JSONObject jsonObject1=new JSONObject();


                                    jsonObject1.put("Semester",Semster);
                                    jsonObject1.put("Section",Section);
                                    jsonObject1.put("CourseCode",Course);


                                    jsonArray.put(jsonObject1);

                                    j=row.getLastCellNum()+1;
                                    break;
                                }
                            }
                        }

                        if(j>5){
                            j=row.getLastCellNum();
                            break;
                        }


                    }catch(Exception e){
                    }


                }


            }


        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public JSONArray getCourseJsonArray(){
        return  jsonArray;
    }
}
