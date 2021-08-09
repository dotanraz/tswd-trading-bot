package com.theswdeveloper.tradingbot.io;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CSV {

    private static final Logger logger = LoggerFactory.getLogger(CSV.class);
    private String file;
    private String[] headers;
    private CSVPrinter csvPrinter;
    boolean isCsvGenerated = false;

    public CSV(String file) {
        this.file = file;
    }

    public CSV(String file, String[] headers) {
        this.file = file;
        this.headers = headers;
        generateCsvFile();
    }

    public void generateCsvFile() {
        if (!isCsvGenerated) {
            logger.info("generating csv file " + file);
            try {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.file) );
                this.csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(this.headers));
                csvPrinter.flush();
                csvPrinter.close();
                isCsvGenerated = true;
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void append(String[] headers, String... data) throws IOException {
        createCsvFileIfNotExist(headers);
        BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(this.file),
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
        csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(data);
        csvPrinter.flush();
        csvPrinter.close();
    }

    public Iterable<CSVRecord> readFromCsv(String [] headers) throws IOException {
        createCsvFileIfNotExist(headers);
        Reader in = new FileReader(this.file);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(headers)
                .withFirstRecordAsHeader()
                .parse(in);
        return records;
    }

    private void createCsvFileIfNotExist(String[] headers) {
        if (!isCsvFileExist()) {
            isCsvGenerated = false;
            this.headers = headers;
            generateCsvFile();
        }
    }

    public boolean isCsvFileExist() {
        return new File(this.file).exists();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

}
