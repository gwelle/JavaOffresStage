-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 24 Avril 2017 à 19:14
-- Version du serveur :  5.5.20-log
-- Version de PHP :  5.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetoffrestage`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `idPersonne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`idPersonne`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `domaineoffre`
--

CREATE TABLE IF NOT EXISTS `domaineoffre` (
  `numDomaineOffre` int(11) NOT NULL,
  `libDomaineOffre` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `domaineoffre`
--

INSERT INTO `domaineoffre` (`numDomaineOffre`, `libDomaineOffre`) VALUES
(1, 'Réseau'),
(2, 'Développement'),
(5, 'Comptabilité');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE IF NOT EXISTS `employe` (
  `idPersonne` int(11) NOT NULL,
  `numEntreprise` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `employe`
--

INSERT INTO `employe` (`idPersonne`, `numEntreprise`) VALUES
(27, NULL),
(28, NULL),
(30, NULL),
(11, 9),
(16, 10),
(21, 11),
(22, 12),
(23, 13),
(24, 14),
(26, 15),
(25, 16),
(31, 18),
(32, 19);

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE IF NOT EXISTS `entreprise` (
  `numEntreprise` int(11) NOT NULL,
  `nomEntreprise` varchar(25) DEFAULT NULL,
  `adresseEntreprise` varchar(25) DEFAULT NULL,
  `cpEntreprise` char(5) DEFAULT NULL,
  `villeEntreprise` varchar(25) DEFAULT NULL,
  `mailEntreprise` varchar(25) DEFAULT NULL,
  `telEntreprise` varchar(10) DEFAULT NULL,
  `secteurActivite` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `entreprise`
--

INSERT INTO `entreprise` (`numEntreprise`, `nomEntreprise`, `adresseEntreprise`, `cpEntreprise`, `villeEntreprise`, `mailEntreprise`, `telEntreprise`, `secteurActivite`) VALUES
(1, 'nomE', '1 rue du test', '10000', 'test', 'test@test.fr', '102030405', 'revendeur informatique'),
(2, 'test', 'test', '91000', 'test', 'test', '4050607', 'test'),
(3, 'hehe', 'hehe', '7000', 'hehe', 'hehe', '46070', 'hehe'),
(4, 'ggthjrh', 'rhrhrhr', '40005', 'rhrhrhr', 'efefefefef', '407270', 'egegegege'),
(7, 'test1', 'test1', '80404', 'test1', 'test1', '405050', 'test1'),
(8, 'test4', 'test2', '05215', 'test2', 'test2', '40505', 'test2'),
(9, 'test7', 'test7', '70700', 'test7', 'test7@test7.fr', '40404', 'test7'),
(10, 'test78', 'test78', '80808', 'test78', 'test78', '800', 'test78'),
(11, 'test85', 'test85', '91205', 'test85', 'test85', '280808', 'test85'),
(12, 'Entreprise employe1', '1 rue employe1', '80100', 'employe1', 'employe1@employe1.fr', '5008080', 'employe1'),
(13, 'employe2', 'employe2', '8001', 'employe2', 'employe2', '870080', 'employe2'),
(14, 'employe3', 'employe3', '7000', 'employe3', 'employe3', '70', 'employe3'),
(15, 'employe5', 'employe5', '80551', 'employe5', 'employe5@employe5.fr', '0102030405', '40g'),
(16, 'employe4', 'employe4', '90100', 'employe4', 'employe4@employe4.fr', '0102030405', 'employe4'),
(18, 'employe9', 'employe9', '90507', 'employe9', 'employe9@employe9.fr', '0102030405', 'employe9'),
(19, 'employe10', 'employe10', '70000', 'employe10', 'employe10@employe10.fr', '0102040506', 'employe10');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE IF NOT EXISTS `etudiant` (
  `idPersonne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`idPersonne`) VALUES
(13),
(14),
(15),
(17),
(18);

-- --------------------------------------------------------

--
-- Structure de la table `offrestage`
--

CREATE TABLE IF NOT EXISTS `offrestage` (
  `numOffreStage` int(11) NOT NULL,
  `numDomaineOffre` int(11) NOT NULL,
  `descriptifOffre` text,
  `libelleOffre` varchar(25) DEFAULT NULL,
  `dateDebutOffre` date DEFAULT NULL,
  `dureeOffre` int(11) DEFAULT NULL,
  `cheminOffre` varchar(25) DEFAULT NULL,
  `numEntreprise` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `offrestage`
--

INSERT INTO `offrestage` (`numOffreStage`, `numDomaineOffre`, `descriptifOffre`, `libelleOffre`, `dateDebutOffre`, `dureeOffre`, `cheminOffre`, `numEntreprise`) VALUES
(12, 2, 'test78', 'test78', '2017-04-27', 80, 'test78', 11),
(13, 1, 'test78', 'test78', '2017-04-27', 70, 'test78', 11),
(15, 5, 'employe10 employe10 employe10 employe10 employe10 employe10 employe10 employe10 employe10 employe10 employe10 employe10', 'offre1', '2017-04-27', 40, 'employe10', 19),
(16, 5, 'test78test78test78test78test78test78test78test78test78test78test78test78test78test78test78 test78 test78 test78 test78 test78 test78 test78 test78 ', 'test78', '2017-04-27', 40, 'test78', 9),
(17, 2, 'employe10 employe10employe10 ', 'offre1', '2017-04-28', 70, 'employe10', 19),
(18, 2, 'test70test70', 'test70', '2017-05-02', 480, 'test70', 19);

-- --------------------------------------------------------

--
-- Structure de la table `offrestageenregistree`
--

CREATE TABLE IF NOT EXISTS `offrestageenregistree` (
  `id` int(11) NOT NULL,
  `idEtudiant` int(11) NOT NULL,
  `idOffreStage` int(11) NOT NULL,
  `valideOffre` char(3) NOT NULL DEFAULT 'oui'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `offrestageenregistree`
--

INSERT INTO `offrestageenregistree` (`id`, `idEtudiant`, `idOffreStage`, `valideOffre`) VALUES
(1, 15, 15, ''),
(2, 14, 15, ''),
(3, 14, 12, ''),
(4, 13, 12, ''),
(5, 15, 17, ''),
(6, 15, 18, 'non');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE IF NOT EXISTS `personne` (
  `idPersonne` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `login` varchar(30) NOT NULL,
  `mdp` varchar(80) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`idPersonne`, `nom`, `prenom`, `login`, `mdp`) VALUES
(1, 'admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
(11, 'test1', 'test1', 'test1', '5a105e8b9d40e1329780d62ea2265d8a'),
(12, 'test3', 'test3', 'test4', '2f6c2404198add983753e94fc24e752f'),
(13, 'test', 'test', 'test', 'fb469d7ef430b0baf0cab6c436e70375'),
(14, 'test5', 'test5', 'test5', 'e3d704f3542b44a621ebed70dc0efe13'),
(15, 'test6', 'test6', 'test6', '4cfad7076129962ee70c36839a1e3e15'),
(16, 'test43', 'test43', 'test43', 'de8397f651cbb078e955067ca46df6b3'),
(17, 'test47', 'test47', 'test47', '168c34716cb6aabbe34ca310090c6e43'),
(18, 'test52', 'test52', 'test52', 'aaf1892a6026f15c11931d1e52341cff'),
(19, 'test88', 'test88', 'test88', '841f54c24fd24fb27f45377b2e941070'),
(21, 'test85', 'test85', 'test85', 'c39906c9308f0e3b9edabd1c67fc9b61'),
(22, 'employe1', 'employe1', 'employe1', '54a51d77ba883e4c77cf681bc70214e5'),
(23, 'employe2', 'employe2', 'employe2', 'db57a2c4455defb2052e690537e2663e'),
(24, 'employe3', 'employe3', 'employe3', '79ed2a63b8e20bc0a3e09e99c034bb27'),
(25, 'employe4', 'employe4', 'employe4', 'b9b1bd5733d461e05dea25f6c659ccbb'),
(26, 'employe5', 'employe5', 'employe5', '54387aed52eec034213ac723b21686fc'),
(27, 'employe6', 'employe6', 'employe6', 'bca39f59706ae54a3cf42997ee0efb62'),
(28, 'employe7', 'employe7', 'employe7', '6f6146b84fea01a95b87d91529d4006c'),
(30, 'employe8', 'employe8', 'employe8', 'df176623b81ba6caf4459b193cd9981d'),
(31, 'employe9', 'employe9', 'employe9', '8bb82b44be604625665a9ee4845ffc93'),
(32, 'employe10', 'employe10', 'employe10', '8aab37c955ae1ecdfa05fe6d18e46e40');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`idPersonne`);

--
-- Index pour la table `domaineoffre`
--
ALTER TABLE `domaineoffre`
  ADD PRIMARY KEY (`numDomaineOffre`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`idPersonne`),
  ADD KEY `EMP_ENTREPRISE_FK` (`numEntreprise`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`numEntreprise`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`idPersonne`);

--
-- Index pour la table `offrestage`
--
ALTER TABLE `offrestage`
  ADD PRIMARY KEY (`numOffreStage`),
  ADD KEY `FK_OffreStage_numEntreprise` (`numEntreprise`),
  ADD KEY `numDomaineOffre` (`numDomaineOffre`);

--
-- Index pour la table `offrestageenregistree`
--
ALTER TABLE `offrestageenregistree`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `fk_etudiant` (`idEtudiant`),
  ADD KEY `fk_offrestage` (`idOffreStage`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`idPersonne`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `domaineoffre`
--
ALTER TABLE `domaineoffre`
  MODIFY `numDomaineOffre` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `numEntreprise` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `offrestage`
--
ALTER TABLE `offrestage`
  MODIFY `numOffreStage` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `offrestageenregistree`
--
ALTER TABLE `offrestageenregistree`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `idPersonne` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`idPersonne`) REFERENCES `personne` (`idPersonne`) ON DELETE CASCADE;

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `EMP_ENTREPRISE_FK` FOREIGN KEY (`numEntreprise`) REFERENCES `entreprise` (`numEntreprise`),
  ADD CONSTRAINT `EMP_PERSONNE_FK` FOREIGN KEY (`idPersonne`) REFERENCES `personne` (`idPersonne`) ON DELETE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `ETU_PERSONNE_FK` FOREIGN KEY (`idPersonne`) REFERENCES `personne` (`idPersonne`) ON DELETE CASCADE;

--
-- Contraintes pour la table `offrestage`
--
ALTER TABLE `offrestage`
  ADD CONSTRAINT `FK_OffreStage_numEntreprise` FOREIGN KEY (`numEntreprise`) REFERENCES `entreprise` (`numEntreprise`),
  ADD CONSTRAINT `offrestage_ibfk_1` FOREIGN KEY (`numDomaineOffre`) REFERENCES `domaineoffre` (`numDomaineOffre`);

--
-- Contraintes pour la table `offrestageenregistree`
--
ALTER TABLE `offrestageenregistree`
  ADD CONSTRAINT `offrestageenregistree_ibfk_1` FOREIGN KEY (`idEtudiant`) REFERENCES `etudiant` (`idPersonne`) ON DELETE CASCADE,
  ADD CONSTRAINT `offrestageenregistree_ibfk_2` FOREIGN KEY (`idOffreStage`) REFERENCES `offrestage` (`numOffreStage`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
