package com.pedromiranda.java_cli_app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
			processJsonInput(line);
		}
	}

	private void processJsonInput(String jsonInput) {
		try {
			List<Operation> trades = objectMapper.readValue(jsonInput, new TypeReference<>() {});
			Calculator calculator = new Calculator();
			List<TaxResult> results = calculator.calculateTaxes(trades);

			System.out.println(objectMapper.writeValueAsString(results));
		} catch (Exception e) {
			System.err.println("Erro ao processar JSON: " + e.getMessage());
		}
	}
}
