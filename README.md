# Projet Domain Specific Language rhythmML

## Équipe

* Théo Foray
* Florian Juroszek
* Laura Lopez

## Organisation du repository

* [Kernel](https://github.com/FlorianJuroszek/rhythmML/tree/master/kernel) : le kernel en Java utilisé pour le DSL. 
* [DSL Groovy](https://github.com/FlorianJuroszek/rhythmML/tree/master/dsl) : le projet Groovy qui contient le DSL.
* [DomainModel](https://github.com/FlorianJuroszek/rhythmML/blob/master/doc/lab2.mdj) : le dossier où vous pouvez retrouver le diagramme de classe qui représente le *domain model*.

## Générer le fichier MIDI et jouer la musique

#### À partir d'un script Groovy
Dans **DSL Groovy**, déposer le script (dans le dossier dédié de préférence). Lancer le projet avec en argument le chemin vers ce script. Le fichier MIDI est généré, le morceau joué et les notes affichées dans la console.

PS: Ne pas oublier de lancer la commande `mvn clean install` à partir du projet **Kernel**.
