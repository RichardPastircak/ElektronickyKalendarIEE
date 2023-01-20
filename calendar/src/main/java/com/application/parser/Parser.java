package com.application.parser;

import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class Parser {
    public String test = "test";
//    public Month currentMonth = LocalDate.now().getMonth();
    public List<String> parseData(String shiftName) throws IOException {
//        if (month == 1){
//            currentMonth = currentMonth.plus(1);
//        }
//        else if (month == -1){
//            currentMonth = currentMonth.minus(1);
//        }
//
//
//        System.out.println(shiftName + " " + currentMonth);
        //File excelFile = new File("C:\\Programy\\IntelliJ IDEA\\projects\\elektronicky-zmenovy-kalendar\\calendar\\src\\main\\resources\\kalendar.xlsx");
        File excelFile = new File("/root/calendar/src/main/resources/\\kalendar.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        DataFormatter formatter = new DataFormatter();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        //Sheet sheet0 = wb.getSheetAt(0); //prescas
        //Sheet sheet1 = wb.getSheetAt(1); //zmema A
        //Sheet sheet2 = wb.getSheetAt(2); //zmena B
        //Sheet sheet3 = wb.getSheetAt(3); //zmena C
        //Sheet sheet4 = wb.getSheetAt(4); //zmena D
        //Sheet sheet5 = wb.getSheetAt(5); //sumar

        Sheet sheet = wb.getSheetAt(5);
        int rows=21;

        String shift= shiftName; //pracovna zmena A, B, C, D
        Integer start=1; //21 riadkov musi nacitat

        switch(shift){
            case "A":
                start=1;
                break;
            case "B":
                start=22;
                break;
            case "C":
                start=43;
                break;
            case "D":
                start=64;
                break;
        }
        List<String> list = new LinkedList<>();

        for (int r = start; r < rows+start-1; r++) {
            Row row = sheet.getRow(r);
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
//                System.out.print(cellRef.formatAsString());
//                System.out.print(" - ");
                String hex = new String();
                try{
                    XSSFCellStyle cs = (XSSFCellStyle) cell.getCellStyle();
                    XSSFColor colour = cs.getFillForegroundColorColor();
                    hex = Hex.encodeHexString( colour.getRGB() );
                }catch (NullPointerException e){}

                switch (cell.getCellType()) {
                    case STRING:
//                        System.out.println(cell.getRichStringCellValue().getString());
                        list.add(cellRef.formatAsString() + " : " + cell.getStringCellValue() + " : " + hex + " : ");
                        //System.out.println(cell.getCellStyle().getFillBackgroundColor());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
//                            System.out.println(cell.getDateCellValue());
                            String text = formatter.formatCellValue(cell);
//							if (cell.getCellStyle().getFillBackgroundColorColor() != null) {
//								System.out.println("Color of cell " + cellRef.formatAsString() + " is " + HSSFColor.getIndexHash().get(cell.getCellStyle().getFillBackgroundColor()));
//							}

                            list.add(cellRef.formatAsString() + " : " + text + " : "+ hex + " : ");
                        } else {
//                            System.out.println(cell.getNumericCellValue());
                            list.add(cellRef.formatAsString() + " : " + String.valueOf(cell.getNumericCellValue()) + " : "+ hex + " : ");
                        }
                        break;
                    case BOOLEAN:
//                        System.out.println(cell.getBooleanCellValue());
                        list.add(cellRef.formatAsString() + " : " + String.valueOf(cell.getBooleanCellValue()) + " : "+ hex + " : ");

                        break;
                    case FORMULA:
//                        System.out.println(cell.getCellFormula());
                        list.add(cellRef.formatAsString() + " : " + String.valueOf(cell.getCellFormula()) + " : "+ hex + " : ");

                        break;
                    default:
//                        System.out.println();
                }
            }
        }

//        for (String item : list){
//            System.out.println(item);
//        }

        return list;
    }

}
