package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TenderController {
    private TenderService tenderService;

    @Autowired
    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping(value = {"/tenders", "/"})
    public String listTenders(Model model) {
        List<TenderDto> tenders = tenderService.findAllTenders();
        model.addAttribute("tenders", tenders);
        return "tenders-list";
    }

    @GetMapping("/tenders/search")
    public String searchCar(@RequestParam(value = "query") String query, Model model) {
        List<TenderDto> tenderDtos = tenderService.searchTenders(query);
        model.addAttribute("tenders", tenderDtos);

        return "tenders-list";
    }

    @GetMapping("/tenders/{tenderId}")
    public String tenderDetails(@PathVariable("tenderId") String tenderId, Model model) {
        TenderDto tenderDto = tenderService.findTenderById(tenderId);
        model.addAttribute("tender", tenderDto);
        return "tenders-details";
    }
}
