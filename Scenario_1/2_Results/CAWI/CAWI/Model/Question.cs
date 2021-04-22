using System.Collections.Generic;

namespace CAWI.Ui.Model
{
    public class Question
    {
        public string Caption { get; set; }
        public string Variable { get; set; }
        public IEnumerable<Option> Options { get; set; }
    }
}