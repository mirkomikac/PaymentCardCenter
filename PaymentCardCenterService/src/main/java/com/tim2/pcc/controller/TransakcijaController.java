package com.tim2.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tim2.pcc.model.RezultatTransakcije;
import com.tim2.pcc.model.Transakcija;
import com.tim2.pcc.serviceimpl.BankaServiceImpl;

@RestController
@RequestMapping("/transakcija")
public class TransakcijaController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BankaServiceImpl bankaServiceImpl;
	
	@PostMapping("/proslediZahtev")
	public void proslediZahtev(@RequestBody Transakcija transakcija){
		System.out.println("[PCC] PROSLEDIZAHTEV USAO");
		String url = bankaServiceImpl.findBankBySwiftKod(transakcija.getPan());
		restTemplate.postForObject(url + "/api/placanje/proveriZahtev", transakcija, Void.class);
	}
	
	@PostMapping("/proslediOdgovor")
	public void proslediOdgovor(@RequestBody RezultatTransakcije rezultatTransakcije){
		String url = bankaServiceImpl.findBankBySwiftKod(rezultatTransakcije.getAcquirerSwiftCode());
		restTemplate.postForObject(url + "/api/placanje/zabeleziPodatke", rezultatTransakcije, Void.class);
	}
	
}
