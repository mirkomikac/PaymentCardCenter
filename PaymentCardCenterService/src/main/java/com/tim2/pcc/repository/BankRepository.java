package com.tim2.pcc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tim2.pcc.model.Banka;

@Repository
public interface BankRepository extends JpaRepository<Banka, Long>{
	
	@Query("select b.url from Banka b where b.swiftCode = ?1")
	public String findBankBySwiftKod(String swiftCode);
	
}
