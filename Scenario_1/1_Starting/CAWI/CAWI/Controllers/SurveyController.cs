using System.Collections.Generic;
using System.Linq;
using System.Text;
using CAWI.Ui.Model;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace CAWI.Ui.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class SurveyController : ControllerBase
    {
        private readonly ILogger<SurveyController> _logger;

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
                    Variable = "Country",
                    Options = ReadCountries().Select(x=> new Option {Caption = x.Name, Value = x.Code})
                },
                new Question
                {
                    Caption = "How is your weather?",
                    Variable = "Weather",
                    Options = CreateWeatherOptions()
                }
            };
        }
        
        [HttpPost]
        public IEnumerable<AnswerValidation> PostAnswers(IEnumerable<Answer> answers)
        {
            return answers.ToList().Select(x => new AnswerValidation()
                {Variable = x.Variable, Value = x.Value, Result = "Plausable"});
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