package com.sitco.api;

import com.sitco.api.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;


@SpringBootApplication

public class ApiApplication {
	public static void main(String[] args) {
		//SpringApplication.run(ApiApplication.class, args);
		var material = Material.builder()
				.materialType(new MaterialType(1, "MFC", "Melamine Faced Chipboard."))
				.brand(new Brand(1, "YIL", "Yildiz Entegre"))
				.decorNumber("VT509")
				.grainDirection(new GrainDirection(1,"YES", "Grain pattern along the long side of panel."))
				.thickness(BigDecimal.valueOf(18.00))
				.moistureType(new MoistureType(1, "NO", "Normal MFC"))
				.build();

		var panel = Panel.builder()
				.id(1L)
				.height(BigDecimal.valueOf(3660.00))
				.width(BigDecimal.valueOf(1830.00))
				.trimCutHeight(BigDecimal.valueOf(10.00))
				.trimCutWidth(BigDecimal.valueOf(10.00))
				.build();

		material.addPanel(panel);

		System.out.println(material);

	}
}
