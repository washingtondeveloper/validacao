package br.com.wdcode.validacao.file.type;

import java.util.List;

import br.com.wdcode.validacao.file.GeneratorFile;
import br.com.wdcode.validacao.file.service.CSV;
import br.com.wdcode.validacao.file.service.XML;
import br.com.wdcode.validacao.model.Cliente;

public enum TypeFile {
	
	XML {
		@Override
		public GeneratorFile getInstanceFile(List<Cliente> clientes) {
			return new XML(clientes);
		}
	},
	CSV {
		@Override
		public GeneratorFile getInstanceFile(List<Cliente> clientes) {
			return new CSV(clientes);
		}
	};
	
	public abstract GeneratorFile getInstanceFile(List<Cliente> clientes);
}
