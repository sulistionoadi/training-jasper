/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.helper;

import com.artivisi.virtualaccount.domain.Nasabah;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import java.util.List;
import javax.xml.crypto.Data;
import org.apache.velocity.VelocityContext;

/**
 *
 * @author adi
 */
public class DirectPrintHelper {

    private static Log LOGGER = LogFactory.getLog(DirectPrintHelper.class);
    private static Template templateDaftarNasabah;
    private static final VelocityEngine velocityEngine = new VelocityEngine();

    static {
        try {
            velocityEngine.setProperty("resource.loader", "classpath");
            velocityEngine.setProperty("classpath.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            velocityEngine.setProperty(
                    RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
                    "org.apache.velocity.runtime.log.Log4JLogChute");
            velocityEngine.setProperty("runtime.log.logsystem.log4j.logger",
                    DirectPrintHelper.class.getName());
            velocityEngine.init();
            
            templateDaftarNasabah = velocityEngine.getTemplate("daftar_nasabah.vm");


        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    private static void setupPrinterStruk(Writer writer) throws IOException {
        writer.write(EpsonCommand.RESET);
        writer.write(EpsonCommand.MODE_DRAFT);
        writer.write(EpsonCommand.FONT_SANS_SERIF);
        writer.write(EpsonCommand.CONDENSED_ON);
        writer.write(EpsonCommand.SIZE_12_CPI);
        writer.write(EpsonCommand.SPACING_9_72);
    }

    public static void printNasabah(List<Nasabah> listData, Writer writer) throws IOException {
        printWithBold(toStruk(listData, writer), writer, 0, 0, 0, 0,
                0, 0, 0);
    }

    public static void printWithBold(String data, Writer writer,
            Integer boldOn, Integer boldOff, Integer esc12On, Integer esc9On,
            Integer esc7On, Integer doubleWidthOn, Integer doubleWidthOff) throws IOException {

        setupPrinterStruk(writer);

        BufferedReader reader = new BufferedReader(new StringReader(data));

        String content;
        int counterBaris = 0;
        while ((content = reader.readLine()) != null) {
            counterBaris++;
            if (counterBaris == boldOn) {
                writer.write(EpsonCommand.BOLD_ON);
            }
            if (counterBaris == boldOff) {
                writer.write(EpsonCommand.BOLD_OFF);
            }
            if (counterBaris == esc12On) {
                writer.write(EpsonCommand.SPACING_12_72);
            }
            if (counterBaris == esc9On) {
                writer.write(EpsonCommand.SPACING_9_72);
            }
            if (counterBaris == esc7On) {
                writer.write(EpsonCommand.SPACING_7_72);
            }
            if (counterBaris == doubleWidthOn) {
                writer.write(EpsonCommand.DOUBLE_WIDTH_ON);
            }
            if (counterBaris == doubleWidthOff) {
                writer.write(EpsonCommand.DOUBLE_WIDTH_OFF);
            }
            writer.write(content);
            writer.write("\n");
        }

        writer.close();
    }

    public static String toStruk(List<Nasabah> listData, Writer writer) throws IOException {

        return convertToPrintableString(
                fillContextDaftarNasabah(listData, writer),
                templateDaftarNasabah);
    }

    private static VelocityContext fillContextDaftarNasabah(
            List<Nasabah> listData, Writer writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("tanggal", new Date());
        context.put("listData", listData);
        return context;
    }

    private static String convertToPrintableString(VelocityContext context,
            Template template) {
        try {
            StringWriter writer = new StringWriter();
            template.merge(context, writer);
            return writer.toString();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new IllegalStateException(ex);
        }
    }
}
