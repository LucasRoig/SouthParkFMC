/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var totalEpisode,
        nbEpSaison,
        nbTagST,
        nbTagSW,
        nbRandy,
        epParSaison;

$(document).ready(function () {
    $.get("/stat/mainData", function (data) {
        totalEpisode = data.totalEpisode;
        nbEpSaison = data.nbEpisode;
        nbTagST = data.nbTagST;
        nbTagSW = data.nbTagSW;
        nbRandy = data.nbRandy;
        epParSaison = data.epParSaison;
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(function () {
            var dataRandy = new google.visualization.DataTable();
            dataRandy.addColumn("string", "Personnage");
            dataRandy.addColumn("number", "nombreEp");
            dataRandy.addRows([["Avec Randy", nbRandy],["Sans Randy", (totalEpisode - nbRandy)]]);
            var option = {
                title : 'Présence de Randy Marsh dans tout les épisodes',
                slices: {1: {offset: 0.1}}
            };
            var chart = new google.visualization.PieChart(document.getElementById('RandyPie'));
            chart.draw(dataRandy, option);
        });
        google.charts.setOnLoadCallback(function () {
            var dataST = new google.visualization.DataTable();
            dataST.addColumn("string", "Saga");
            dataST.addColumn("number", "nombreEp");
            dataST.addRows([["Star Trek", nbTagST],["Star Wars", nbTagSW]]);
            var option = {
                title : 'Star Trek vs. Star Wars',
                slices: {1: {offset: 0.1}}
            };
            var chartST = new google.visualization.PieChart(document.getElementById('StarWars-vs-StarTrek'));
            chartST.draw(dataST, option);
        });
        google.charts.setOnLoadCallback(function () {
            var dataEp = new google.visualization.DataTable();
            dataEp.addColumn("number", "Saison");
            dataEp.addColumn("number", "Nombre d'Episode");
            dataEp.addRows(epParSaison);
            var option = {
                title : 'Nombre d épisode par saison',
            };
            var chartEp = new google.visualization.LineChart(document.getElementById('EpisodeLine'));
            chartEp.draw(dataEp, option);
        });
    });
});

