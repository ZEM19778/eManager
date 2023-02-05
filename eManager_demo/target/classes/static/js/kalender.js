// Add event listener for "Letzte Woche" button

function vorigeWoche() {
    console.log("Ich will nicht mehr");
    var currentWeek = moment().startOf("week");
    // TODO: Add code to change displayed week to previous week
    // code to subtract one week from the current week
    var previousWeek = currentWeek.subtract(1, "week");
    // and refresh the calendar with the updated week
    $.ajax({
        url: "/admin/kalender",
        type: "GET",
        data: {week: previousWeek},
        success: function (data){
            updateCalendar(data);
        }
    })
};

function updateCalendar(termine){
    $("#kalender td").empty();
    for(var i = 0; i < termine.length; i++){
        var termin = termine[i];
        var tag = new Date(termin.datum)
        var tableData = $("#kalender td").filter(function (){
            return $(this).data("day").getDate() === tag.getDate();
        });
        tableData.append("<p>" + termin.bezeichnung + "</p>")
    }
}
// Add event listener for "Nächste Woche" button
document.getElementById("nextWeek").addEventListener("click", function() {
    // TODO: Add code to change displayed week to next week
});