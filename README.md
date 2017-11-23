# vinkkari

[![Build Status](https://travis-ci.org/Aviledev/vinkkari.svg?branch=master)](https://travis-ci.org/Aviledev/vinkkari)
[![codecov](https://codecov.io/gh/Aviledev/vinkkari/branch/master/graph/badge.svg)](https://codecov.io/gh/Aviledev/vinkkari)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8149192fb4fa400dae8493295136abcf)](https://www.codacy.com/app/leevilehtonen/vinkkari?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Aviledev/vinkkari&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/dc809389-f3c9-4fbb-b200-90fe338bc21f)](https://codebeat.co/projects/github-com-aviledev-vinkkari-master)


[Backlog](https://docs.google.com/spreadsheets/d/1pEWCLGSSiPcI1rFv1Su9gxMdy0C1xjPz9IltO_ZW0Qw/edit?usp=sharing)

[Sovellus](https://vinkkari.herokuapp.com/)

[Definition of Done](https://github.com/Aviledev/vinkkari/blob/master/dod.md)

![Tietokantakaavio](https://github.com/Aviledev/vinkkari/blob/master/doc/database_diagram.svg)

Suorita sovellus komennolla _gradle run_, käynnistyy osoitteeseen http://localhost:8080

Testit suoritetaan komennolla _gradle test_

Parametrillä ```-Dspring.profiles.active=production``` voidaan syöttää haluttu ympäristö (tässä tapauksessa ```production```. Muita mahdollisia ovat ```development``` ja ```default```).
