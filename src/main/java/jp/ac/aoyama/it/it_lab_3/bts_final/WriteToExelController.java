package jp.ac.aoyama.it.it_lab_3.bts_final;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class WriteToExelController {
    private void setBorderCellStyle(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    }

    public void createExcelFile(DailyAllowanceModel model) {
        // 新規ブックの作成
        Workbook wb = new XSSFWorkbook();
        // 「出張依頼申請書」シートを作成
        Sheet sheet = wb.createSheet("出張依頼申請書");

        // 必要な行の用意
        for (int r = 0; r <= 16; ++r){
            sheet.createRow(r);
        }

        // セルの結合範囲を定義
        CellRangeAddress regionA1C1 = new CellRangeAddress(0, 0, 0, 2); // A1:C1
        CellRangeAddress regionA2A6 = new CellRangeAddress(1, 5, 0, 0); // A2:A6
        CellRangeAddress regionA7A14 = new CellRangeAddress(6, 13, 0, 0);   // A7:A14
        CellRangeAddress regionA15A17 = new CellRangeAddress(14, 16, 0, 0); // A15:A17

        // 結合をシートへ追加
        sheet.addMergedRegion(regionA1C1);
        sheet.addMergedRegion(regionA2A6);
        sheet.addMergedRegion(regionA7A14);
        sheet.addMergedRegion(regionA15A17);

        // はじめに枠線を作成
        CellStyle borderStyle = wb.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // 行1 ~ 16, 列0 ~ 2のセルに対して枠線を適用
        for (int r = 1; r <= 16; ++r){
            Row row = sheet.getRow(r);
            for (int c = 0; c <= 2; ++c){
                Cell cell = row.getCell(c);
                if (cell == null){
                    cell = row.createCell(c);
                }
                cell.setCellStyle(borderStyle);
            }
        }

        // フォントとスタイルの設定
        Font titleFont = wb.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 18);

        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        setAllBorders(titleStyle, BorderStyle.THIN, IndexedColors.BLACK.getIndex());

        Font sectionFont = wb.createFont();
        sectionFont.setBold(true);
        sectionFont.setFontHeightInPoints((short) 14);

        CellStyle sectionStyle = wb.createCellStyle();
        sectionStyle.setFont(sectionFont);
        sectionStyle.setAlignment(HorizontalAlignment.CENTER);
        sectionStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        setAllBorders(sectionStyle, BorderStyle.THIN, IndexedColors.BLACK.getIndex());

        Font bodyFont = wb.createFont();
        bodyFont.setFontName("Meiryo");

        CellStyle bodyStyle = wb.createCellStyle();
        bodyStyle.setFont(bodyFont);
        setAllBorders(bodyStyle, BorderStyle.THIN, IndexedColors.BLACK.getIndex());

        // セルに文字列とスタイルを追加
        Cell titleCell = sheet.getRow(0).createCell(0);
        titleCell.setCellValue("出張依頼申請書");
        titleCell.setCellStyle(titleStyle);

        Cell sectionCell1 = sheet.getRow(1).createCell(0);
        sectionCell1.setCellValue("申請者");
        sectionCell1.setCellStyle(sectionStyle);

        Cell sectionCell2 = sheet.getRow(6).createCell(0);
        sectionCell2.setCellValue("出張者");
        sectionCell2.setCellStyle(sectionStyle);

        Cell sectionCell3 = sheet.getRow(14).createCell(0);
        sectionCell3.setCellValue("費用");
        sectionCell3.setCellStyle(sectionStyle);

        // 列Bに項目を設定
        String[] items = {
                "所属", "学部", "学科", "職名", "氏名",
                "所属機関名・部局", "職名", "氏名", "出張目的",
                "用務地", "用務先", "日程", "出張時間（日帰りの場合）",
                "日当", "宿泊費", "運賃"
        };
        for (int i = 0; i < items.length; i++) {
            Cell cell = sheet.getRow(i + 1).createCell(1);
            cell.setCellValue(items[i]);
            cell.setCellStyle(bodyStyle);
        }

        // 列幅の設定（スタイルと内容設定後に実行）
        sheet.autoSizeColumn(0);    // A列の幅を自動設定
        sheet.autoSizeColumn(1);    // B列の幅を自動設定
        sheet.setColumnWidth(2, 60 * 256);  // C列の幅を60ポイントに設定

        // 出力
        try {
            // main/resources/static/output.xlsx にエクセルファイルを出力
            String outputFilePath = getClass().getClassLoader().getResource("static").getPath()
                    + File.separator + "output.xlsx";
            System.out.println(outputFilePath);
            OutputStream fileOut = new FileOutputStream(outputFilePath);
            wb.write(fileOut);
            wb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 枠線を設定するメソッド（スタイルに直接枠線を設定）
    private static void setAllBorders(CellStyle style, BorderStyle border, short color) {
        style.setBorderTop(border);
        style.setBorderBottom(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setTopBorderColor(color);
        style.setBottomBorderColor(color);
        style.setLeftBorderColor(color);
        style.setRightBorderColor(color);
    }

    @PostMapping("/calc_daily_allowance")
    public DailyAllowanceModel calcDailyAllowance(@RequestBody DailyAllowanceModel model) {
        System.out.println(model.getName());
        System.out.println(model.getTravelHours());
        int travelHours = model.getTravelHours();
        if (travelHours < 4) {
            model.setDailyAllowance(0);
        } else if (4 <= travelHours && travelHours < 8) {
            model.setDailyAllowance(1000);
        } else if (8 <= travelHours && travelHours < 12) {
            model.setDailyAllowance(2000);
        } else if (12 <= travelHours) {
            model.setDailyAllowance(3000);
        }
        System.out.println(model.getDailyAllowance());
        createExcelFile(model);
        return model;
    }
}
