package com.idigi.helpers;

import com.idigi.entity.User;
import com.idigi.entity.UserDTO;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class Converter {
    public void objectToXML(UserDTO userDTO) throws JAXBException, FileNotFoundException {
        JAXBContext contextObj = JAXBContext.newInstance(UserDTO.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshallerObj.setProperty(Marshaller.JAXB_FRAGMENT,true);
        marshallerObj.marshal(userDTO, new FileOutputStream(Constants.XML_PATH));
    }
    public UserDTO entityToDTO(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setContactNumber(user.getContactNumber());
        return userDTO;
    }
    public User dTOToEntity(UserDTO userDTO){
        User user= new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailId(userDTO.getEmailId());
        user.setContactNumber(userDTO.getContactNumber());
        return user;
    }
}
