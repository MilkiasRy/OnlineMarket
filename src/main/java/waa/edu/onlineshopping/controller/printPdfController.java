package waa.edu.onlineshopping.controller;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CartItemService;
import waa.edu.onlineshopping.service.CartService;
//import waa.edu.onlineshopping.service.UserService;
import waa.edu.onlineshopping.service.serviceImpl.ReportCreationService;


import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class printPdfController {
    @Autowired
    ReportCreationService reportCreationService;
       @Autowired
    private TemplateEngine templateEngine;
    private static final String DIRECTORY = "D:\\Marashi University\\MUM Assignment\\WAA\\Project\\onlineshopping";
    private static final String DEFAULT_FILE_NAME = "message.pdf";


    @GetMapping("/printPdf/{id}")
    public String getPdf(@PathVariable("id") Long id, HttpServletRequest request, Principal principal){


        final Context ctx = new Context();
        ctx.setVariable("report", reportCreationService.createReport(principal,id));

        StringWriter stringWriter = new StringWriter();
        templateEngine.process("report", ctx, stringWriter);

        String html = stringWriter.toString();

        try {

            OutputStream outputStream = new FileOutputStream(principal.getName()+".pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            try {
                renderer.createPDF(outputStream);
                System.out.println("PDF Successfully created");
            }
            catch (DocumentException documentException){
                System.out.println(documentException.getMessage());
            }
            outputStream.close();
        }
       catch (IOException ex){

       }
        return "forward:/download";
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> downloadFile2(
            Principal principal) throws IOException {

        //  MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        System.out.println("fileName: " + principal.getName()+".pdf");
        //  System.out.println("mediaType: " + mediaType);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Path path = Paths.get(DIRECTORY + "/" + principal.getName()+".pdf");
        byte[] data = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(MediaType.APPLICATION_PDF)
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
    }
}
