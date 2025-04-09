package poly.edu.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.Entity.ReportSoldProduct;
import poly.edu.Repository.CategoryRepository;
import poly.edu.Repository.OrderDetailRepository;


@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    OrderDetailRepository orderDetailRepo;
    @Autowired
    CategoryRepository categoryRepo;

    @GetMapping
    public String getMethodName(Model model) {
        List<ReportSoldProduct> reportResults = orderDetailRepo.reportSoldProduct();
        System.out.println(reportResults.size());
        model.addAttribute("ReportList", reportResults);
        return "Report.html";
    }

    @GetMapping("/getLoai")
    public String getMe(@PathVariable("id") Integer id, Model model) {
        List<ReportSoldProduct> reportResults = orderDetailRepo.reportSoldProduct();
        System.out.println(reportResults.size());
        model.addAttribute("ReportList", reportResults);
        model.addAttribute("productList", categoryRepo.findById(id));
        return "Report.html";
    }

}
