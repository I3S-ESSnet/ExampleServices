using System;

namespace StatisticalPrograms.Data.Model
{
    public class StatisticalProgram
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public DateTime DateInitiated { get; set; }
        public DateTime DateEnded { get; set; }
    }
}