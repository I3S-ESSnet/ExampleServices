# ExampleServices

`starting point, describe the repository, its purpose, how to view examples`

This repository is created to help developers and architects view examples of features applied into services. The scenarios describe different states your environment could be in, and examples how to gain a more recommended state using architecture described in CSPA.

## Scenario 1 - From disconnected services to connected metadata driven

### S1 Starting point

![Scenario 1 start](./resources/scenario_1_start.jpg)

We start with two services (CAWI, ErrorLocalization). CAWI is a web based tool for taking surveys. ErrorLocalization is a service that can flag answers as faulty based on a rule set. The services or application do not know of each other, each has implemented its own implementation of code lists. Therefor each service need logic for handling of the codes lists used. The possibility of 
Code is found here: [Code Scenario 1](./Scenario_1)

### S1 Goals

![Scenario 1 Goal](./resources/scenario_1_goal.png)

To follow principle of metadata driven systems, we see that each of the two services needs to manage the same meta data. By extracting this function we can make each service be more accomodated towards single responsibility. Thus lowering the manual labour keeping two systems up to date with the same data.

### S1 Thought process

Taking small steps and adding value incrementally. Trying not to overreach in our examples we strafe for taking small logical steps to evolve our services. This is why in our examples might take tideous small steps in advancing the architecture of the system. By extracting the handling of metadata to separate service we create a single point of thruth regarding data consistency.

### S1 Result

Having a disconnected state of applications the manual labour to keep data up to date is high. The risk of poor data integrity is high. By extracting logic of manupilating the codelist metadata we have lowered the cost of keeping data up to date. We have now a masterdata repository who is owner of its data.

## Scenario 2 - Context Aware

`TBC`
We have the three services CAWI, CodeList, and ErrorLoc but we have identified they use the same meta information in some regard. The dupication of information is therefore a fact. By making the services context aware we can extract logic regarding setting up dupicate meta information in the applications.

![Scenario 2 overview](./resources/scenario_2_mockup.jpg)

### S2 Starting point

Three services; CAWI, CodeList and ErrorLoc

### S2 Goals

By extracting a service for handling meta data regarding Statistical programs we can minimize the duplication of manual setup within each service.

## Scenario 3 - Revisiting integration pattern

![Scenario 3 overview](./resources/scenario_3_mockup.jpg)

### S3 Starting point


## Scenario 4 - Containers

![Scenario 4 overview](./resources/scenario_4_mockup.jpg)

## Scenario 5

![Scenario 5 overview](./resources/scenario_5_mockup.jpg)

###############################################

## Description of services within code book

### StatisticalPrograms

Main objective of this service is to hold metadata regarding the statistical programs. It is the owner of this data.

### CAWI

The service is the web user interface to collect statistical data from the respondents

### Code Lists

Service for maintaining and setting up code lists

### Error Localizations

Service that runs validation on datasets

### Manual Editing

Used for users editing respondent data in need for revising
