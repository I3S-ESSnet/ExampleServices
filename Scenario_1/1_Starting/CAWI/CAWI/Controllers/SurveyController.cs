using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using CAWI.Ui.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Formatters;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace CAWI.Ui.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class SurveyController : ControllerBase
    {
        private readonly ILogger<SurveyController> _logger;
        private const string Weather = "weather";
        private const string Country = "country";
        
        public SurveyController(ILogger<SurveyController> logger)
        {
            _logger = logger;
        }

        [HttpGet]
        public IEnumerable<Question> GetQuestions()
        {
            return new List<Question>
            {
                new Question
                {
                    Caption = "Where are you?",
                    Variable = Country,
                    Options = ReadCountries().Select(x=> new Option {Caption = x.Name, Value = x.Code})
                },
                new Question
                {
                    Caption = "How is your weather?",
                    Variable = Weather,
                    Options = CreateWeatherOptions()
                }
            };
        }

        [HttpPost]
        public async Task<IEnumerable<Error>> PostAnswers(IEnumerable<Answer> answers)
        {
            var validationResult = await ValidateAnswers(answers);
            
            return validationResult;
        }

        public async Task<IEnumerable<Error>> ValidateAnswers(IEnumerable<Answer> answers)
        {
            var country = answers.FirstOrDefault(x => x.Variable.ToLower() == Country);
            var weather = answers.FirstOrDefault(x => x.Variable.ToLower() == Weather);
            
            using (var client = new HttpClient())
            {
                try
                {
                    var uri = "http://localhost:8081" + $"/api/validate?country={country?.Variable}&weather={weather?.Value}";
                    var content = new MultipartFormDataContent
                    {
                        {new StringContent("country"), country?.Variable},
                        {new StringContent("weather"), weather?.Variable}
                    };
                    using (var response = await client.PostAsync(uri, content))
                    {
                        var apiResponse = await response.Content.ReadAsStringAsync();
                        var errors = JsonConvert.DeserializeObject<List<Error>>(apiResponse);
                        return errors;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                    _logger.LogError(e, "Fault validating answers", answers);
                    throw;
                }

            }
        }

        private IEnumerable<Option> CreateWeatherOptions()
        {
            return new[]
            {
                new Option {Caption = "Snow", Value = "Snow"},
                new Option {Caption = "Rain", Value = "Rain"},
                new Option {Caption = "Sun", Value = "Sun"},
                new Option {Caption = "Fog", Value = "Fog"},
                new Option {Caption = "Hail", Value = "Hail"},
                new Option {Caption = "Cloud", Value = "Cloud"}
            };
        }

        private IEnumerable<Country> ReadCountries()
        {
            const string fileName = "eu-countries.json";
            var jsonString = System.IO.File.ReadAllText(fileName, Encoding.UTF8);
            var countries = JsonConvert.DeserializeObject<IEnumerable<Country>>(jsonString);
            return countries;
        }
    }
}