using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using StatisticalPrograms.Data;
using StatisticalPrograms.Data.Model;


namespace StatisticalPrograms.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class StatisticalProgramController : ControllerBase
    {
        private readonly ILogger<StatisticalProgramController> _logger;
        private readonly IStatisticalProgramRepository _repository;

        public StatisticalProgramController(ILogger<StatisticalProgramController> logger, IStatisticalProgramRepository repository)
        {
            _logger = logger;
            _repository = repository;
        }

        [HttpGet]
        public IEnumerable<StatisticalProgram> GetStatisticalPrograms()
        {
            return new StatisticalProgramRepository().GetAll();
        }

        [HttpGet("{id}")]
        public StatisticalProgram Get(int id)
        {
            return _repository.Get(id);
        }

        [HttpGet("{id}/statisticalprogramcycles")]
        public IEnumerable<StatisticalProgramCycle> GetStatisticalProgramCycles(int id)
        {
            return new List<StatisticalProgramCycle>();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            try
            {
                _repository.Delete(id);
                return Accepted();
            }
            catch (Exception e)
            {
                _logger.LogError(e.Message);
                throw;
            }
        }

        [HttpPost]
        public IActionResult Add()
        {
            _repository.Add(new StatisticalProgram() {Name = "newname"});
            return new OkResult();
        }
    }
}