package adsd.semester1.zorgapp.controller;

import adsd.semester1.zorgapp.model.Medicine;
import adsd.semester1.zorgapp.model.Patient;
import adsd.semester1.zorgapp.model.Weight;
import adsd.semester1.zorgapp.service.MedicineService;
import adsd.semester1.zorgapp.service.PatientService;
import adsd.semester1.zorgapp.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicineService medicineService;
    @Autowired
    private WeightService weightService;

    @RequestMapping(value = {"", "/"})
    public String getAllPatients(Model model) {
        List<Patient> listPatients = patientService.listAll();
        model.addAttribute("listPatients", listPatients);
        return "patient/overview";
    }

    @RequestMapping("/new")
    public String showNewPatientPage(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        return "patient/new_patient";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);

        return "redirect:/patients";
    }

    @RequestMapping("/{id}/edit")
    public ModelAndView showEditPatientPage(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("patient/edit_patient");
        Patient patient = patientService.get(id);
        mav.addObject("patient", patient);
        List<Medicine> listMedicines = medicineService.listAll();
        mav.addObject("listMedicines", listMedicines);
        return mav;
    }

    @RequestMapping("/{id}/delete")
    public String deletePatient(@PathVariable(name = "id") int id) {
        patientService.delete(id);
        return "redirect:/patients";
    }

    @RequestMapping(value = "/{id}/medicines/new", method = RequestMethod.POST)
    public String newMedicine(@PathVariable long id, @RequestParam int medicineId) {
        Patient patient = patientService.get(id);
        patient.addMedicine(medicineService.get(medicineId));
        patientService.save(patient);
        return "redirect:/patients/" + id + "/edit";
    }

    @RequestMapping("/{id}/medicines/{medicineId}/delete")
    public String deleteMedicine(@PathVariable(name = "id") long id, @PathVariable(name = "medicineId") int medicineId) {
        Patient patient = patientService.get(id);
        patient.removeMedicine(medicineId);
        patientService.save(patient);
        return "redirect:/patients/" + id + "/edit";
    }

    @RequestMapping(value = "/{id}/weight/new", method = RequestMethod.POST)
    public String newWeight(@PathVariable long id, @RequestParam double weightId) {
        Patient patient = patientService.get(id);
        var weight = new Weight();
        weight.setWeight(weightId);
        weight.setPatient(patient);
        weightService.save(weight);
        return "redirect:/patients/" + id + "/edit";
    }

    @RequestMapping("/{id}/weights/{weightId}/delete")
    public String deleteWeight(@PathVariable(name = "id") long id, @PathVariable(name = "weightId") int weightId) {
        Patient patient = patientService.get(id);
        patient.removeWeight(weightId);
        weightService.delete(weightId);
        return "redirect:/patients/" + id + "/edit";
    }
}