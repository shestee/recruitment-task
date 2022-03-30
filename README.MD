##Projekt demonstracyjny
###Cel:
Projekt demonstracyjny z zakresu REST API, stworzony w celu wzięcia udziału w procesie rekrutacyjnym.
###Treść zadania:
Utworzyć dwie klasy: Student oraz Nauczyciel. Nauczyciel może mieć wiele studentów oraz student może mieć wiele nauczycieli.
Nauczyciel powinien mieć pola: imię, nazwisko, wiek, email oraz przedmiot.
Student powinien mieć pola: imię, nazwisko, wiek, email oraz kierunek.

###Wymagania:
1. Obie klasy można tworzyć, usuwać i edytować. Dane powinny być walidowane: poprawny email, imię dłuższe od dwóch liter, wiek > 18.
2. Powinna być możliwość wyświetlenia wszystkich studentów oraz wszystkich nauczycieli (dwa endpointy, możliwość stronicowania i sortowania).
3. Dane można filtrować: wyszukać wszystkich studentów danego nauczyciela i odwrotnie.
4. Studentów oraz nauczycieli można wyszukiwać po imieniu i nazwisku.

###Technologie:
• Java 11 (lub wyższa)
• Spring Boot
• MySQL (lub inna z rodziny SQL)