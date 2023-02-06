let vorigeWoche = document.getElementById("previousWeek");
let naechsteWoche = document.getElementById("nextWeek");
let kw = parseInt($('#weekCounter').val(), 10);
var currentYear = new Date().getFullYear();
var updatedYear = parseInt(localStorage.getItem('updatedYear')) || currentYear;

document.getElementById("yearCounter").value = updatedYear;

vorigeWoche.addEventListener("click", decrement);
naechsteWoche.addEventListener("click", increment);

function decrement(){
    console.log("Decrement")
    if(kw > 1){
        kw = kw - 1;
        let wnr = kw.toString();
        document.getElementById("weekCounter").value = wnr;
        window.location.href = '/admin/kalender' + wnr;
    }
    else{
        document.getElementById("weekCounter").value = "52";
        updatedYear = updatedYear - 1;
        localStorage.setItem('updatedYear', updatedYear)
        document.getElementById("yearCounter").value = updatedYear.toString();
        window.location.href = '/admin/kalender52';
    }
}
function increment(){
    console.log("Increment")
    if(kw < 52){
        kw = kw + 1;
        let wnr = kw.toString();
        document.getElementById("weekCounter").value = wnr;
        window.location.href = '/admin/kalender' + wnr;
    }
    else{
        document.getElementById("weekCounter").value = "1";
        updatedYear = updatedYear + 1;
        localStorage.setItem('updatedYear', updatedYear)
        document.getElementById("yearCounter").value = updatedYear.toString();
        window.location.href = '/admin/kalender1';
    }
}