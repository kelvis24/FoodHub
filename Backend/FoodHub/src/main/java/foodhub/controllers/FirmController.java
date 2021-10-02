package foodhub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import foodhub.database.*;

@RestController
public class FirmController {
	
	@Autowired
	FirmRepository firmRepository;

	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

    @GetMapping("/firms")
    public List<Firm> listFirms(Model model) {
    	return firmRepository.findAll();
    }

    @PostMapping(path = "/firms")
    public String createFirm(@RequestBody Firm firm) {
    	if (firm == null)
    		return failure;
    	Firm sameEmail = firmRepository.findByUsername(firm.getUsername());
    	if (sameEmail != null)
    		return failure;
    	firmRepository.save(firm);
    	return success;
    }
}
