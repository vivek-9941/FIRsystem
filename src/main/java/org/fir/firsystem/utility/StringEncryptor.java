package org.fir.firsystem.utility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;

@Converter
public class StringEncryptor implements AttributeConverter<String, String> {

    private final AES256TextEncryptor encryptor;
    StringEncryptor(@Value("${ENCRYPTION_KEY}") String SECRET_KEY) {
        encryptor = new AES256TextEncryptor();
        encryptor.setPassword(SECRET_KEY);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(attribute == null)return null;
        return encryptor.encrypt(attribute) ;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
//🔄 Who Calls These Methods?
//These methods are automatically invoked by JPA/Hibernate when an entity is persisted to or loaded from the database.
//
//🧩 How It Works Internally
//1. convertToDatabaseColumn()
//🔁 When: Every time JPA writes the field value to the database.
//
//⚙️ Action: Encrypts the field.

//@Convert(converter = StringEncryptor.class)
//private String city;
//When saving this entity:

//Address address = new Address("MG Road", "Pune", "MH", "411001", "India");
//appUser.setAddress(address);
//userRepository.save(appUser);
//➡️ Hibernate will automatically call:

//String encrypted = convertToDatabaseColumn("Pune"); // result stored in DB
//2. convertToEntityAttribute()
//🔁 When: Every time JPA reads the field value from the database.
//⚙️ Action: Decrypts the field.