package com.pfms.Personal_Finance_Management.controller;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pfms.Personal_Finance_Management.model.Category;
import com.pfms.Personal_Finance_Management.model.Goal;
import com.pfms.Personal_Finance_Management.model.Piechart;
import com.pfms.Personal_Finance_Management.model.TransactionDetails;
import com.pfms.Personal_Finance_Management.model.User;
import com.pfms.Personal_Finance_Management.model.Wallet;
import com.pfms.Personal_Finance_Management.repository.CategoryRepository;
import com.pfms.Personal_Finance_Management.repository.GoalRepository;
import com.pfms.Personal_Finance_Management.repository.PiechartRepository;
import com.pfms.Personal_Finance_Management.repository.TransRepository;
import com.pfms.Personal_Finance_Management.repository.UserRepository;
import com.pfms.Personal_Finance_Management.repository.WalletRepository;
import com.pfms.Personal_Finance_Management.service.CsvService;
import com.pfms.Personal_Finance_Management.service.GoalService;
import com.pfms.Personal_Finance_Management.service.WalletService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.common.record.Record;

@RestController
@RequestMapping("/wallet")
public class TransController {
	
	@Autowired
	private TransRepository transRepo;
	@Autowired
	private CsvService transService;
	@Autowired
	private WalletRepository walletRepo;
	@Autowired
	private WalletService walletService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private GoalRepository goalRepo;
	@Autowired
	private GoalService goalService;
	@Autowired
	private PiechartRepository pieRepo;
	
	@PostMapping("/importCsv")
	public String uploadData(@RequestParam("file") MultipartFile file,@RequestParam String email) throws Exception {
		//to receive the file from input and extract the records
		List<TransactionDetails> transList = new ArrayList<>();
		//receive file stream
		InputStream inputStream = file.getInputStream();
		//creat the parser for working with csv
		CsvParserSettings settings= new CsvParserSettings();
		//to remove first column
		settings.setHeaderExtractionEnabled(true);
		//create acsv parser
		CsvParser parser =new CsvParser(settings);
		//use the parser and get the details from input stream
		List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
		parseAllRecords.forEach(record -> {
		TransactionDetails trans = new TransactionDetails();
		User u=userRepository.findByEmail(email);
			trans.setDate(record.getString("date"));
			trans.setCategory(record.getString("category"));
			trans.setBank_name(record.getString("bank_name"));
			trans.setAmount(Integer.parseInt(record.getString("amount")));
			trans.setUid(u.getUser_id());
			transList.add(trans);
		});
		transRepo.saveAll(transList);
		return "uploaded";
	}
	
	@GetMapping("/exportCsv") 
	public ResponseEntity<Resource> getFile() {
		    String filename = "transactions.csv";
		    InputStreamResource file = new InputStreamResource(transService.load());

		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		  }
	

	@PostMapping("/transaction/add")
	public Map<String, String> addTransaction(@RequestBody TransactionDetails tr, @RequestParam String email) {
		System.out.println(email);
		User u=userRepository.findByEmail(email);
		System.out.println(u.getUser_id());
		String name=tr.getBank_name();
		System.out.println(name);
		//TransactionDetails trans = new TransactionDetails();

		Wallet wal = walletRepo.findByIdAndName(u.getUser_id(),name);
		System.out.println(tr.getCategory());


		Integer compValue= Double.compare(tr.getAmount(), wal.getWallet_amount());
		if(compValue < 0) {

			Double amount= walletService.setAmount(tr.getCategory(),tr.getAmount(),wal.getWallet_amount());
			wal.setWallet_amount(amount);
			System.out.println(wal.getWallet_amount());
			walletRepo.save(wal);
			tr.setUid(u.getUser_id());
			transRepo.save(tr);
			Map<String,String> resp = goalService.updateGoal(tr.getCategory(),u.getUser_id(),tr.getAmount());
			
			return resp;
			}
		else {
			Map<String,String> r=new HashMap<String,String>();
			r.put("message","No Sufficient Funds");
			return r ;
		}
	}
		
	@GetMapping("/wallet_transactions")
	public List<TransactionDetails> getAll(@RequestParam String wallet_name, @RequestParam String email){
		
		User u =userRepository.findByEmail(email);
		
///		Wallet w =walletRepo.findByIdAndName(u.getUser_id(),wallet_name);
		
		return transRepo.findByIdAndName(u.getUser_id(),wallet_name);
	}
	
	
	@GetMapping("/transaction_all")
	public List<TransactionDetails> getTransactions(@RequestParam String walletName){
		
		return transRepo.findAll();
	}
	
	
	@GetMapping("/transaction/top")
	public List<TransactionDetails> getTop3(@RequestParam String email){
		User u =userRepository.findByEmail(email);
		return transRepo.getTop3(u.getUser_id());
	}
	
	
	@PostMapping("/addcategory")
	public Category addCategory(@RequestBody Category category,@RequestParam String email) {
		
		User u=userRepository.findByEmail(email);
		category.setUid(u.getUser_id());
		return categoryRepo.save(category);
	}
	
	@GetMapping("/getcategory")
	public List<String> getCategories(@RequestParam String email){
		
		User u=userRepository.findByEmail(email);
		//System.out.println(u.getUser_id());
		return categoryRepo.findAllById(u.getUser_id());
		
		
	}
	@GetMapping("/pichart")
	public List<Piechart> piechart(@RequestParam String wallet_name,@RequestParam String email) {

		User u=userRepository.findByEmail(email);
		List<Object[]> data = transRepo.getData(wallet_name,u.getUser_id());
//		Piechart pi = new Piechart();
		
		List<Piechart> pidata =pieRepo.findAll();
		for (int i=0; i<data.size(); i++){
			Piechart pi = new Piechart();
			Object[] row= data.get(i);
			pi.setname((String)row[0]);
			pi.setvalue((BigDecimal)row[1]);
			pidata.add(pi);
		}

		return pidata;
	}

	
	@PostMapping("/all_pieData")
	public List<Piechart> pieData(@RequestParam String fDate,@RequestParam String dDate,@RequestParam String email) {

		User u=userRepository.findByEmail(email);
		List<Object[]> data = transRepo.getdataByDate(fDate,dDate,u.getUser_id());
//		Piechart pi = new Piechart();
		
		List<Piechart> pidata =pieRepo.findAll();
		for (int i=0; i<data.size(); i++){
			Piechart pi = new Piechart();
			Object[] row= data.get(i);
			pi.setname((String)row[0]);
			pi.setvalue((BigDecimal)row[1]);
			pidata.add(pi);
		}

		return pidata;
	}
	
	

}
