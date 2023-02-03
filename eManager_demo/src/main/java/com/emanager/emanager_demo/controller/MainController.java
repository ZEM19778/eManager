package com.emanager.emanager_demo.controller;

import com.emanager.emanager_demo.model.*;
import com.emanager.emanager_demo.repository.TermineRepository;
import com.emanager.emanager_demo.service.*;
import com.emanager.emanager_demo.utility.Temporals;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.bytebuddy.matcher.ElementMatchers.is;

@Controller
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private UrlaubServiceIn urlaubService;

    @Autowired
    private DiensteServiceIn diensteService;

    @Autowired
    private NachrichtenServiceIn nachrichtenService;

    @Autowired
    private TermineServiceIn termineService;

    @Autowired
    private BaustelleServiceIn baustelleService;

    @Autowired
    private TermineRepository termineRepository;

    private List<Dienste> individuelleDienste;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin_login";
    }

    @GetMapping("/exception")
    public String getAccessDenied() {
        return "/exception";
    }

    @GetMapping("/admin/home")
    public String homepageAdmin(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten", listNachrichten);

        List<Urlaub> listUrlaub = urlaubService.getAllUrlaub();
        model.addAttribute("listUrlaub", listUrlaub);
        return "homepageAdmin";
    }


    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user_login";
    }

    @GetMapping("/user/home")
    public String user(Model model) {
        List<Nachrichten> listNachrichten = nachrichtenService.getAllNachrichten();
        model.addAttribute("listNachrichten", listNachrichten);
        return "homepageUser";
    }

    @GetMapping("/user/urlaub")
    public String urlaubuser(Model model) {
        List<Urlaub> listUrlaub = urlaubService.getAllUrlaub();
        model.addAttribute("listUrlaub", listUrlaub);
        return "urlaubsehenuser";
    }



    @GetMapping("/user/urlaubErstellen")
    public String urlauberstellen(Model model) {

        Urlaub urlaub = new Urlaub();
        model.addAttribute("urlaub", urlaub);

        return "urlaubErstellen";
    }

    @GetMapping("/user/urlaubloeschen/{id}")
    public String deleteurlaub(@PathVariable(value = "id") long id) {
        this.urlaubService.deleteUrlaubById(id);
        return "redirect:/user/urlaub";
    }

    @PostMapping("/user/saveUrlaub")
    public String saveUrlaub(Urlaub urlaub) {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        urlaub.setBeantragtMitarbeiter(username);

        urlaubService.saveUrlaub(urlaub);
        return "redirect:/user/urlaub";
    }


    @GetMapping("/admin/showFormForUpdateUrlaub/{id}")
    public String showFormForUpdateUrlaub(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Urlaub urlaub = urlaubService.getUrlaubById(id);
        model.addAttribute("urlaub", urlaub);
        return "updateUrlaub";
    }

    @GetMapping("/admin/kalender")
    public String kalenderAdmin(Model model) {
        List<Termin> listTermine = termineService.getAllTermine();
        LocalDate weekReference = LocalDate.now();
        LocalDate currentMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        List<LocalDate> weekDays = IntStream.range(0, 7)
                .mapToObj(i -> currentMonday.plusDays(i))
                .collect(Collectors.toList());
        Temporals t = new Temporals();

        model.addAttribute("listTermine", listTermine);
        model.addAttribute("temporals", t);
        model.addAttribute("weekDays", weekDays);
        return "kalenderAdmin";
    }

    //@GetMapping("/admin/kalender/{weekNumber}")
    //public List<Termin> getTermineForPreviousWeek(@PathVariable int weekNumber){
    //    return "/admin/kalender";
    //}

    @GetMapping("/admin/terminErstellen")
    public String termin(Model model) {

        Termin termin = new Termin();
        model.addAttribute("termin", termin);

        return "terminErstellen";
    }


    @GetMapping("/admin/deletetermin/{id}")
    public String deletetermin(@PathVariable(value = "id") long id) {
        this.termineService.deleteTerminById(id);
        return "redirect:/admin/kalender";
    }

    @PostMapping("/admin/saveTermin")
    public String saveTermin(Termin termin) {
        //if(termin.get)
        termineService.saveTermin(termin);
        return "redirect:/admin/kalender";
    }

    @PostMapping("/admin/saveUrlaub")
    public String saveUpdateUser(Urlaub urlaub) {
        //if(termin.get)
        urlaubService.saveUrlaub(urlaub);
        return "redirect:/admin/home";
    }


    @GetMapping("/user/kalender")
    public String kalenderUser(Model model) {

        List<Termin> listTermine = termineService.getAllTermine();
        model.addAttribute("listTermine", listTermine);

        return "kalenderUser";
    }


    @GetMapping("admin/userverwaltung")
    public String userverwaltung(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "userverwaltung";
    }


    @GetMapping("admin/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(User user, RedirectAttributes ra) {
        // save User to database
        service.saveUser(user);

        return "redirect:/admin/userverwaltung";
    }


    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        User user = service.getUserById(id);
        model.addAttribute("user", user);
        return "updateuser";
    }

    @GetMapping("/admin/wochenzettel")
    public String wochenzettel(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "wochenzettel";
    }

    @GetMapping("/admin/wochenzettel/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=wochenzettel_"+currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);

        UserPDFExporter exporter = new UserPDFExporter(individuelleDienste);
        individuelleDienste = new ArrayList<>();
        exporter.export(response);
    }

    @GetMapping("/admin/wochenzettelView/")
    public String wochenzettelView(@RequestParam("user") String username,  Model model) {
        individuelleDienste = diensteService.findDiensteByMitarbeiterLike(username);
        model.addAttribute("listDienste",individuelleDienste);
        return "wochenzettelView";
    }

    //Update User

    //@RequestMapping(method = RequestMethod.PUT, value = "/admin/updateUser{id}")
    //public void updateuser(@RequestBody User user) {
    //  service.updateUser(user);
    //}

    //@PutMapping(path = "/admin/updateUser")
    //public @ResponseBody String updateUser(@RequestBody User user, @PathVariable Long id ) {
    //    User existingData = service.getUserById(id);
    //   existingData.setUsername(user.getUsername());
    //   existingData.setRole(user.getRole());

    // return "redirect:/admin/userverwaltung";
    //  }

    @GetMapping("/admin/deleteuser/{id}")
    public String deleteuser(@PathVariable (value = "id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String eingeloggt = authentication.getName();
        User user = service.getUserById(id);
        String mitarbeiter = user.getUsername();
        if(!Objects.equals(eingeloggt, mitarbeiter)){
            this.service.deleteUsereById(id);
            return "redirect:/admin/userverwaltung";
        }
        return "redirect:/admin/userverwaltung";
    }


//////////////////////

    @GetMapping("user/diensteEintragen")
    public String diensteEintragen(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        List<Dienste> listDienste = diensteService.findDiensteByMitarbeiterLike(benutzername);
        model.addAttribute("listDienste",listDienste);


        return "diensteEintragen";
    }


    @GetMapping("user/diensteerstellen")
    public String diensteerstellen(Model model) {
        // create model attribute to bind form data
        Dienste dienste = new Dienste();
        model.addAttribute("dienste", dienste);

        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);

        return "diensteerstellen";
    }

    @PostMapping("/user/saveDienste")
    public String saveDienste( Dienste dienste) {
        String username;
        LocalTime von = dienste.getZeitvon();
        LocalTime bis = dienste.getZeitbis();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        dienste.setMitarbeiter(username);
        Duration diff = Duration.between(von, bis);

        Long d = diff.toMinutes();
        int stunde = 60;
        double stunden = (double) d /  stunde;
        float stundenzahl = (float) stunden;


        if(bis.isAfter(von)) {
            dienste.setDauer(stundenzahl);
        }
        else {
            return "exceptionTime";
        }




       /*
        if(stundenzahl > 0){
            throw new RuntimeException(" Zweite Zeit darf nicht vor der ersten sein " );
        }
        else{
            dienste.setDauer(stundenzahl);
        }


       String vonneu = (String) dienste.getZeitvon().toString();
        int compare = dienste.getZeitvon().compareTo(dienste.getZeitvon());

        System.out.println(compare);'*/




        diensteService.saveDienste(dienste);

        return "redirect:/user/diensteEintragen";
    }




    @GetMapping("/admin/nachrichtenerstellen")
    public String nachrichtenerstellen(Model model) {
        // create model attribute to bind form data
        Nachrichten nachrichten = new Nachrichten();
        model.addAttribute("nachrichten", nachrichten);
        return "nachrichtenerstellen";
    }


    @PostMapping("/admin/saveNachrichten")
    public String saveNachrichten( Nachrichten nachrichten) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        nachrichten.setsenderAdmin(username);

        nachrichtenService.saveNachrichten(nachrichten);
        return "redirect:/admin/home";
    }



    @GetMapping("/admin/deletenachricht/{id}")
    public String deletenachricht(@PathVariable (value = "id") long id) {
        this.nachrichtenService.deleteNachrichtById(id);
        return "redirect:/admin/home";
    }





    @GetMapping("/user/deletedienst/{id}")
    public String deletedienst(@PathVariable (value = "id") long id) {
        this.diensteService.deleteDienstById(id);

        return "redirect:/user/diensteEintragen";
    }



    //USER Passwortändern
    @GetMapping("/user/passwortaendern")
    public String passwortaendern(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        User user = service.finduserByMitarbeiterLike(benutzername);
        model.addAttribute("listUsers",user);

        return "passwortaendern";
    }

    @GetMapping("user/showNewPasswordForm")
    public String showNewPasswordForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @GetMapping("/user/showFormForUpdatePassword/{id}")
    public String showFormForUpdatePassword(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "updatepassword";
    }

    @PostMapping("/user/savePassword")
    public String savePassword( User user) {

        service.saveUser(user);

        return "redirect:/user/passwortaendern";
    }







    //ADMIN Passwortändern
    @GetMapping("/admin/passwortaendernadmin")
    public String adminpasswortaendern(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        User user = service.finduserByMitarbeiterLike(benutzername);
        model.addAttribute("listUsers",user);

        return "adminpasswortaendern";
    }

    @GetMapping("admin/adminshowNewPasswordForm")
    public String adminshowNewPasswordForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @GetMapping("/admin/adminshowFormForUpdatePassword/{id}")
    public String adminshowFormForUpdatePassword(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "adminupdatepassword";
    }

    @PostMapping("/admin/adminsavePassword")
    public String adminsavePassword( User user) {

        service.saveUser(user);

        return "redirect:/admin/passwortaendernadmin";
    }


    @GetMapping("/admin/baustelle")
    public String baustelle(Model model) {
        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);
        return "baustellesehen";
    }


    @GetMapping("/admin/baustelleneu")
    public String baustelleneu(Model model) {
        // create model attribute to bind form data
        Baustelle baustelle = new Baustelle();
        model.addAttribute("baustelle", baustelle);

        return "baustelleneu";
    }

    @PostMapping("/admin/saveBaustelle")
    public String saveBaustelle(Baustelle baustelle, RedirectAttributes ra) {

        baustelleService.saveBaustelle(baustelle);
        return "redirect:/admin/baustelle";

    }

    @GetMapping("/admin/baustelleUpdate/{id}")
    public String baustelleUpdate(@PathVariable ( value = "id") long id, Model model) {

        Baustelle baustelle = baustelleService.getBaustelleById(id);

        model.addAttribute("baustelle", baustelle);
        return "baustelleupdate";
    }

    @GetMapping("/admin/baustelleDelete/{id}")
    public String baustelleDelete(@PathVariable(value = "id") long id) {
        this.baustelleService.deleteBaustelleById(id);
        return "redirect:/admin/baustelle";
    }


    @GetMapping("/user/showFormForUpdateDienst/{id}")
    public String showFormForUpdateDienst(@PathVariable(value = "id") long id, Model model) {

        Dienste dienste = diensteService.getDiensteById(id);
        model.addAttribute("dienste", dienste);

        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);
        return "updateDienst";
    }
}
