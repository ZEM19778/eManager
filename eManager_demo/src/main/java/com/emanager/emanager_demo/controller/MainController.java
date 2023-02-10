package com.emanager.emanager_demo.controller;

import com.emanager.emanager_demo.model.*;
import com.emanager.emanager_demo.repository.TermineRepository;
import com.emanager.emanager_demo.service.*;
import com.emanager.emanager_demo.utility.Temporals;
import com.lowagie.text.DocumentException;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.Console;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;
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

    Temporals temporals = new Temporals();

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
        model.addAttribute("temporals", temporals);
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
        model.addAttribute("temporals", temporals);
        return "homepageUser";
    }

    @GetMapping("/user/urlaub")
    public String urlaubuser(Model model) {
        List<Urlaub> listUrlaub = urlaubService.getAllUrlaub();
        model.addAttribute("listUrlaub", listUrlaub);
        model.addAttribute("temporals", temporals);
        return "urlaubsehenuser";
    }



    @GetMapping("/user/urlaubErstellen")
    public String urlauberstellen(Model model) {

        Urlaub urlaub = new Urlaub();
        model.addAttribute("urlaub", urlaub);
        model.addAttribute("temporals", temporals);

        return "urlaubErstellen";
    }

    @GetMapping("/user/urlaubloeschen/{id}")
    public String deleteurlaub(@PathVariable(value = "id") long id, Model model) {
        this.urlaubService.deleteUrlaubById(id);
        model.addAttribute("temporals", temporals);
        return "redirect:/user/urlaub";
    }

    @PostMapping("/user/saveUrlaub")
    public String saveUrlaub(Urlaub urlaub, Model model) {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        urlaub.setBeantragtMitarbeiter(username);

        urlaubService.saveUrlaub(urlaub);
        model.addAttribute("temporals", temporals);
        return "redirect:/user/urlaub";
    }


    @GetMapping("/admin/showFormForUpdateUrlaub/{id}")
    public String showFormForUpdateUrlaub(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Urlaub urlaub = urlaubService.getUrlaubById(id);
        model.addAttribute("urlaub", urlaub);
        model.addAttribute("temporals", temporals);
        return "updateUrlaub";
    }


    @GetMapping("/admin/kalender{wochennummer}/{yearCounter}")
    public String kalenderAdmin(@PathVariable(value = "wochennummer") int wochennummer, @PathVariable(name="yearCounter") int yearCounter, Model model) {
        List<Termin> terminListe = termineService.getAllTermine();
        LocalDate now = LocalDate.now();
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate currentMonday = now.with(weekFields.weekOfWeekBasedYear(), wochennummer).with(DayOfWeek.MONDAY).withYear(yearCounter);
        List<LocalDate> weekDays = IntStream.range(0, 7)
                .mapToObj(i -> currentMonday.plusDays(i))
                .collect(Collectors.toList());
        List<Termin> gefiltert = new ArrayList<>();
        for(Termin t : terminListe){
            if(t.getDatum().getYear() == yearCounter){
                gefiltert.add(t);
            }
        }
        Collections.sort(terminListe, new Comparator<Termin>() {
            @Override
            public int compare(Termin o1, Termin o2) {
                return o1.getBeginn().compareTo(o2.getBeginn());
            }
        });
        System.out.println(gefiltert);
        model.addAttribute("listTermine", gefiltert);
        model.addAttribute("temporals", temporals);
        model.addAttribute("weekDays", weekDays);
        model.addAttribute("yearcounter", yearCounter);
        return "kalenderAdmin";
    }

    @GetMapping("/admin/terminErstellen")
    public String termin(Model model) {
        Termin termin = new Termin();
        model.addAttribute("termin", termin);
        model.addAttribute("temporals", temporals);
        return "terminErstellen";
    }


    @GetMapping("/admin/deletetermin/{id}")
    public String deletetermin(@PathVariable(value = "id") long id, Model model) {
        this.termineService.deleteTerminById(id);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/kalender" + temporals.wochenNummer + "/" + temporals.jahr;
    }

    @PostMapping("/admin/saveTermin")
    public String saveTermin(Termin termin, Model model) {
        //if(termin.get)
        termineService.saveTermin(termin);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/kalender" + temporals.wochenNummer + "/" + temporals.jahr;
    }

    @PostMapping("/admin/saveUrlaub")
    public String saveUpdateUser(Urlaub urlaub, Model model) {
        //if(termin.get)
        urlaubService.saveUrlaub(urlaub);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/home";
    }


    @GetMapping("/user/kalender{wochennummer}/{yearCounter}")
    public String kalenderUser(@PathVariable(value = "wochennummer") int wochennummer, @PathVariable(name="yearCounter") int yearCounter, Model model) {
        List<Termin> terminListe = termineService.getAllTermine();
        LocalDate now = LocalDate.now();
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate currentMonday = now.with(weekFields.weekOfWeekBasedYear(), wochennummer).with(DayOfWeek.MONDAY).withYear(yearCounter);
        List<LocalDate> weekDays = IntStream.range(0, 7)
                .mapToObj(i -> currentMonday.plusDays(i))
                .collect(Collectors.toList());
        List<Termin> mitarbeiterTermine = new ArrayList<>();
        for(Termin t : terminListe){
            if(Objects.equals(t.getBetrifft(), "Mitarbeiter" )|| Objects.equals(t.getBetrifft(), "Alle")){
                if(t.getDatum().getYear() == yearCounter){
                    mitarbeiterTermine.add(t);
                }
            }
        }
        Collections.sort(terminListe, new Comparator<Termin>() {
            @Override
            public int compare(Termin o1, Termin o2) {
                return o1.getBeginn().compareTo(o2.getBeginn());
            }
        });
        System.out.println(mitarbeiterTermine);
        model.addAttribute("listTermine", mitarbeiterTermine);
        model.addAttribute("temporals", temporals);
        model.addAttribute("weekDays", weekDays);
        model.addAttribute("yearcounter", yearCounter);
        return "kalenderUser";
    }


    @GetMapping("admin/userverwaltung")
    public String userverwaltung(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("temporals", temporals);
        return "userverwaltung";
    }


    @GetMapping("admin/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("temporals", temporals);
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
        model.addAttribute("temporals", temporals);
        return "updateuser";
    }

    @GetMapping("/admin/wochenzettel")
    public String wochenzettel(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("temporals", temporals);
        return "wochenzettel";
    }

    @GetMapping("/admin/wochenzettel/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException{
        List<Dienste> alleDienste = diensteService.getAllDienste();
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=wochenzettel_"+currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);
        //Filterung nach Wochennummer
        List<Dienste> gefilterteDienste = new ArrayList<>();
        int kw = temporals.wochenNummer;
        WeekFields weekFields = WeekFields.of(Locale.GERMAN);
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(weekFields.weekOfWeekBasedYear(), kw).with(DayOfWeek.MONDAY);
        LocalDate end = now.with(weekFields.weekOfWeekBasedYear(), kw).with(DayOfWeek.SUNDAY);
        for(Dienste d : individuelleDienste){
            Instant instant = d.getDatumvon().toInstant();
            LocalDate datum = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            if(datum.compareTo(start) >= 0 && datum.compareTo(end) <= 0){
                gefilterteDienste.add(d);
            }
        }
        //Sortierung
        Collections.sort(gefilterteDienste, new Comparator<Dienste>() {
            @Override
            public int compare(Dienste o1, Dienste o2) {
                return o1.getDatumvon().compareTo(o2.getDatumvon());
            }
        });
        //PDF-Erstellung
        UserPDFExporter exporter = new UserPDFExporter(gefilterteDienste);
        individuelleDienste = new ArrayList<>();
        exporter.export(response);
    }

    @GetMapping("/admin/wochenzettelView/")
    public String wochenzettelView(@RequestParam("user") String username,  Model model) {
        individuelleDienste = diensteService.findDiensteByMitarbeiterLike(username);
        model.addAttribute("listDienste",individuelleDienste);
        model.addAttribute("temporals", temporals);
        return "wochenzettelView";
    }

    @GetMapping("/admin/deleteuser/{id}")
    public String deleteuser(@PathVariable (value = "id") long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String eingeloggt = authentication.getName();
        User user = service.getUserById(id);
        String mitarbeiter = user.getUsername();
        if(!Objects.equals(eingeloggt, mitarbeiter)){
            this.service.deleteUsereById(id);
            return "redirect:/admin/userverwaltung";
        }
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/userverwaltung";
    }


