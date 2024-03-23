//package innohackatons.zavod_it.controller;
//
//import innohackatons.zavod_it.dto.TenderDto;
//import innohackatons.zavod_it.service.TenderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class TenderController {
//    private TenderService tenderService;
//
//    @Autowired
//    public TenderController(TenderService tenderService) {
//        this.tenderService = tenderService;
//    }
//
//    @GetMapping("/tenders")
//    public String listCars(Model model) {
//        List<TenderDto> tenders = new ArrayList<>();
//        model.addAttribute("tenders", tenders);
//        return "tenders-list";
//    }
//}
