package com.blog;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	/*
	 * ModelMapper is a maven library which is used for the conversion of entities object to DTO and vice-versa.
	 * */
	
	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper modelMapper() {
		System.out.println("in model mapper bean creation");
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT) // only MATCHING prop names n data types
																						// between src n dest will be
																						// transferred , during the
																						// mapping
				.setPropertyCondition(Conditions.isNotNull());// only non null properties will be transferred from src
																// --> dest , during the mapping
		return modelMapper;//configured model mapper instance reted to SC

	}

}
