package org.example.producerapi.service;
import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.producerapi.model.AdministrativeUnit;
import org.example.producerapi.repository.AdministrativeUnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AUService {
    @Autowired
    private  AdministrativeUnitRepository administrativeUnitRepository;

    Logger logger = LoggerFactory.getLogger(AUService.class);

    @Async
    public CompletableFuture<List<AdministrativeUnit>> createAdministrativeUnit(MultipartFile file) {
        try {
            long startTime = System.currentTimeMillis();
            List<AdministrativeUnit> administrativeUnits = parseCsvFile(file);

            logger.info("Saving list of administrative units of size " + administrativeUnits.size() + " " +  Thread.currentThread().getName());
            administrativeUnitRepository.save(administrativeUnits);

            long endTime = System.currentTimeMillis();
            logger.info("Total time {} ms", endTime - startTime);
            return CompletableFuture.completedFuture(administrativeUnits);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<List<AdministrativeUnit>> findAllAdministrativeUnits() {
        logger.info("Finding all administrative units");
        List<AdministrativeUnit> administrativeUnits = administrativeUnitRepository.findAll();
        return CompletableFuture.completedFuture(administrativeUnits);
    }

    private List<AdministrativeUnit> parseCsvFile(final MultipartFile file) throws IOException {
        try {
            if (!isValidExcelFile(file)) {
                throw new IOException(file.getOriginalFilename() + " is not a valid excel file");
            }

            final List<AdministrativeUnit> administrativeUnits = new ArrayList<>();
            try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
                XSSFSheet sheet = workbook.getSheetAt(0);

                int rowIndex = 0;
                for (Row row : sheet) {
                    if (rowIndex == 0) {
                        rowIndex++;
                        continue;
                    }

                    Iterator<Cell> cellIterator = row.cellIterator();
                    int cellIndex = 0;


                    AdministrativeUnit administrativeUnit = new AdministrativeUnit();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();


                        switch (cellIndex) {
                            case 0 -> administrativeUnit.setCode(cell.getStringCellValue());
                            case 1 -> administrativeUnit.setName(cell.getStringCellValue());
                            case 2 -> administrativeUnit.setNameEnglish(cell.getStringCellValue());
                            case 3 -> administrativeUnit.setLevel(cell.getStringCellValue());
                            case 4 -> administrativeUnit.setDistrictCode(cell.getStringCellValue().isEmpty() ? "" : cell.getStringCellValue());
                            case 5 -> administrativeUnit.setDistrict(cell.getStringCellValue().isEmpty() ? "" : cell.getStringCellValue());
                            case 6 -> administrativeUnit.setProvinceCityCode(cell.getStringCellValue().isEmpty() ? "" : cell.getStringCellValue());
                            case 7 -> administrativeUnit.setProvinceCity(cell.getStringCellValue().isEmpty() ? "" : cell.getStringCellValue());
                        }

                        cellIndex++;
                    }

                    
                    administrativeUnits.add(administrativeUnit);
                }
            }
            return administrativeUnits;

        } catch (Exception e) {
            logger.error("Failed to parse CSV file", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean isValidExcelFile(MultipartFile file) {
        System.out.println(file.getContentType());
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        return true;
    }


}
