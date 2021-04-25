# Scenario-1

A weather survey of EU countries.

We have two sets of code for the scenario, one for the starting point `starting_1` and one for where we land after architectural design and refactoring in `2_results`

## 1_Starting

Here we have the two services of `CAWI` and `ErrorLocalization` example services is responsible for setting up its own datasets.
Each service contains a readme file for running and building the service.
Services included:

``` batch
CAWI
ErrorLocalization
```

When the services was created the list of eu countries was set. But when changes of this dataset changed, it requires changes to be made in both services to keep data consistency. In this scenario, United Kingdom needs to be removed  from the dataset. But what happens if only the ErrorLocalization updates its dataset. The data inconsistency is a fact.

### Try it yourself

#### Instructions

* Clone this repository
* Use folder `ExampleServices/Scnenario_1/1_Starting`
* Follow build instructions in each project to build and run

##### **Scene 1 - Before Brexit:**

Expected result: In the list of eu countries United Kingdom is listed, by selecting this country and a common weather the form should be able to be submitted.
The weather evaluation might be a bit harsh though.

##### **Scene 2 - After Brexit**

* remove GB from the dataset in countries.json located in `ErroLocalization/src/main/resources/json/countries.json`

Build and run ErrorLocalization again with the updated dataset. Since we did not update the web-ui, we still can select United Kingdom. When we do that we have another valuation error, which should not occur if we had a consistent dataset between the services.

## 2_Results

After consideration regarding the dataset inconsistency we have refactored the system following the **principle of metadata driven systems** and extracted the service `CodeList` for handling of the code lists for eu countries.

Services included is:

``` batch
CAWI        
ErrorLocalization
CodeList
```

### Try it yourself

#### Instructions

* Use folder `ExampleServices/Scnenario_1/2_Results`

##### **Scene 1 - Before Brexit:**

Expected result: In the list of eu countries United Kingdom is listed, by selecting this country and a common weather the form should be able to be submitted.
The weather evaluation might be a bit harsh though.

##### **Scene 2 - After Brexit**

* remove GB from the dataset in countries.json located in `CodeListService/codelistservice/code-list.json`

Build and run CodeListService again with the updated dataset. We have not updated either `CAWI` or `ErrorLocalization`, but since both services reads from the CodeListService we get an updated and correct dataset supplied for both services.
