using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using CAWI.Ui.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace CAWI.Ui.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class SurveyController : ControllerBase
    {
        private readonly IConfiguration _configuration;
        private readonly IHttpClientFactory _clientFactory;
        private readonly ILogger<SurveyController> _logger;
        private const string Weather = "weather";
        private const string Country = "country";

        public SurveyController(ILogger<SurveyController> logger, IConfiguration configuration, IHttpClientFactory clientFactory)
        {
            _logger = logger;
            _configuration = configuration;
            _clientFactory = clientFactory;
        }

        [HttpGet]
        public IEnumerable<Question> GetQuestions()
        {
            var countries = ReadCountriesFromService().GetAwaiter().GetResult();
            
            return new List<Question>
            {
                new Question
                {
                    Caption = "Where are you?",
                    Variable = Country,
                    Options = countries.Select(x=> new Option {Caption = x.Name, Value = x.Code})
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
            var a = (List<Answer>) answers;
            if (!a.Any()) return new List<Error>();
            
            var validationResult = await ValidateAnswers(answers.ToList());

            return validationResult;
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

        public async Task<IEnumerable<Error>> ValidateAnswers(List<Answer> answers)
        {
            var country = answers.FirstOrDefault(x => x.Variable.ToLower() == Country);
            var weather = answers.FirstOrDefault(x => x.Variable.ToLower() == Weather);

            try
            {
                var client = _clientFactory.CreateClient();

                var uri = _configuration["ErrorLocalization:Url"] +
                          $"/api/validate?country={country?.Value}&weather={weather?.Value}";
                _logger.LogInformation($"ErrorLocalizationRequest: {uri}");
                
                var content = new MultipartFormDataContent
                {
                    {new StringContent("country"), country?.Value},
                    {new StringContent("weather"), weather?.Value}
                };
                using (var response = await client.PostAsync(uri, content))
                {
                    var apiResponse = await response.Content.ReadAsStringAsync();
                    var validationErrors = JsonConvert.DeserializeObject<List<Error>>(apiResponse);
                    return validationErrors;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                _logger.LogError(e, "Error validating answers", answers);
                throw;
            }
        }

        private async Task<IEnumerable<Country>> ReadCountriesFromService()
        {
            try
            {
                var uri = _configuration["CodeList:Url"] + "/countries";
                var request = new HttpRequestMessage(HttpMethod.Get, uri);

                var client = _clientFactory.CreateClient();

                var response = await client.SendAsync(request);
                if (response.IsSuccessStatusCode)
                {
                    var responseStream = await response.Content.ReadAsStringAsync();
                    var countries = JsonConvert.DeserializeObject<IEnumerable<Country>>(responseStream);

                    return countries;
                }

                return new List<Country>();
            }
            catch (Exception e)
            {
                _logger.LogError(e, $"Error getting countries from codelist service");
                throw;
            }
        }
    }
}