package com.milotnt.controller;

//import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.milotnt.entity.ClassTable;
import com.milotnt.service.IClassTableService;
//import com.milotnt.service.impl.ClassTableRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/class")
public class FileController {
//    @Autowired
//    private ClassTableRepository classRepository; // 添加这一行
    @Autowired
    private IClassTableService classService;

    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {

        String fileName = "课程信息.xlsx"; // 设置您想要的文件名

        // 设置响应头
        HttpHeaders responseHeaders = new HttpHeaders();
        String defaultFileName = "课程信息.xlsx"; // 默认文件名
        String encodedFileName = URLEncoder.encode(fileName != null && !fileName.isEmpty() ? fileName : defaultFileName, "UTF-8");
        responseHeaders.add("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
        responseHeaders.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 设置文件类型


        List<ClassTable> courses = classService.list(); // 获取所有课程数据

        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("课程信息");

        // 创建表头
        Row headerRow = sheet.createRow(0);
        String[] headers = {"编号", "名称", "时间", "时长", "教练"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 添加数据行
        int rowNum = 1;
        for (ClassTable classTable : courses) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(classTable.getClassId());
            row.createCell(1).setCellValue(classTable.getClassName());
            row.createCell(2).setCellValue(classTable.getClassTime());
            row.createCell(3).setCellValue(classTable.getClassBegin());
            row.createCell(4).setCellValue(classTable.getCoach());
        }

        // 写入到输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();



        return new ResponseEntity<>(outputStream.toByteArray(), responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "请选择一个文件上传");
                return "redirect:/class/selClass"; // 重定向到课程管理页面
            }

            // 解析 Excel 文件并保存到数据库
            List<ClassTable> classList = new ArrayList<>();
            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // 从第二行开始解析（跳过标题行）
                    Row row = sheet.getRow(rowIndex);
                    if (row == null) continue; // 跳过空行

                    ClassTable classTable = new ClassTable();
                    classTable.setClassId((int) row.getCell(0).getNumericCellValue()); // 假设第一列是课程编号
                    classTable.setClassName(row.getCell(1).getStringCellValue()); // 假设第二列是课程名称
                    classTable.setClassBegin(row.getCell(2).getStringCellValue()); // 假设第三列是开课时间
                    classTable.setClassTime(row.getCell(3).getStringCellValue()); // 假设第四列是课程时长
                    classTable.setCoach(row.getCell(4).getStringCellValue()); // 假设第五列是教练

                    classList.add(classTable);
                }
            }

            // 保存课程信息到数据库
            for (ClassTable classTable : classList) {
                classService.save(classTable); // 保存课程信息
            }
            redirectAttributes.addFlashAttribute("success", "文件上传成功，已导入课程信息");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "文件上传失败: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "发生错误: " + e.getMessage());
        }
        return "redirect:/class/selClass"; // 重定向到课程管理页面
    }
}
