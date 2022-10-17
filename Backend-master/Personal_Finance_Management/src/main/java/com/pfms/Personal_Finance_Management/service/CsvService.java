package com.pfms.Personal_Finance_Management.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.pfms.Personal_Finance_Management.model.TransactionDetails;
import com.pfms.Personal_Finance_Management.repository.TransRepository;

@Service
public class CsvService {
	
	@Autowired
	TransRepository transrepo;
	
	public ByteArrayInputStream load() {
	    List<TransactionDetails> transaction = transrepo.findAll();
	    
	    System.out.println(transaction);

	    ByteArrayInputStream in = transToCsv(transaction);
	    return in;
	  }
	
	 public static ByteArrayInputStream transToCsv(List<TransactionDetails> transaction) {
		    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
		        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
		      for (TransactionDetails trans : transaction) {
		        List<String> data = Arrays.asList(
		        		trans.getDate(),
		              trans.getCategory(),
		              trans.getBank_name(),
		              String.valueOf(trans.getAmount())
		            );
		        
		

		        csvPrinter.printRecord(data);
		      }

		      csvPrinter.flush();
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		    }
		  }
	  
}
