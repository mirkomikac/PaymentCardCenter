package com.tim2.pcc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim2.pcc.repository.BankRepository;
import com.tim2.pcc.service.BankaService;

@Service
public class BankaServiceImpl implements BankaService{

	@Autowired
	private BankRepository bankRepository;
	
	@Override
	public String findBankBySwiftKod(String swiftCode) {
		String url = bankRepository.findBankBySwiftKod(swiftCode.substring(0, 3));
		return url;
	}
	
}
