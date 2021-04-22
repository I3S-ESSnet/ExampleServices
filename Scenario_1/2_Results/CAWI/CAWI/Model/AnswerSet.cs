using System.Collections.Generic;
using CAWI.Ui.Controllers;

namespace CAWI.Ui.Model
{
    public class AnswerSet
    {
        public IEnumerable<Answer> Answers { get; set; }
    }
    
    public class Answer
    {
        public string Variable { get; set; }
        public string Value { get; set; }
    }
}