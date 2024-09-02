package com.belajar.restapi;

import com.belajar.restapi.dto.AddressDto;
import com.belajar.restapi.dto.ProfileData;
import com.belajar.restapi.models.entities.Address;
import com.belajar.restapi.models.entities.Comment;
import com.belajar.restapi.models.entities.Profile;
import com.belajar.restapi.models.entities.User;
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
		return new ModelMapper();
	}
}
