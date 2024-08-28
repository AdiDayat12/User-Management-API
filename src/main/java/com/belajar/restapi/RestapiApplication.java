package com.belajar.restapi;

import com.belajar.restapi.dto.AddressDto;
import com.belajar.restapi.models.entities.Address;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<AddressDto, Address>() {
			@Override
			protected void configure() {
				map().setStreet(source.getStreet());
				map().setCity(source.getCity());
				map().setState(source.getState());
				map().setZipCode(source.getZipCode());
			}
		});
		return modelMapper;
	}
}
