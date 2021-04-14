# ErrorLocalization

A service that validates answers against a fixed set of rules

POST on `api/validate`

object

```json
{
    weather: string
    country: string
}
```

## Validation

As a start the service reads a json-file `code-list.json`

Validations:

* Is country valid (exists in code-list.json)?
* Validate Weather in Country

``` Java
    if(country == 'SE')
        {return weather == 'Snow'}
    if(country == 'IE')
        {return weather == 'Sun'}
```
