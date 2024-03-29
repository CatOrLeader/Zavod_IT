package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.db.TenderRepository;
import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TenderController {
    private final TenderService tenderProService;
    private final TenderRepository repository;

    @GetMapping(value = {"/tenders", "/"})
    public String listTenders(Model model) {
        List<TenderDto> tenders = tenderProService.findAllTenders().block();
        repository.add(tenders);
        model.addAttribute("tenders", tenders);
        return "tenders-list";
    }

    @GetMapping("/tenders/search")
    public String searchTender(@RequestParam(value = "query") String query, Model model) {
        List<TenderDto> tenderDto = repository.findByQuery(query);
        model.addAttribute("tenders", tenderDto);

        return "tenders-list";
    }

    @GetMapping("/tenders/{tenderId}")
    public String tenderDetails(@PathVariable("tenderId") String tenderId, Model model) {
        TenderDto tenderDto = repository.findById(tenderId).get();
        model.addAttribute("tender", tenderDto);
        return "tenders-details";
    }
}
