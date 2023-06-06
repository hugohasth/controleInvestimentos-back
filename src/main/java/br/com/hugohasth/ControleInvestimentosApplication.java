package br.com.hugohasth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.hugohasth.model.Ativo;
import br.com.hugohasth.model.Setor;
import br.com.hugohasth.repository.SetorRepository;

@SpringBootApplication
public class ControleInvestimentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleInvestimentosApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initDatabase(SetorRepository setorRepository) {
		return args -> {
			setorRepository.deleteAll();
			
			Setor s = new Setor();
			s.setNome("AÇÕES (BRASIL)");
			s.setPorcentagem(55.0);
			s.setValor(36075.49);
			Ativo a = new Ativo();
			a.setNome("VALE S.A.");
			a.setSetor(s);
			a.setSigla("VALE3");
			a.setTipo("AÇÃO");
			a.setSegmento("MINERAIS METÁLICOS");
			s.getAtivos().add(a);
			setorRepository.save(s);
			s = new Setor();
			s.setNome("AÇÕES (USA)");
			s.setPorcentagem(35.0);
			s.setValor(4550.83); 
			a = new Ativo();
			a.setNome("BERKSHIRE HATHAWAY INC.");
			a.setSetor(s);
			a.setSigla("NYSE:BRK.B");
			a.setTipo("STOCK");
			a.setSegmento("Conglomerado");
			s.getAtivos().add(a);
			a = new Ativo();
			a.setNome("AMAZON.COM INC.");
			a.setSetor(s);
			a.setSigla("NASDAQ:AMZN");
			a.setTipo("STOCK");
			a.setSegmento("Varejo");
			s.getAtivos().add(a);
			setorRepository.save(s);
		};
	}

}
