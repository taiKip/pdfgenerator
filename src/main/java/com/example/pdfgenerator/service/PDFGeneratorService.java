package com.example.pdfgenerator.service;


import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@Service
public class PDFGeneratorService {
    //@desc we get http servlet response which we will attach a doc and send it back to  user
    public void export (HttpServletResponse response) throws IOException {
        //@desc create doc
//        Document document = new Document(PageSize.A4);
//
//        //@desc get the document and write it to the output stream of the response
//        PdfWriter.getInstance(document,response.getOutputStream());
////@desc open doc to style it and add content
//        String imgSrc = "\\images\\IMG_1603.jpg";
//
//        document.open();
//        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        fontTitle.setSize(18);
//
//
//        Paragraph paragraph = new Paragraph("This is a title.",fontTitle);
//        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
//        fontParagraph.setSize(12);
//
//        Paragraph paragraph2 = new Paragraph("This is a paragraph.",fontParagraph );
//        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
//
//        document.add(paragraph);
//        document.add(paragraph2);
//
//        document.close();

        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document document = new Document(pdf, PageSize.A4);
        PdfPage page = pdf.addNewPage();
        PdfCanvas canvas = new PdfCanvas(page);

        /**@desc get page dimensions*/

float pageWidth = page.getPageSize().getWidth();
float pageHeight = page.getPageSize().getHeight();
Color bgColor = new DeviceRgb(46,40,40);
        canvas.setFillColor(bgColor);
        canvas.rectangle(0, 0, pageWidth/4,pageHeight);
        canvas.fill();
ImageData imageData = ImageDataFactory.create("/Users/tarus/dev/pdfgenerator/images/IMG_1603.jpg");
Image image = new Image(imageData);
        //image.scaleAbsolute(200f,200f);
        image.scaleToFit(100f,100f);

        PdfCanvas circleCanvas = new PdfCanvas(page);

        circleCanvas.circle(50,700,200);

        circleCanvas.fill();


        document.add(image);
        document.close();
    }
}