//////////////////////

    @GetMapping("user/diensteEintragen")
    public String diensteEintragen(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        List<Dienste> listDienste = diensteService.findDiensteByMitarbeiterLike(benutzername);
        model.addAttribute("listDienste",listDienste);
        model.addAttribute("temporals", temporals);

        return "diensteEintragen";
    }


    @GetMapping("user/diensteerstellen")
    public String diensteerstellen(Model model) {
        // create model attribute to bind form data
        Dienste dienste = new Dienste();
        model.addAttribute("dienste", dienste);

        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);

        model.addAttribute("temporals", temporals);
        return "diensteerstellen";
    }

    @PostMapping("/user/saveDienste")
    public String saveDienste( Dienste dienste, Model model) {
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
        diensteService.saveDienste(dienste);
        model.addAttribute("temporals", temporals);
        return "redirect:/user/diensteEintragen";
    }




    @GetMapping("/admin/nachrichtenerstellen")
    public String nachrichtenerstellen(Model model) {
        // create model attribute to bind form data
        Nachrichten nachrichten = new Nachrichten();
        model.addAttribute("nachrichten", nachrichten);
        model.addAttribute("temporals", temporals);
        return "nachrichtenerstellen";
    }


    @PostMapping("/admin/saveNachrichten")
    public String saveNachrichten( Nachrichten nachrichten, Model model) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        nachrichten.setsenderAdmin(username);

        nachrichtenService.saveNachrichten(nachrichten);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/home";
    }



    @GetMapping("/admin/deletenachricht/{id}")
    public String deletenachricht(@PathVariable (value = "id") long id, Model model) {
        this.nachrichtenService.deleteNachrichtById(id);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/home";
    }





    @GetMapping("/user/deletedienst/{id}")
    public String deletedienst(@PathVariable (value = "id") long id, Model model) {
        this.diensteService.deleteDienstById(id);
        model.addAttribute("temporals", temporals);
        return "redirect:/user/diensteEintragen";
    }



    //USER Passwortändern
    @GetMapping("/user/passwortaendern")
    public String passwortaendern(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        User user = service.finduserByMitarbeiterLike(benutzername);
        model.addAttribute("listUsers",user);
        model.addAttribute("temporals", temporals);
        return "passwortaendern";
    }

    @GetMapping("user/showNewPasswordForm")
    public String showNewPasswordForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("temporals", temporals);
        return "newuser";
    }

    @GetMapping("/user/showFormForUpdatePassword/{id}")
    public String showFormForUpdatePassword(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        model.addAttribute("temporals", temporals);
        return "updatepassword";
    }

    @PostMapping("/user/savePassword")
    public String savePassword( User user, Model model) {

        service.saveUser(user);
        model.addAttribute("temporals", temporals);
        return "redirect:/user/passwortaendern";
    }

    //ADMIN Passwortändern
    @GetMapping("/admin/passwortaendernadmin")
    public String adminpasswortaendern(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String benutzername = authentication.getName();
        User user = service.finduserByMitarbeiterLike(benutzername);
        model.addAttribute("listUsers",user);
        model.addAttribute("temporals", temporals);
        return "adminpasswortaendern";
    }

    @GetMapping("admin/adminshowNewPasswordForm")
    public String adminshowNewPasswordForm(Model model) {
        // create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("temporals", temporals);
        return "newuser";
    }

    @GetMapping("/admin/adminshowFormForUpdatePassword/{id}")
    public String adminshowFormForUpdatePassword(@PathVariable ( value = "id") long id, Model model) {

        // get employee from the service
        User user = service.getUserById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        model.addAttribute("temporals", temporals);
        return "adminupdatepassword";
    }

    @PostMapping("/admin/adminsavePassword")
    public String adminsavePassword( User user, Model model) {

        service.saveUser(user);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/passwortaendernadmin";
    }


    @GetMapping("/admin/baustelle")
    public String baustelle(Model model) {
        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);
        model.addAttribute("temporals", temporals);
        return "baustellesehen";
    }


    @GetMapping("/admin/baustelleneu")
    public String baustelleneu(Model model) {
        // create model attribute to bind form data
        Baustelle baustelle = new Baustelle();
        model.addAttribute("baustelle", baustelle);
        model.addAttribute("temporals", temporals);
        return "baustelleneu";
    }

    @PostMapping("/admin/saveBaustelle")
    public String saveBaustelle(Baustelle baustelle, RedirectAttributes ra, Model model) {

        baustelleService.saveBaustelle(baustelle);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/baustelle";

    }

    @GetMapping("/admin/baustelleUpdate/{id}")
    public String baustelleUpdate(@PathVariable ( value = "id") long id, Model model) {

        Baustelle baustelle = baustelleService.getBaustelleById(id);

        model.addAttribute("baustelle", baustelle);
        model.addAttribute("temporals", temporals);
        return "baustelleupdate";
    }

    @GetMapping("/admin/baustelleDelete/{id}")
    public String baustelleDelete(@PathVariable(value = "id") long id, Model model) {
        this.baustelleService.deleteBaustelleById(id);
        model.addAttribute("temporals", temporals);
        return "redirect:/admin/baustelle";
    }


    @GetMapping("/user/showFormForUpdateDienst/{id}")
    public String showFormForUpdateDienst(@PathVariable(value = "id") long id, Model model) {

        Dienste dienste = diensteService.getDiensteById(id);
        model.addAttribute("dienste", dienste);

        List<Baustelle> listBaustelle = baustelleService.getAllBaustelle();
        model.addAttribute("listBaustelle", listBaustelle);
        model.addAttribute("temporals", temporals);
        return "updateDienst";
    }
}
