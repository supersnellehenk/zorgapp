package adsd.semester1.zorgapp.controller;

import adsd.semester1.zorgapp.model.Medicine;
import adsd.semester1.zorgapp.repository.MedicineRepository;
import adsd.semester1.zorgapp.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = {"", "/"})
    public String getAllMedicines(Model model) {
        List<Medicine> listMedicines = medicineService.listAll();
        model.addAttribute("listMedicines", listMedicines);

        return "medicine/overview";
    }

    @RequestMapping("/new")
    public String showNewMedicinePage(Model model) {
        Medicine medicine = new Medicine();
        model.addAttribute("medicine", medicine);

        return "medicine/new_medicine";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {
        medicineService.save(medicine);

        return "redirect:/medicines";
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView showEditMedicinePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("medicine/edit_medicine");
        Medicine medicine = medicineService.get(id);
        mav.addObject("medicine", medicine);

        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String deleteMedicine(@PathVariable(name = "id") int id) {
        medicineService.delete(id);
        return "redirect:/medicines";
    }
}