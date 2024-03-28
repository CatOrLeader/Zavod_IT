package innohackatons.zavod_it.controller;

import innohackatons.zavod_it.db.repository.TenderRepository;
import innohackatons.zavod_it.db.repository.jpa.JpaTenderRepository;
import innohackatons.zavod_it.dto.TenderDto;
import innohackatons.zavod_it.service.TenderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TenderController {
    private final TenderService icetradeService;
//    private final JpaTenderRepository repository;

    @GetMapping(value = {"/tenders", "/"})
    public String listTenders(Model model) {
        List<TenderDto> tenders = icetradeService.findAllTenders().get();
//        repository.add(tenders);
        model.addAttribute("tenders", tenders);
        return "tenders-list";
    }

//    @GetMapping("/tenders/search")
//    public String searchCar(@RequestParam(value = "query") String query, Model model) {
//        List<TenderDto> tenderDtos = tenderService.searchTenders(query);
//        model.addAttribute("tenders", tenderDtos);
//
//        return "tenders-list";
//    }
//
//    @GetMapping("/tenders/{tenderId}")
//    public String tenderDetails(@PathVariable("tenderId") String tenderId, Model model) {
//        TenderDto tenderDto = tenderService.findTenderById(tenderId);
//        model.addAttribute("tender", tenderDto);
//        return "tenders-details";
//    }
}
