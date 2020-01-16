using System;
using System.Collections.Generic;
using System.Linq;
using StatisticalPrograms.Data.Model;

namespace StatisticalPrograms.Data
{
    public interface IStatisticalProgramRepository
    {
        IEnumerable<StatisticalProgram> GetAll();
        StatisticalProgram Get(int id);
        void Delete(in int id);
        int Add(StatisticalProgram statisticalProgram);
    }

    public class StatisticalProgramRepository : IStatisticalProgramRepository
    {
        private static List<StatisticalProgram> _instance;

        public IEnumerable<StatisticalProgram> GetAll()
        {
            return GetInstance();
        }

        private IEnumerable<StatisticalProgram> GetInstance()
        {
            return _instance ??= CreateStatisticalPrograms();
        }

        public StatisticalProgram Get(int id)
        {
            return GetInstance().FirstOrDefault(x => x.Id == id);
        }

        public void Delete(in int id)
        {
            var i = id;
            var item = _instance.SingleOrDefault(x => x.Id == i);
            if (item != null)
            {
                _instance.Remove(item);
            }
        }

        public int Add(StatisticalProgram statisticalProgram)
        {
            var idMax = _instance.Any() ? _instance.Max(x => x.Id) : 0;
            statisticalProgram.Id = ++idMax;
            
            _instance.Add(statisticalProgram);

            return statisticalProgram.Id;
        }

        private List<StatisticalProgram> CreateStatisticalPrograms()
        {
            return new List<StatisticalProgram>
            {
                new StatisticalProgram
                {
                    Id = 0,
                    Name = "StatisticalProgram_1",
                    DateInitiated = DateTime.MinValue,
                    DateEnded = DateTime.MaxValue
                }
                ,new StatisticalProgram
                {
                    Id = 1,
                    Name = "StatisticalProgram_2",
                    DateInitiated = DateTime.Today.Subtract(new TimeSpan(5,0,0,0)),
                    DateEnded = DateTime.Today.AddDays(50)
                }
            };
        }
    }
}
