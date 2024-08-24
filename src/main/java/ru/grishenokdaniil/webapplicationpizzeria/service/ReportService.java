package ru.grishenokdaniil.webapplicationpizzeria.service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Order;
import ru.grishenokdaniil.webapplicationpizzeria.repository.OrderRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public byte[] generateOrderReport() throws IOException {
        List<Order> orders = orderRepository.findAll();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Использование стандартного шрифта Helvetica
        PdfFont font = PdfFontFactory.createFont("Helvetica", "Cp1251", true);
        document.setFont(font);

        // Заголовок отчета
        document.add(new Paragraph("Отчет по заказам").setFontSize(18).setHorizontalAlignment(HorizontalAlignment.CENTER));

        // Таблица с данными по заказам
        Table table = new Table(new float[]{1, 2, 2});
        table.addHeaderCell(new Paragraph("Номер заказа").setFont(font));
        table.addHeaderCell(new Paragraph("Кол-во товара").setFont(font));
        table.addHeaderCell(new Paragraph("Итоговая стоимость").setFont(font));

        for (Order order : orders) {
            int itemCount = order.getOrderItems().size();
            double totalPrice = order.getTotalPrice();

            table.addCell(new Paragraph(order.getId().toString()).setFont(font));
            table.addCell(new Paragraph(String.valueOf(itemCount)).setFont(font));
            table.addCell(new Paragraph(String.valueOf(totalPrice)).setFont(font));
        }

        double totalSum = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        table.addCell(new Paragraph("Итоговая сумма").setFont(font));
        table.addCell(new Paragraph("").setFont(font));
        table.addCell(new Paragraph(String.valueOf(totalSum)).setFont(font));

        document.add(table);

        // Добавление графиков
        document.add(new Paragraph("\nГрафики").setFontSize(16).setHorizontalAlignment(HorizontalAlignment.CENTER));

        // График распределения сумм заказов
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Order order : orders) {
            dataset.addValue(order.getTotalPrice(), "Сумма", order.getId().toString());
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Распределение сумм заказов",
                "Номер заказа",
                "Сумма",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(chartOut, barChart, 500, 300);
        Image chartImage = new Image(ImageDataFactory.create(chartOut.toByteArray()));
        chartImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(chartImage);

        // График распределения количества товаров в заказах
        DefaultCategoryDataset itemCountDataset = new DefaultCategoryDataset();
        for (Order order : orders) {
            itemCountDataset.addValue(order.getOrderItems().size(), "Количество", order.getId().toString());
        }
        JFreeChart itemCountChart = ChartFactory.createBarChart(
                "Распределение количества товаров в заказах",
                "Номер заказа",
                "Количество товаров",
                itemCountDataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ByteArrayOutputStream itemCountChartOut = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(itemCountChartOut, itemCountChart, 500, 300);
        Image itemCountChartImage = new Image(ImageDataFactory.create(itemCountChartOut.toByteArray()));
        itemCountChartImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(itemCountChartImage);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
