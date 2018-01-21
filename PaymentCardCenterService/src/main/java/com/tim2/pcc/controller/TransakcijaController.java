package com.tim2.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public Transakcija proslediZahtev(@RequestBody Transakcija transakcija){
		String url = bankaServiceImpl.findBankBySwiftKod(transakcija.getPan());
		return restTemplate.postForObject(url + "/placanje/proveriZahtev", transakcija, Transakcija.class);
	}
	
	@PostMapping()
	@ResponseBody
	public RezultatTransakcije proslediOdgovor(@RequestBody RezultatTransakcije rezultatTransakcije){
		//mican generise ACQ_ORDER_ID = SWIFT_KOD + ID, preko swift koda dolazim do acquirer banke
		return restTemplate.postForObject("url", rezultatTransakcije, RezultatTransakcije.class);
	}
	
}
