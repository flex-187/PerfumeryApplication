package ok.perfumery.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ok.perfumery.entity.Producer;
import ok.perfumery.service.ProducerService;

@Controller
@RequestMapping("/producers")
public class ProducerController {

	private ProducerService producerService;

	public ProducerController(ProducerService producerService) {
		this.producerService = producerService;
	}

	@GetMapping("")
	public String showProducersOverview(Model model) {
		model.addAttribute("producers",producerService.getAllProducers());
		return "overview";
	}
	
	@GetMapping("/{id}")
	public String showProducerDetails(@PathVariable int id, Model model) {
		model.addAttribute("producer",producerService.getProducerById(id));
		return "producerDetails";
	}

	@GetMapping("/new")
	public String showProducerForm(Model model) {
		model.addAttribute(new Producer());
		return "producerForm";
	}

	@PostMapping("/new")
	public String saveProducer(@ModelAttribute("producer") @Valid Producer producer, Errors errors) {
		
		if (errors.hasErrors()) {
			return "producerForm";
		}
		
		if (producer.getId() != 0) {
			producerService.updateProducer(producer);
		} else {
			producerService.createNewProducer(producer);
		}

		return "redirect:/";
	}
	
	@GetMapping("/{id}/edit")
	public String showFormForEditProduct(@PathVariable int id, Model model) {
		model.addAttribute("producer", producerService.getProducerById(id));
		return "producerForm";
	}

}
