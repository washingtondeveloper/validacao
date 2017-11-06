package br.com.wdcode.validacao.file.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.com.wdcode.validacao.file.GeneratorFile;
import br.com.wdcode.validacao.model.Cliente;
import br.com.wdcode.validacao.utils.CSVUtils;

public class CSV implements GeneratorFile {
	
	private List<Cliente> clientes;

	public CSV(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public void criarArquivo() {
		try {
			String csvFile = "clientes.csv";
	        FileWriter writer = new FileWriter(csvFile);
	       
	        //Coluna principal para a listagem
	        CSVUtils.writeLine(writer, Arrays.asList("Nome", "Idade", "CPF"));
	        
	        //Listagem para as determinadas colunas
	        for(Cliente cliente : clientes) {
	        	 CSVUtils.writeLine(writer, Arrays.asList(cliente.getNome(), String.valueOf(cliente.getIdade()), cliente.getCpf()));
	        }
	        
	        writer.flush();
	        writer.close();
		}catch (IOException e) {
			// TODO: handle exception
		}
	}

}
