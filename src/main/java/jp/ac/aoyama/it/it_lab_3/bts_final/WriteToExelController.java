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
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("出張依頼申請書");

        // 行(0~16)作成
        for (int r = 0; r <= 16; ++r) {
            sheet.createRow(r);
        }

        // セル結合
        CellRangeAddress regionA1C1 = new CellRangeAddress(0, 0, 0, 2); // A1:C1
        CellRangeAddress regionA2A6 = new CellRangeAddress(1, 5, 0, 0); // A2:A6
        CellRangeAddress regionA7A14 = new CellRangeAddress(6, 13, 0, 0); // A7:A14
        CellRangeAddress regionA15A17 = new CellRangeAddress(14, 16, 0, 0); // A15:A17
        sheet.addMergedRegion(regionA1C1);
        sheet.addMergedRegion(regionA2A6);
        sheet.addMergedRegion(regionA7A14);
        sheet.addMergedRegion(regionA15A17);

        // 枠線スタイル
        CellStyle borderStyle = wb.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);
        borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // 枠線適用
        for (int r = 1; r <= 16; ++r) {
            Row row = sheet.getRow(r);
            for (int c = 0; c <= 2; ++c) {
                Cell cell = row.getCell(c);
                if (cell == null) {
                    cell = row.createCell(c);
                }
                cell.setCellStyle(borderStyle);
            }
        }

        // タイトル・セクション・本文スタイル
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

        // タイトル
        Cell titleCell = sheet.getRow(0).createCell(0);
        titleCell.setCellValue("出張依頼申請書");
        titleCell.setCellStyle(titleStyle);

        // セクション
        Cell sectionCell1 = sheet.getRow(1).createCell(0);
        sectionCell1.setCellValue("申請者");
        sectionCell1.setCellStyle(sectionStyle);

        Cell sectionCell2 = sheet.getRow(6).createCell(0);
        sectionCell2.setCellValue("出張者");
        sectionCell2.setCellStyle(sectionStyle);

        Cell sectionCell3 = sheet.getRow(14).createCell(0);
        sectionCell3.setCellValue("費用");
        sectionCell3.setCellStyle(sectionStyle);

        // 項目名 (B列) と出力データ (C列)
        String[] items = {
                "所属",
                "学部",
                "学科",
                "職名",
                "氏名",
                "所属機関名・部局",
                "氏名",
                "出張目的",
                "用務地",
                "用務先",
                "出張時間（時間）",
                "日当",
                "宿泊費",
                "運賃",
        };

        // C列に書き込むユーザ入力＆計算結果
        String[] userData = {
                model.getAffiliation(),
                model.getFaculty(),
                model.getDepartment(),
                model.getJobTitle(),
                model.getName(),
                model.getInstitutionTravel(),
                model.getTravelName(),
                model.getPurpose(),
                model.getLocation(),
                model.getDestination(),
                String.valueOf(model.getTripHours()),       // 計算された出張時間
                String.valueOf(model.getDailyAllowance()),  // サーバ側計算後の日当
                "",  // 宿泊費（未入力の例）
                ""   // 運賃（未入力の例）
        };

        // B列(COLUMN 1) → 項目名, C列(COLUMN 2) → データ
        for (int i = 0; i < items.length; i++) {
            Cell cellB = sheet.getRow(i + 1).getCell(1);
            cellB.setCellValue(items[i]);
            cellB.setCellStyle(bodyStyle);

            Cell cellC = sheet.getRow(i + 1).getCell(2);
            cellC.setCellValue(userData[i]);
            cellC.setCellStyle(bodyStyle);
        }

        // 列幅設定
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.setColumnWidth(2, 60 * 256);

        // 出力
        try {
            // main/resources/static/output.xlsx にエクセルファイルを作成
            String outputFilePath = getClass().getClassLoader().getResource("static").getPath()
                    + File.separator + "output.xlsx";
            System.out.println(outputFilePath);
            OutputStream fileOut = new FileOutputStream(outputFilePath);
            wb.write(fileOut);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 枠線を設定するための共通メソッド
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
        System.out.println("名前: " + model.getName());
        System.out.println("出張時間: " + model.getTripHours());

        // 出張時間による日当計算
        int travelHours = model.getTripHours();
        if (travelHours < 4) {
            model.setDailyAllowance(0);
        } else if (travelHours < 8) {
            model.setDailyAllowance(1000);
        } else if (travelHours < 12) {
            model.setDailyAllowance(2000);
        } else {
            model.setDailyAllowance(3000);
        }
        System.out.println("日当: " + model.getDailyAllowance());

        // Excelファイル作成
        createExcelFile(model);

        // 結果を返す
        return model;
    }
}