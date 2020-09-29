package adsd.semester1.zorgapp.controller;

import adsd.semester1.zorgapp.exception.ResourceNotFoundException;
import adsd.semester1.zorgapp.model.Patient;
import adsd.semester1.zorgapp.repository.PatientRepository;
import adsd.semester1.zorgapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = {"","/"})
    public String getAllPatients(Model model) {
        List<Patient> listPatients = patientService.listAll();
        model.addAttribute("listPatients", listPatients);

        return "patient/overview";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        return "patient/new_patient";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("patient") Patient patient) {
        patientService.save(patient);

        return "redirect:/patients";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("patient/edit_patient");
        Patient patient = patientService.get(id);
        mav.addObject("patient", patient);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
            patientService.delete(id);
            return "redirect:/patients";
    }

//    @GetMapping("/patients/{id}")
//    public ResponseEntity < Patient > getPatientById(@PathVariable(value = "id") Long patientId)
//            throws ResourceNotFoundException {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//        return ResponseEntity.ok().body(patient);
//    }
//
//    @GetMapping("patients/bmi/{id}")
//    public ResponseEntity < Double > getPatientBmi(@PathVariable(value = "id") Long patientId)
//            throws ResourceNotFoundException {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//        return ResponseEntity.ok().body(patient.getBmi());
//    }
//
//    @PostMapping("/patients")
//    public Patient createPatient(@Valid @RequestBody Patient patient) {
//        return patientRepository.save(patient);
//    }
//
//    @PutMapping("/patients/{id}")
//    public ResponseEntity < Patient > updatePatient(@PathVariable(value = "id") Long patientId,
//                                                      @Valid @RequestBody Patient patientDetails) throws ResourceNotFoundException {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//
//        patient.setLastName(patientDetails.getLastName());
//        patient.setFirstName(patientDetails.getFirstName());
//        final Patient updatedPatient = patientRepository.save(patient);
//        return ResponseEntity.ok(updatedPatient);
//    }
//
//    @DeleteMapping("/patients/{id}")
//    public Map < String, Boolean > deletePatient(@PathVariable(value = "id") Long patientId)
//            throws ResourceNotFoundException {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
//
//        patientRepository.delete(patient);
//        Map < String, Boolean > response = new HashMap < > ();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
}