package com.daw135.dawFinalProyect.service.report;

import net.sf.jasperreports.engine.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.Map;

@Service
public class JasperReportService {

    private static final Logger logger = LogManager.getLogger(JasperReportService.class);

    private static final String JASPER_REPORT_LOCATION = "/report/jaspertReport/";

    @Autowired
    private DataSource dataSource;

    public String generarReportePDF(Map<String, Object> parametros, String nombreReporte) {
        try (Connection connection = dataSource.getConnection()) { // Usa la conexión de tu DB

            // 1. Cargar y compilar el reporte
            String rutaCompleta = JASPER_REPORT_LOCATION + nombreReporte ;
            InputStream jrxmlStream = getClass().getResourceAsStream(rutaCompleta);

            if (jrxmlStream == null) {
                throw new RuntimeException("Reporte no encontrado: " + rutaCompleta);
            }

            JasperReport reporte = JasperCompileManager.compileReport(jrxmlStream);

            // 2. Llenar el reporte con datos desde la DB (usando la conexión)
            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, connection);

            // 3. Exportar a PDF y convertir a Base64
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, outputStream);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (Exception e) {
            logger.error("Error generando reporte desde DB", e);
            return null;
        }
    }
}