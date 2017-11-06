package br.com.wdcode.validacao.file.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.wdcode.validacao.file.GeneratorFile;
import br.com.wdcode.validacao.model.Cliente;

public class XML implements GeneratorFile {
	
	private List<Cliente> clientes;

	public XML(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	@Override
	public void criarArquivo() {
		try {
			XStream stream = new XStream(new DomDriver());
			stream.alias("cliente", Cliente.class);
			String clientexml = stream.toXML(clientes);

			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("clientes.xml"))) {
				writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+clientexml);
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}

}
