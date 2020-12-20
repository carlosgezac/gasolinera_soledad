package com.gasolinerasoledadsacv.reportes;

import com.gasolinerasoledadsacv.entity.Empleado;
import com.gasolinerasoledadsacv.util.DateUtil;
import com.gasolinerasoledadsacv.util.PathUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ReporteEmpleadoPDF {

    public void crearPDFEmpleado(Empleado empleado, String ruta) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);
        // Text
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset(140, page.getMediaBox().getHeight() - 52);
            contentStream.showText("Estación de Servicios Soledad S.A de C.V.");
            contentStream.endText();

            // Logo de gasolinera
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/images/logo.png"));
            PDImageXObject pdLogo = LosslessFactory.createFromImage(document, image);
            contentStream.drawImage(pdLogo, 430, 730, 30, 30);

            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset(30, page.getMediaBox().getHeight() - 120);
            contentStream.showText("Datos del empleado");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.showText("No. Empleado:");
            contentStream.newLine();
            contentStream.showText("Nombres:");
            contentStream.newLine();
            contentStream.showText("Apellido Paterno:");
            contentStream.newLine();
            contentStream.showText("Apellido Materno:");
            contentStream.newLine();
            contentStream.showText("Escolaridad:");
            contentStream.newLine();
            contentStream.showText("CURP:");
            contentStream.newLine();
            contentStream.showText("Estatus:");
            contentStream.newLine();
            contentStream.showText("Fecha de Nacimiento:");
            contentStream.newLine();
            contentStream.showText("Genero:");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Dirección");
            contentStream.newLine();
            contentStream.showText("Calle:");
            contentStream.newLine();
            contentStream.showText("Num. Interior:");
            contentStream.newLine();
            contentStream.showText("Num. Exterior:");
            contentStream.newLine();
            contentStream.showText("Colonia:");
            contentStream.newLine();
            contentStream.showText("Municipio:");
            contentStream.newLine();
            contentStream.showText("Estado:");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Contacto");
            contentStream.newLine();
            contentStream.showText("Telefono Casa:");
            contentStream.newLine();
            contentStream.showText("Celular:");
            contentStream.newLine();
            contentStream.showText("Correo Electrónico:");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Cargo/Puesto:");
            contentStream.newLine();
            contentStream.showText("Fecha de Ingreso:");
            contentStream.newLine();
            contentStream.showText("RFC:");
            contentStream.newLine();
            contentStream.showText("NSS:");
            contentStream.newLine();
            contentStream.showText("Enfermedades:");
            contentStream.newLine();
            contentStream.showText("Incidentes Laborales:");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Cursos Capacitación:");
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText("Datos Adicionales:");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(155, page.getMediaBox().getHeight() - 120);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(empleado.getNumeroEmpleado());
            contentStream.newLine();
            contentStream.showText(empleado.getNombre());
            contentStream.newLine();
            contentStream.showText(empleado.getApellidoPaterno());
            contentStream.newLine();
            contentStream.showText(empleado.getApellidoMaterno());
            contentStream.newLine();
            contentStream.showText(empleado.getEscolaridad());
            contentStream.newLine();
            contentStream.showText(empleado.getCurp());
            contentStream.newLine();
            contentStream.showText(empleado.getEstatus());
            contentStream.newLine();
            contentStream.showText(DateUtil.formatDefault(empleado.getFechaNacimiento()));
            contentStream.newLine();
            contentStream.showText(empleado.getGenero());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getCalle());
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getNumeroInterior());
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getNumeroExterior());
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getColonia());
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getMunicipio());
            contentStream.newLine();
            contentStream.showText(empleado.getDireccion().getEstado());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(empleado.getContacto().getTelefonoCasa());
            contentStream.newLine();
            contentStream.showText(empleado.getContacto().getCelular());
            contentStream.newLine();
            contentStream.showText(empleado.getContacto().getEmail());
            contentStream.newLine();
            contentStream.newLine();
            contentStream.showText(empleado.getPuesto());
            contentStream.newLine();
            contentStream.showText(DateUtil.formatDefault(empleado.getFechaIngreso()));
            contentStream.newLine();
            contentStream.showText(empleado.getRfc());
            contentStream.newLine();
            contentStream.showText(empleado.getNssImss());
            contentStream.newLine();
            contentStream.showText(empleado.getEnfermedad());
            String incidentesLaborales = empleado.getIncidentesLaborales();
            BreakText breakText = new BreakText();
            List<String> lines = breakText.getLines(incidentesLaborales, 12, PDType1Font.TIMES_ROMAN, page.getMediaBox().getWidth() - 170);
            lines.forEach(line -> {
                try {
                    contentStream.newLine();
                    contentStream.showText(line);
                } catch (IOException ex) {
                }
            });
            switch (lines.size()) {
                case 0:
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                case 1:
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                case 2:
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                default:
                    break;
            }
            String cursosCapacitacion = empleado.getCursosCapacitacion();
            breakText = new BreakText();
            lines = breakText.getLines(cursosCapacitacion, 12, PDType1Font.TIMES_ROMAN, page.getMediaBox().getWidth() - 170);
            lines.forEach(line -> {
                try {
                    contentStream.newLine();
                    contentStream.showText(line);
                } catch (IOException ex) {
                }
            });
            switch (lines.size()) {
                case 0:
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                case 1:
                    contentStream.newLine();
                    contentStream.showText("");
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                case 2:
                    contentStream.newLine();
                    contentStream.showText("");
                    break;
                default:
                    break;
            }
            contentStream.newLine();
            String datosAdicionales = empleado.getObservaciones();
            breakText = new BreakText();
            lines = breakText.getLines(datosAdicionales, 12, PDType1Font.TIMES_ROMAN, page.getMediaBox().getWidth() - 170);
            lines.forEach(line -> {
                try {
                    contentStream.newLine();
                    contentStream.showText(line);
                } catch (IOException ex) {
                }
            });
            contentStream.endText();

            // Imagen de empleado
            String dirFoto = PathUtil.getImagesPath() + empleado.getRutaFoto();
            PDImageXObject pdFoto = PDImageXObject.createFromFile(dirFoto, document);
            contentStream.drawImage(pdFoto, 420, 520, 150, 150);

            contentStream.close();
        }
        document.save(ruta);
        document.close();
    }
}
