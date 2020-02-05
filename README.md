# About
#### Swing desktop application which helps ophthalmologists in making right decisions. Application is split into 4 important parts (Diagnosis, Treatment, Further tests, Preventive tests). Every single part is implemented in two different ways, Rule based reasoning and Case based reasoning. We used 4 different technologies to achive this:
- Bayesian network
- RDF
- Prolog
- CBR

### Authors:
- Nikolina Tomić
- Ivan Inđič

### Professors:
- Stevan Gostojić 
- Marko Marković

# Manual <br />
> This is a basic swing desktop application. It uses MySQL database for saving data in two tables (Karton, IP).
  Karton table stores informations about patients and IP table stores informations about patient's medical examination. <br />
> Starting this applications is very easy and it can be done from every Java IDE such as Idea Intelij or Eclipse.<br />
> Only one thing is necessary to have, and that is MySQL server. To create tables use schemaTables.sql script. CBR uses 
  treatment.csv and disease.csv to get optimal solution for given problem. RDF uses groupAtRisk.ttl and rdfFurtherTests.ttl and
  parses their content to get answers.
