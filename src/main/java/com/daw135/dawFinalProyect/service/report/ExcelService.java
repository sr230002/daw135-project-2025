package com.daw135.dawFinalProyect.service.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw135.dawFinalProyect.entity.admin.EventoProgramacion;
import com.daw135.dawFinalProyect.entity.eventos.Evento;
import com.daw135.dawFinalProyect.entity.eventos.EventoRegistro;
import com.daw135.dawFinalProyect.repository.eventos.EventoProgramacionRepository;
import com.daw135.dawFinalProyect.repository.eventos.EventoRegistroRepository;

@Service
public class ExcelService {

    @Autowired
    private EventoProgramacionRepository eventoProgramacionRepository;
    @Autowired
    private EventoRegistroRepository eventoRegistroRepository;

    public byte[] generateAttendanceExcel(Long eventoProgramacionId) throws IOException {
        Optional<EventoProgramacion> optionalProgramacion = eventoProgramacionRepository.findById(eventoProgramacionId);

        if (optionalProgramacion.isEmpty()) {
            throw new IllegalArgumentException(
                    "No se encontró la programación del evento con ID: " + eventoProgramacionId);
        }

        EventoProgramacion programacion = optionalProgramacion.get();
        Evento evento = programacion.getEvento();
        List<EventoRegistro> registros = eventoRegistroRepository.findBySesionId(eventoProgramacionId);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Reporte de Asistencia");

            // --- Event Information Header ---
            int rowIdx = 0;
            Row eventTitleRow = sheet.createRow(rowIdx++);
            eventTitleRow.createCell(0).setCellValue("Reporte de Asistencia del Evento");
            CellStyle boldStyle = workbook.createCellStyle();
            Font boldFont = workbook.createFont();
            boldFont.setBold(true);
            boldStyle.setFont(boldFont);
            eventTitleRow.getCell(0).setCellStyle(boldStyle);

            Row eventNameRow = sheet.createRow(rowIdx++);
            eventNameRow.createCell(0).setCellValue("Título del Evento:");
            eventNameRow.createCell(1).setCellValue(evento.getTitulo());
            eventNameRow.getCell(0).setCellStyle(boldStyle);

            Row eventDescriptionRow = sheet.createRow(rowIdx++);
            eventDescriptionRow.createCell(0).setCellValue("Descripción:");
            eventDescriptionRow.createCell(1).setCellValue(evento.getDescripcion());
            eventDescriptionRow.getCell(0).setCellStyle(boldStyle);

            Row programDateRow = sheet.createRow(rowIdx++);
            programDateRow.createCell(0).setCellValue("Fecha de Programación:");
            programDateRow.createCell(1).setCellValue(
                    programacion.getFechaProgramacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            programDateRow.getCell(0).setCellStyle(boldStyle);

            Row programTimeRow = sheet.createRow(rowIdx++);
            programTimeRow.createCell(0).setCellValue("Hora de Programación:");
            programTimeRow.createCell(1)
                    .setCellValue(programacion.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm")) + " - "
                            + programacion.getHoraFin().format(DateTimeFormatter.ofPattern("HH:mm")));
            programTimeRow.getCell(0).setCellStyle(boldStyle);

            Row programPlaceRow = sheet.createRow(rowIdx++);
            programPlaceRow.createCell(0).setCellValue("Lugar:");
            programPlaceRow.createCell(1).setCellValue(programacion.getLugar());
            programPlaceRow.getCell(0).setCellStyle(boldStyle);

            if (programacion.getEnlace() != null && !programacion.getEnlace().isEmpty()) {
                Row programLinkRow = sheet.createRow(rowIdx++);
                programLinkRow.createCell(0).setCellValue("Enlace:");
                programLinkRow.createCell(1).setCellValue(programacion.getEnlace());
                programLinkRow.getCell(0).setCellStyle(boldStyle);
            }

            rowIdx += 2; // Add some space before the table

            // --- Participants Table Header ---
            Row headerRow = sheet.createRow(rowIdx++);
            headerRow.createCell(0).setCellValue("No.");
            headerRow.createCell(1).setCellValue("Nombre del Participante");
            headerRow.createCell(2).setCellValue("Correo Electrónico");
            headerRow.createCell(3).setCellValue("Asistencia Confirmada");

            // Apply bold style to headers
            for (int i = 0; i < 4; i++) {
                headerRow.getCell(i).setCellStyle(boldStyle);
            }

            // --- Participants Data ---
            int participantNumber = 1;
            for (EventoRegistro registro : registros) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(participantNumber++);
                row.createCell(1).setCellValue(registro.getParticipanteId().getNombre());
                row.createCell(2).setCellValue(registro.getParticipanteId().getCorreo());
                row.createCell(3).setCellValue(registro.getAsistenciaConfirmada() ? "Sí" : "No");
            }

            // Auto-size columns for better readability
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();

        }
    }
}