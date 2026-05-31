package com.gatewayflow.fleettrack.report;

import com.gatewayflow.fleettrack.domain.entity.Vehicle;
import com.gatewayflow.fleettrack.repository.VehicleRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfReportServiceImpl
        implements PdfReportService {

    private final VehicleRepository vehicleRepository;

    @Override
    public byte[] generateFleetReport() {

        try {

            List<Vehicle> vehicles =
                    vehicleRepository.findAll();

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            document.add(
                    new Paragraph(
                            "FleetTrack Vehicle Report"
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            PdfPTable table =
                    new PdfPTable(5);

            table.addCell("ID");
            table.addCell("Make");
            table.addCell("Model");
            table.addCell("Year");
            table.addCell("Status");

            for (Vehicle vehicle : vehicles) {

                table.addCell(
                        vehicle.getId().toString()
                );

                table.addCell(
                        vehicle.getMake()
                );

                table.addCell(
                        vehicle.getModel()
                );

                table.addCell(
                        String.valueOf(
                                vehicle.getYear()
                        )
                );

                table.addCell(
                        vehicle.getStatus()
                                .name()
                );
            }

            document.add(table);

            document.close();

            return outputStream.toByteArray();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "Failed to generate PDF report",
                    ex
            );
        }
    }
}