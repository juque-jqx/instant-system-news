# Localisation de la fonctionnalité demandée 
Le code propre à la partie de l'exercice se situe [ici](https://github.com/juque-jqx/instant-system-news/tree/main/features/news/src/main/java/com/example/news).

# Architecture
Pour ce projet, j'ai utilisé la Clean Architecture. Celle-ci permet d'avoir un code facilement maintenable, testable et évolutif.
Celle-ci est un standard, très bien documenté, ce qui facilite son adoption.
De plus, elle est adaptée à des projets de toute taille.

Pour l'implémenter rapidement, j'ai pris comme base un projet existant basé sur cette même architecture : https://github.com/aliahmedbd/WeatherApp-Android-Clean-Architecture-Jetpack-Compose-Kotlin-Hilt-Flow
Plus localement, j'ai utilisé du MVVM. De même, c'est un standard du domaine Android, poussé aussi par Google.

# Technologie
## Jetpack-Compose 
Type d'IHM actuellement poussé par Google, elle bénéficiera donc de son support. 
Il existe également le fork de Compose fait par JetBrain qui permet de réaliser des applications Desktop/Web avec un code majoritairement identique. 
Cela offre une perspective de création d'autres formats au projet avec une grande facilité (Avec KMP par exemple).

## Hilt
Bibliothèque d'injection de dépendance créée par Google. Celle-ci est particulièrement adaptée à Android et apporte une simplicité à l'usage de dagger.
La rendant compatible avec l'emploi de développeur Junior.
Son utilisation permet un découpage du code aisé et aide ainsi à garder une architecture maintenable, testable, modulaire et surtout réutilisable.
Toujours dans un esprit de facilitation, son emploi facilite aussi la concentration sur le besoin métier au niveau du code.

## Flow
Intégré par nature à Kotlin, cela nous permet de conduire des flux de données et les opérations sur ceux-ci. Le tout en étant adapté à des traitements asynchrones.
Comme Kotlin, n'étant pas spécifique à Android, on peut dès lors aussi s'en servir pour factoriser du code entre plusieurs plateformes.
Cela évite en plus de recourir à l'utilisation de bibliothèques supplémentaires comme RxKotlin.

## Retrofit
Pour pouvoir réaliser les échanges réseaux. Cette bibliothèque est éprouvée et est encore une fois recommandée par la communauté Android. 

# Problèmatiques rencontrées

J'ai ajouté la pagination avec la bibliothèque de Google propre à Compose.
N'ayant pas l'habitude de faire de la pagination avec une bibliothèque, cela m'a complexifié la réalisation des tests unitaires.
