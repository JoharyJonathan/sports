# 🏅 Sports — E-commerce sportif

**Sports** est un projet d'application web e-commerce consacré à l'univers du sport. Il vise à proposer une plateforme permettant aux utilisateurs de découvrir des produits sportifs à travers une interface moderne, avec une architecture séparant le frontend, le backend métier et les services API complémentaires.

Le projet s'appuie sur trois technologies principales : **Vue.js**, **Java** et **FastAPI (Python)**.

## 🎯 Objectifs du projet

- Développer une application e-commerce spécialisée dans les produits sportifs.
- Concevoir une interface web interactive et accessible.
- Mettre en place un backend pour gérer la logique métier et les données.
- Séparer les responsabilités entre l'interface utilisateur, le backend principal et les services API complémentaires.
- Explorer l'intégration de mécanismes de recommandation de produits.

## 🛠️ Stack technique

| Technologie | Rôle |
|---|---|
| **Vue.js** | Développement du frontend |
| **Java** | Langage du backend |
| **Jersey (JAX-RS) 3.1.0** | Développement des API REST |
| **Eclipse Jetty 11.0.14** | Serveur web et exécution de l'application Java |
| **Maven** | Gestion des dépendances et compilation |
| **MongoDB** | Base de données NoSQL |
| **Morphia 2.3.7** | Mapping objet-document pour MongoDB |
| **Redis / Jedis 5.0.2** | Gestion du cache ou d'autres données en mémoire |
| **FastAPI (Python)** | Développement de services API complémentaires |

### Architecture

Le projet repose sur une architecture séparant les responsabilités :

- **Frontend :** Vue.js pour l'interface utilisateur.
- **Backend :** Java avec Jersey et JAX-RS pour les API REST.
- **Données :** MongoDB, avec Morphia pour le mapping objet-document.
- **Cache :** Redis, accessible depuis Java via Jedis.
- **Services complémentaires :** FastAPI pour les fonctionnalités développées en Python.

D'autres bibliothèques sont utilisées ou prévues, notamment pour l'authentification JWT, la sécurisation des mots de passe et l'intégration des paiements avec Stripe.


## 🏗️ Architecture du projet

Le dépôt est organisé autour de trois dossiers principaux :

```text
sports/
├── front/       # Application frontend Vue.js
├── services/    # Services complémentaires / API
├── sport/       # Backend Java
└── README.md
```

### Frontend — Vue.js

Le frontend constitue la partie visible de l'application. Il permet aux utilisateurs d'interagir avec la plateforme et de consulter les produits sportifs.

### Backend — Java

Le backend principal est destiné à centraliser la logique métier et à gérer les échanges de données nécessaires au fonctionnement de l'application.

### Services API — FastAPI

La couche FastAPI permet de développer des services complémentaires en Python. Elle pourra notamment accueillir des mécanismes de recommandation de produits selon les fonctionnalités effectivement implémentées.

## 🚀 Installation et démarrage

Les instructions précises dépendent des outils de build et de la configuration de chaque partie du projet.

### 1. Cloner le dépôt

```bash
git clone https://github.com/JoharyJonathan/sports.git
cd sports
```

### 2. Installer et démarrer le frontend

Se placer dans le dossier `front/`, puis consulter le fichier `package.json` pour identifier le gestionnaire de paquets et les scripts disponibles.

### 3. Démarrer le backend Java

Se placer dans le dossier `sport/`, puis consulter les fichiers de configuration du projet afin d'identifier le système de build utilisé et les éventuelles dépendances nécessaires.

### 4. Démarrer les services FastAPI

Se placer dans le dossier `services/`, puis consulter les fichiers Python et les dépendances du projet pour identifier la commande de démarrage appropriée.

> **Prérequis :** les versions requises de Node.js, Java et Python, ainsi que les éventuelles variables d'environnement, restent à documenter selon la configuration réelle du dépôt.

## 🗺️ Évolutions envisagées

- Amélioration de l'expérience utilisateur.
- Enrichissement des fonctionnalités e-commerce.
- Intégration de recommandations de produits, si cette fonctionnalité est retenue.
- Amélioration de l'architecture et de la communication entre les services.
- Renforcement des tests et de la qualité du code.

## 👨‍💻 Auteur

**Johary Jonathan**

- GitHub : [@JoharyJonathan](https://github.com/JoharyJonathan)
- Dépôt du projet : [sports](https://github.com/JoharyJonathan/sports)

---

*Projet de développement web autour du commerce en ligne et de l'univers du sport.*