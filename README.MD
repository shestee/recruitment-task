## Projekt demonstracyjny

Projekt demonstracyjny z zakresu tworzenia REST API.

## Treść zadania:
Utworzyć dwie klasy: Student oraz Nauczyciel. Nauczyciel może mieć wiele studentów oraz student może mieć wiele nauczycieli.
Nauczyciel powinien mieć pola: imię, nazwisko, wiek, email oraz przedmiot.
Student powinien mieć pola: imię, nazwisko, wiek, email oraz kierunek.

### Wymagania:
1. Obie klasy można tworzyć, usuwać i edytować. Dane powinny być walidowane: poprawny email, imię dłuższe od dwóch liter, wiek > 18.
2. Powinna być możliwość wyświetlenia wszystkich studentów oraz wszystkich nauczycieli (dwa endpointy, możliwość stronicowania i sortowania).
3. Dane można filtrować: wyszukać wszystkich studentów danego nauczyciela i odwrotnie.
4. Studentów oraz nauczycieli można wyszkiwać po imieniu i nazwisku.

### Technologie:
• Java 11 (lub wyższa)  
• Spring Boot  
• MySQL (lub inna z rodziny SQL)


Zastosowano bazę danych H2 tworzoną w pamięci. Możliwy dostęp do bazy poprzez konsolę: `http://localhost:8080/h2-console`

## Użycie
Udostępniono endpointy `/students` oraz `/teachers`.  

• pobieranie wszystkich studentów wraz z przypisanymi nauczycielami:  
    `GET /students`  

• pobieranie wszystkich studentów bez przypisanych nauczycieli:  
    `GET /students?noTeachers=`  

• pobieranie wszystkich nauczycieli wraz z przypisanymi studentami:  
    `GET /teachers`    

• pobieranie wszystkich nauczycieli bez przypisanych studentów:  
    `GET /teachers?noStudents=`

• pobieranie studenta / nauczyciela po imieniu i nazwisku:  
    `GET /students?firstname=&lastname=` (może zwrócić więcej niż jednego studenta)  
    `GET /teachers?firstname=&lastname=` (może zwrócić więcej niż jednego nauczyciela)  

• dodawanie studenta / nauczyciela:  
    `POST /students` ciało metody: JSON  
    `POST /teachers` ciało metody: JSON  

• edytowanie studenta / nauczyciela:  
    `PUT /students?id=` ciało metody: JSON; parametr: id studenta  
    `PUT /teachers?id=` ciało metody: JSON; parametr: id nauczyciela  

• usuwanie studenta / nauczyciela:  
    `DELETE /students?id=` ciało metody: JSON; parametr: id studenta  
    `DELETE /teachers?id=` ciało metody: JSON; parametr: id nauczyciela  

• przypisania nauczyciela do studenta :  
    `PUT /students?studentId=&teacherId=` parametry: id studenta, id nauczyciela  

Metody wyszukujące wszystkich studentów / nauczycieli domyślnie wyświetlają 10 wyników na stronie.