package com.example.pdfgenerator.controller;

import com.example.pdfgenerator.service.PDFGeneratorService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@Data
public class PDFExportController {
    private  final PDFGeneratorService pdfGeneratorService;
/**
 * @desc generate pdf
 * @route /pdf/generate
 * @access public
 * */
    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        /**@desc use it to name file */
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

/**@desc header - tells browser what kind of file we are returning*/
        String headerKey = "Content-Disposition";
        String headerValue  = "attchment; filename=pdf_"+currentDateTime +".pdf";
        response.setHeader(headerKey,headerValue);

        this.pdfGeneratorService.export(response);

    }
}
