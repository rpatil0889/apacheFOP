package com.idigi.config;

import com.idigi.helpers.Constants;
import org.apache.fop.apps.FopFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

@Configuration
public class FOPConfig {

    @Bean
    public FopFactory fopFactory(){
        return FopFactory.newInstance(new File(".").toURI());
    }

    @Bean
    public Transformer transformer() throws TransformerConfigurationException {
        return TransformerFactory.newInstance().newTransformer(new StreamSource(new File(Constants.XSL_PATH)));
    }
}
