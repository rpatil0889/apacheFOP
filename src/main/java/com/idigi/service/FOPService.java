package com.idigi.service;


import com.idigi.entity.User;
import com.idigi.entity.UserDTO;
import com.idigi.helpers.Constants;
import com.idigi.helpers.Converter;
import com.idigi.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
@Slf4j
public class FOPService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Converter converter;
    @Autowired
    private FopFactory fopFactory;
    @Autowired
    private Transformer transformer;

    public UserDTO addUser(UserDTO userDTO){
        return converter.entityToDTO(userRepo.save(converter.dTOToEntity(userDTO)));
    }
    public UserDTO getUser(int id){
        return converter.entityToDTO(userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found with is "+id)));
    }
    public void generateWelcomePDF(int id) throws JAXBException, FileNotFoundException {

        converter.objectToXML(this.getUser(id));

        try {
            // Step 1: Set up the FOP factory -Autowired this step
//            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

            // Step 2: Set up output stream
            File pdfFile = new File(Constants.PDF_PATH);
            OutputStream out = new FileOutputStream(pdfFile);

            // Step 3: Construct FOP with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

            // Step 4: Set the XSL transformation source
            Source xslt = new StreamSource(new File(Constants.XSL_PATH));

            // Step 5: Set the input source for XML content
            Source xml = new StreamSource(new File(Constants.XML_PATH));

            // Step 6: Perform transformation and generate PDF
            Result result = new SAXResult(fop.getDefaultHandler());

            // Step 7: Prepare the XSL transformation
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer transformer = factory.newTransformer(new StreamSource(new File(Constants.XSL_PATH)));
            transformer.transform(xml, result);

            // Step 8: Close the output stream
            out.close();

            log.info("PDF generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
