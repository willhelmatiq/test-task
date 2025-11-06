package com.example.test_task;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.test_task.HeapUtils.*;
import static com.example.test_task.HeapUtils.siftUpMaxHeap;

@Service
public class MyService {

    public int processExcel(String filePath, int n) {
        File file = new File(filePath);

        if (!file.exists() || !filePath.endsWith(".xlsx")) {
            throw new RuntimeException("Файл не найден или имеет неверный формат");
        }
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell == null) {
                    break;
                }
                numbers.add((int) cell.getNumericCellValue());
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
        }
        if (numbers.isEmpty()) {
            throw new RuntimeException("Ошибка: файл не содержит чисел");
        } else if (n > numbers.size()) {
            throw new RuntimeException("Ошибка: файл содержит " + numbers.size() +
                    " чисел. n должно быть не больше " + numbers.size());
        }
        int[] heapMax = new int[n];
        int lengthMaxHeap = 0;
        for (int element : numbers) {
            if (lengthMaxHeap < n) {
                lengthMaxHeap++;
                heapMax[lengthMaxHeap - 1] = element;
                siftUpMaxHeap(heapMax, lengthMaxHeap - 1);
            } else {
                if (element < heapMax[0]) {
                    heapMax[0] = element;
                    siftDownMaxHeap(heapMax, 0, lengthMaxHeap);
                }
            }
        }
        return heapMax[0];
    }
}
