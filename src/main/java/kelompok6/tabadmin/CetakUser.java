package kelompok6.tabadmin;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.*;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import kelompok6.lib.koneksii;

public class CetakUser {

    public void generateReport(List<Map<String, ?>> data, String outputFilePath) {
        try {
            // Create JasperDesign
            JasperDesign jasperDesign = new JasperDesign();
            jasperDesign.setName("DynamicReport");
            jasperDesign.setPageWidth(595);
            jasperDesign.setPageHeight(842);
            jasperDesign.setColumnWidth(555);
            jasperDesign.setLeftMargin(20);
            jasperDesign.setRightMargin(20);
            jasperDesign.setTopMargin(20);
            jasperDesign.setBottomMargin(20);
            jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

            // Add Fields
            String[] fields = { "id", "nama", "username", "password", "email", "alamat" };
            for (String field : fields) {
                JRDesignField jrField = new JRDesignField();
                jrField.setName(field);
                jasperDesign.addField(jrField);
            }

            // Title Band
            JRDesignBand titleBand = new JRDesignBand();
            titleBand.setHeight(50);
            JRDesignStaticText titleText = new JRDesignStaticText();
            titleText.setText("Laporan pengguna");
            titleText.setX(0);
            titleText.setY(10);
            titleText.setWidth(555);
            titleText.setHeight(30);
            titleText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
            titleBand.addElement(titleText);
            jasperDesign.setTitle(titleBand);

            // Header Band
            JRDesignBand headerBand = new JRDesignBand();
            headerBand.setHeight(30);
            int x = 0;

            String[] headers = { "ID", "Nama", "Username", "Password", "Email", "Alamat" };
            for (int i = 0; i < headers.length; i++) {
                JRDesignStaticText headerText = new JRDesignStaticText();
                headerText.setText(headers[i]);
                headerText.setX(x);
                headerText.setY(0);
                headerText.setWidth(70);
                headerText.setHeight(30);
                headerText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
                headerText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
                headerText.setBackcolor(Color.BLUE);
                headerText.setForecolor(Color.WHITE);
                headerText.setMode(ModeEnum.OPAQUE);

                headerText.getLineBox().getPen().setLineWidth(0.5f);

                headerBand.addElement(headerText);
                x += 70; // Adjust width for each column
            }
            jasperDesign.setColumnHeader(headerBand);

            // Detail Band
            JRDesignBand detailBand = new JRDesignBand();
            detailBand.setHeight(20);
            x = 0;

            for (String field : fields) {
                JRDesignTextField textField = new JRDesignTextField();
                textField.setX(x);
                textField.setY(0);
                textField.setWidth(70);
                textField.setHeight(20);
                textField.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
                textField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
                textField.setStretchType(StretchTypeEnum.NO_STRETCH); // Use StretchTypeEnum instead

                textField.setExpression(new JRDesignExpression("$F{" + field + "}"));
                textField.getLineBox().getPen().setLineWidth(0.5f);

                detailBand.addElement(textField);
                x += 70; // Adjust width for each column
            }
            ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);

            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

            // Parameters
            Map<String, Object> parameters = new HashMap<>();

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to PDF
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(outputFilePath));

            System.out.println("Laporan berhasil disimpan di: " + outputFilePath);

            // Open the PDF
            File pdfFile = new File(outputFilePath);
            if (pdfFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Gagal membuka file PDF.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CetakUser reportGenerator = new CetakUser();
        List<Map<String, ?>> data = reportGenerator.fetchData();
        String outputFilePath = "CetakLaporanUser.pdf";
        reportGenerator.generateReport(data, outputFilePath);
    }

    List<Map<String, ?>> fetchData() {
        List<Map<String, ?>> data = new ArrayList<>();
        String sql = "SELECT id, nama, username, password, email, alamat FROM user";

        try (Connection con = koneksii.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getString("id"));
                row.put("nama", rs.getString("nama"));
                row.put("username", rs.getString("username"));
                row.put("password", rs.getString("password"));
                row.put("email", rs.getString("email"));
                row.put("alamat", rs.getString("alamat"));
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
