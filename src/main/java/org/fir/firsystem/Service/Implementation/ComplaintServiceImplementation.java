package org.fir.firsystem.Service.Implementation;

import org.fir.firsystem.GenAi.GroqController;
import org.fir.firsystem.Model.AppUser;
import org.fir.firsystem.Model.Complaint;
import org.fir.firsystem.Model.Incidence;
import org.fir.firsystem.Model.Person;
import org.fir.firsystem.Repository.ComplaintRepository;
import org.fir.firsystem.Service.AppUserService;
import org.fir.firsystem.Service.ComplaintService;
import org.fir.firsystem.Service.IncidenceService;
import org.fir.firsystem.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImplementation implements ComplaintService {

    @Autowired
    private GroqController groqController;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    IncidenceService incidenceService;

    @Autowired
    private PersonService personService;

    @Override
    public Complaint saveComplaint(Complaint complaint) {
        System.out.println(complaint);
        Person savedAccused = personService.save(complaint.getAccused());
        Person savedVictim = personService.save(complaint.getVictim());
        //description
        String incidence =complaint.getIncidence().getDescription();
        String summary = groqController.callApi("Convert the given incident into a concise, grammatically correct, and legally appropriate description in formal language, suitable for the 'Incident Description' section of an FIR complaint. Respond with only the final description and nothing else."+" "+ incidence );
        Incidence incidence1 = complaint.getIncidence();
        incidence1.setDescription(summary);
        //crime category;
        String prompt = summary + "\n\n" +
                "Given the following list of crime categories:\n" +
                "[\n" +
                "  \"Cognizable Offenses\",\n" +
                "  \"Non-Cognizable Offenses\",\n" +
                "  \"Bailable Offenses\",\n" +
                "  \"Non-Bailable Offenses\",\n" +
                "  \"Compoundable Offenses\",\n" +
                "  \"Non-Compoundable Offenses\",\n" +
                "  \"Offenses against Women\",\n" +
                "  \"Offenses against Children\",\n" +
                "  \"Economic Offenses\",\n" +
                "  \"Cyber Crimes\",\n" +
                "  \"Drug Offenses\",\n" +
                "  \"Environmental Offenses\",\n" +
                "  \"Traffic Offenses\",\n" +
                "  \"Property Offenses\",\n" +
                "  \"Terrorism-related Offenses\",\n" +
                "  \"White-collar Crimes\",\n" +
                "  \"Corruption Offenses\",\n" +
                "  \"Fraudulent Practices\",\n" +
                "  \"Domestic Violence Offenses\",\n" +
                "  \"Sexual Harassment Offenses\",\n" +
                "  \"Human Trafficking Offenses\",\n" +
                "  \"Intellectual Property Crimes\",\n" +
                "  \"Hate Crimes\",\n" +
                "  \"Juvenile Offenses\",\n" +
                "  \"Organized Crime\",\n" +
                "  \"Money Laundering Offenses\",\n" +
                "  \"Forgery and Counterfeiting Offenses\",\n" +
                "  \"Alcohol-related Offenses\",\n" +
                "  \"Public Order Offenses\",\n" +
                "  \"Violation of Intellectual Property Rights\",\n" +
                "  \"Cyberbullying Offenses\",\n" +
                "  \"Religious Offenses\",\n" +
                "  \"Wildlife Crimes\",\n" +
                "  \"Labour Law Violations\",\n" +
                "  \"Immigration Offenses\"\n" +
                "]\n\n" +
                "Task:\n" +
                "1. Identify which categories best fit the provided summary.\n" +
                "2. only Return the identified categories as an array and nothing.\n" +
                "3. If no categories match, return [\"Not Identified\"].";
        String crimes= groqController.callApi(prompt);
        incidence1.setCrimetype(crimes);
        System.out.println(incidence1);
        Incidence saved_incidence = incidenceService.save(incidence1);
        //user will onyl send the username . based on which whole user object will be
        //taken out from db and attached with complaint.
        AppUser user = appUserService.findByUsername(complaint.getUser().getUsername());

        Complaint savedComplaint = new Complaint();





        //saving the complaint
        savedComplaint.setAccused(savedAccused);
        savedComplaint.setVictim(savedVictim);
        savedComplaint.setIncidence(saved_incidence);
        savedComplaint.setEvidenceLink(complaint.getEvidenceLink());
        savedComplaint.setStatus(complaint.getStatus());
        savedComplaint.setUser(user);

        return complaintRepository.save(savedComplaint);
    }


}
